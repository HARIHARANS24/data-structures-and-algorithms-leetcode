import java.util.*;

class Solution {
    static class DSU {
        int[] p, r;
        DSU(int n){
            p = new int[n];
            r = new int[n];
            for(int i=0;i<n;i++) p[i]=i;
        }
        int find(int x){
            if(p[x]!=x) p[x]=find(p[x]);
            return p[x];
        }
        boolean union(int a,int b){
            int pa=find(a), pb=find(b);
            if(pa==pb) return false;
            if(r[pa]<r[pb]) p[pa]=pb;
            else if(r[pb]<r[pa]) p[pb]=pa;
            else{ p[pb]=pa; r[pa]++; }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int lo=0, hi=200000, ans=-1;
        while(lo<=hi){
            int mid=(lo+hi)/2;
            if(can(n,edges,k,mid)){
                ans=mid;
                lo=mid+1;
            } else hi=mid-1;
        }
        return ans;
    }

    boolean can(int n,int[][] edges,int k,int target){
        DSU dsu=new DSU(n);
        int used=0;

        for(int[] e:edges){
            if(e[3]==1){
                if(e[2]<target) return false;
                if(!dsu.union(e[0],e[1])) return false;
                used++;
            }
        }

        List<int[]> opt=new ArrayList<>();
        for(int[] e:edges) if(e[3]==0) opt.add(e);

        opt.sort((a,b)->b[2]-a[2]);

        int upgrades=0;

        for(int[] e:opt){
            if(dsu.find(e[0])==dsu.find(e[1])) continue;

            int s=e[2];
            if(s>=target){
                dsu.union(e[0],e[1]);
                used++;
            } 
            else if(s*2>=target && upgrades<k){
                upgrades++;
                dsu.union(e[0],e[1]);
                used++;
            }

            if(used==n-1) return true;
        }

        return used==n-1;
    }
}