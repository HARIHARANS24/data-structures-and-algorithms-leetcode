class Solution {

    static class Node {
        Node[] next = new Node[26];
        int idx = -1;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        Node root = new Node();

        int best = 0;

        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[best].length()) {
                best = i;
            }
        }

        root.idx = best;

        for (int i = 0; i < wordsContainer.length; i++) {

            String s = wordsContainer[i];
            Node cur = root;

            for (int j = s.length() - 1; j >= 0; j--) {

                int c = s.charAt(j) - 'a';

                if (cur.next[c] == null) {
                    cur.next[c] = new Node();
                }

                cur = cur.next[c];

                if (cur.idx == -1 || better(i, cur.idx, wordsContainer)) {
                    cur.idx = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {

            String s = wordsQuery[i];
            Node cur = root;

            int res = root.idx;

            for (int j = s.length() - 1; j >= 0; j--) {

                int c = s.charAt(j) - 'a';

                if (cur.next[c] == null) break;

                cur = cur.next[c];
                res = cur.idx;
            }

            ans[i] = res;
        }

        return ans;
    }

    boolean better(int a, int b, String[] arr) {

        int x = arr[a].length();
        int y = arr[b].length();

        if (x != y) return x < y;

        return a < b;
    }
}