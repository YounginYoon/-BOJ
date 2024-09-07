//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer before = new StringTokenizer(br.readLine());
        StringTokenizer after = new StringTokenizer(br.readLine());
        diff = new int[n];
        for(int i=0;i<n;i++) {
            int bef = Integer.parseInt(before.nextToken());
            int aft = Integer.parseInt(after.nextToken());

            diff[i] = aft - bef;
        }

        int ans = solution();
        System.out.println(ans);
    }

    public static int solution() {
        int cnt = 0;

        while (!isAllZero(diff)) {
            // 0이 아닌 수가 연속으로 나오면서, 부호가 모두 같은
            int idx = 0;
            for(int i=0;i<n;i++) {
                if (diff[i] != 0) {
                    idx = i;
                    break;
                }
            }
            int sign = (diff[idx] < 0) ? -1:1;
            int minAbs = Integer.MAX_VALUE;
            for(int i=idx;i<n;i++) {
                // 0이 아니고 부호가 같은 연속 그룹 묶기
                if (diff[i] == 0) break;
                int checkSign = (diff[i] < 0) ? -1 : 1;
                if (checkSign == sign) {
                    minAbs = Math.min(minAbs, Math.abs(diff[i]));
                }
                else break;
            }
            cnt += minAbs;
            for(int i=idx;i<n;i++) {
                // 0이 아니고 부호가 같은 연속 그룹 묶기
                if (diff[i] == 0) break;
                int checkSign = (diff[i] < 0) ? -1 : 1;
                if (checkSign == sign) {
                    int setting = Math.abs(Math.abs(diff[i]) - minAbs);
                    diff[i] = setting * sign;
                }
                else break;
            }

//            for(int i=0;i<n;i++) {
//                System.out.printf("%d ", diff[i]);
//            }
//            System.out.println();
        }

        return cnt;
    }

    public static boolean isAllZero(int[] diff) {
        for(int i=0;i<n;i++) {
            if (diff[i] != 0) return false;
        }
        return true;
    }
}
