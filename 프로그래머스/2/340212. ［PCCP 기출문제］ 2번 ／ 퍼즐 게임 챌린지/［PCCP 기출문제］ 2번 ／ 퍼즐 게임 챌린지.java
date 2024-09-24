class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        int left = 1, right = 1;
        int level = Integer.MAX_VALUE;
        
        for(int i=0;i<n;i++) {
            right = Math.max(right, diffs[i]);
        }
        
        int middle = (left + right ) / 2;
        while(left <= right) {
            middle = (left + right) / 2;
            long t = 0;
            for(int i=0;i<n;i++) {
                if (diffs[i] <= middle) {
                    t += times[i];
                }
                else {
                    int prev = 0;
                    if (i > 0) prev = times[i - 1];
                    t = t + (diffs[i] - middle) * (times[i] + prev) + times[i];
                }
            }
            if (t <= limit) {
                level = Math.min(middle, level);
                right = middle - 1;
            }
            else left = middle + 1;
        }
        System.out.println(left + " " + right + " " + level);
        return level;
    }
}