class Solution {
public:
    
    static bool cmp(const vector<int>& a, const vector<int>& b) {
        // sort by (minimum - actual) in descending order
        return (a[1] - a[0]) > (b[1] - b[0]);
    }
    
    int minimumEffort(vector<vector<int>>& tasks) {
        
        sort(tasks.begin(), tasks.end(), cmp);

        long long currentEnergy = 0;
        long long answer = 0;

        for (auto &task : tasks) {
            
            int actual = task[0];
            int minimum = task[1];

            // if current energy is not enough to start this task
            if (currentEnergy < minimum) {
                
                // add only the extra energy required
                answer += (minimum - currentEnergy);
                currentEnergy = minimum;
            }

            // finish task
            currentEnergy -= actual;
        }

        return (int)answer;
    }
};