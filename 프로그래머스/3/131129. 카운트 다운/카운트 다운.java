import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] answer = bfs(target);
        return answer;
    }
    
    public int[] bfs(int target) {
        HashMap<Integer, int[]> visited = new HashMap<>();
        Queue<Info> q = new LinkedList<>();
        Info minInfo = null;
        q.add(new Info(0, 0, 0));
        visited.put(0, new int[] {0, 0}); // 0: sb,  1: cnt
        
        while (!q.isEmpty()) {
            Info cur = q.poll();
            if (cur.score == target) {
                System.out.printf("Get target %d %d %d\n", cur.score, cur.sb, cur.cnt);
                // if (minInfo == null) {
                //     minInfo = cur;
                // }
                // else {
                //     if (cur.cnt < minInfo.cnt) {
                //         minInfo = cur;
                //     }
                //     else if (cur.cnt == minInfo.cnt) {
                //         if (cur.sb > minInfo.sb) {
                //             minInfo = cur;
                //         }
                //     }
                // }
                minInfo = cur;
                continue;
            }
            if (cur.score > target) continue;
            for(int i=1;i<=20;i++) {
                for(int j=1;j<=4;j++) {
                    if (1 <= j && j <= 3) {
                        int newScore = cur.score + i * j;
                        if (newScore > target) continue;
                        if (!visited.containsKey(newScore)) {
                            Info newInfo = new Info(newScore, cur.sb, cur.cnt + 1);
                            if (j == 1) newInfo.sb += 1;
                            q.add(newInfo);
                            visited.put(newScore, new int[] {newInfo.sb, newInfo.cnt});
                            if (i == 8 && j == 1) System.out.printf("cur %d score %d sb %d cnt %d\n", cur.score, newScore, newInfo.sb, newInfo.cnt);
                        }
                        else {
                            Info newInfo = new Info(newScore, cur.sb, cur.cnt + 1);
                            if (j == 1) newInfo.sb += 1;
                            int[] arr = visited.get(newScore);
                            if (arr[1] >= newInfo.cnt && arr[0] < newInfo.sb) {
                                visited.replace(newScore, new int[] {newInfo.sb, newInfo.cnt});
                                q.add(newInfo);
                                if (i == 8 && j == 1) System.out.printf("cur %d score %d sb %d cnt %d\n", cur.score, newScore, newInfo.sb, newInfo.cnt);
                            }
                        }
                    }
                    else {
                        int newScore = cur.score + 50;
                        if (newScore > target) continue;
                        if (!visited.containsKey(newScore)) {
                            Info newInfo = new Info(newScore, cur.sb + 1, cur.cnt + 1);
                            q.add(newInfo);
                            visited.put(newScore, new int[] {newInfo.sb, newInfo.cnt});
                            if (i == 8 && j == 1) System.out.printf("cur %d score %d sb %d cnt %d\n", cur.score, newScore, newInfo.sb, newInfo.cnt);
                        }
                        else {
                            Info newInfo = new Info(newScore, cur.sb + 1, cur.cnt + 1);
                            int[] arr = visited.get(newScore);
                            if (arr[1] >= newInfo.cnt && arr[0] < newInfo.sb) {
                                visited.replace(newScore, new int[] {newInfo.sb, newInfo.cnt});
                                q.add(newInfo);
                                if (i == 8 && j == 1) System.out.printf("cur %d score %d sb %d cnt %d\n", cur.score, newScore, newInfo.sb, newInfo.cnt);
                            }
                        }
                    }
                }
            }
        }
        int[] ans = new int[2];
        ans[0] = minInfo.cnt;
        ans[1] = minInfo.sb;
        return ans;
    }
    
    static class Info {
        int score, sb, cnt;
        Info(int score, int sb, int cnt) {
            this.score = score;
            this.sb = sb;
            this.cnt = cnt;
        }
    }
}