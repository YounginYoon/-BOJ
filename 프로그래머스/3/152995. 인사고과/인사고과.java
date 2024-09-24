import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wonho = scores[0];
        int wTotal = wonho[0] + wonho[1];
        int rank = 1;
        int maxScore = 0;
        
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1]-b[1]:b[0]-a[0]); // 근무태도 점수가 같다면 동료평가 점수를 기준으로 오름차순 (근무태도가 정렬됐으므로 이건 신경쓰지 않아도 되고, 동료평가의 값이 이전보다 작으면 인센티브를 받지 못함)
        
        // 정렬된 배열을 순차적으로 돌면서 b의 가장 큰 값을 탐색하여 인센티브를 받지 못하는 경우를 검색
        for(int[] score: scores) {
            if (maxScore <= score[1]) {
                maxScore = score[1];
                if (score[0] + score[1] > wTotal) rank++; // 원호보다 큰 경우 랭크 증가
            }
            else {
                if (score.equals(wonho)) return -1;
            }
        }
        return rank;
    }
    
}