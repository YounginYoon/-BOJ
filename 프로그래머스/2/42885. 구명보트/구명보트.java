import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        boolean[] visited = new boolean[people.length + 1];        
        
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        while (left <= right && right >= 0 && left < people.length - 1) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
            }
            else {
                right--;
            }
            answer++;
        }
        return answer;
    }
    
}