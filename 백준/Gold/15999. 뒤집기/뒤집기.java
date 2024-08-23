//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];

        for(int i=0;i<n;i++) {
            char[] str = br.readLine().toCharArray();
            for(int j=0;j<m;j++) {
                map[i][j] = str[j];
            }
        }

        boolean[][] check = new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                // 오른쪽
                if (!isOutOfRange(i, j + 1, n, m) && (map[i][j] != map[i][j+1])) check[i][j] = check[i][j+1] = true;
                // 아래
                if (!isOutOfRange(i + 1, j, n, m) && (map[i][j] != map[i+1][j])) check[i][j] = check[i + 1][j] = true;
            }
        }

        int cnt = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) if (check[i][j]) cnt++;
        }

        int ans = 1;
        for(int i=0;i<n*m-cnt;i++) {
            ans = (ans * 2) % 1000000007;
        }
        System.out.println(ans);
    }

    public static boolean isOutOfRange(int r, int c, int n, int m) {
        if (r < 0 || c < 0 || r >= n || c >= m) return true;
        return false;
    }
}
