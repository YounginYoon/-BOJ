//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] visit = new boolean[m];

        int[] man = new int[n];
        int[] woman = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            man[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) {
            woman[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(woman);
        Arrays.sort(man);
        int[][] dp = new int[n + 1][m + 1];

        for(int i=1;i<n+1;i++) {
            for(int j=1;j<m+1;j++) {
                if (i == j) { // 남녀 쌍이 동일하게 매칭 완료
                    dp[i][j] = dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]);
                }
                else if (i > j) { // 남자가 여자보다 더 많음 -> 남자는 1) 솔로로 남음, 2) j번째 여자와 매칭
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]));
                }
                else { // 여자가 남자보다 더 많음 -> 여자는 1) 솔로로 남음, 2) i번째 여자와 매칭
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j-1] + Math.abs(man[i-1] - woman[j-1]));
                }
            }
        }
        System.out.println(dp[n][m]);

    }
}
