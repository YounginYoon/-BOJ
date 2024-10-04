import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work: works) pq.offer(work);
        while (n > 0) {
            int curWork = pq.poll();
            
            if (curWork == 0) break;
            curWork--;
            pq.offer(curWork);
            n--;
        }
        
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            answer += cur * cur;
        }
        return answer;
    }
}