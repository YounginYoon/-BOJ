//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                for(int d1=1;d1<n;d1++) {
                    for(int d2=1;d2<n;d2++) {
                        if (check(i, j, d1, d2)) {
                            int ret = divideIsland(i, j, d1, d2);
                            if (ret != -1) ans = Math.min(ans, ret);
                        }
                    }
                }
            }
        }

        if (ans == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(ans);
    }

    public static boolean check(int tr, int tc, int d1, int d2) {
        if (d1 >= 1 && d2 >= 1 && (0 <= tr && tr < tr + d1 + d2 && tr + d1 + d2 < n) && (0 <= tc-d1 && tc-d1 < tc && tc < tc+d2 && tc+d2<n)) return true;
        return false;
    }

    public static void printMap(int[][] tmp, int tr, int tc, int d1, int d2) {
        System.out.println("-------- !print! ---------");
        System.out.printf("target %d %d d1 %d d2 %d\n", tr, tc, d1, d2);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.printf("%d ", tmp[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isOutOfRange(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n) return true;
        return false;
    }

    public static int divideIsland(int tr, int tc, int d1, int d2) {
        int[] total = new int[6];
        int[][] newMap = new int[n][n];

        // 5
        for(int d=0;d<=d1;d++) {
            int cr = tr + d;
            int cc = tc - d;
            if (isOutOfRange(cr, cc)) continue;
            newMap[cr][cc] = 5;
        }
        for(int d=0;d<=d2;d++) {
            int cr = tr + d;
            int cc = tc + d;
            if (isOutOfRange(cr, cc)) continue;
            newMap[cr][cc] = 5;
        }
        for(int d=0;d<=d2;d++) {
            int cr = tr + d1 + d;
            int cc = tc - d1 + d;
            if (isOutOfRange(cr, cc)) continue;
            newMap[cr][cc] = 5;
        }
        for(int d=0;d<=d1;d++) {
            int cr = tr + d2 + d;
            int cc = tc + d2 - d;
            if (isOutOfRange(cr, cc)) continue;
            newMap[cr][cc] = 5;
        }

        //printMap(newMap);

        // 1
        for(int r=0; r<tr+d1; r++) {
            for(int c=0; c<=tc; c++) {
                if (isOutOfRange(r, c)) continue;
                if (newMap[r][c] == 5) break;
                if (newMap[r][c] == 0) newMap[r][c] = 1;
            }
        }

        // 2
        for(int r=0;r<=tr+d2;r++) {
            for(int c=n-1;c>tc;c--) {
                if (isOutOfRange(r, c)) continue;
                if (newMap[r][c] == 5) break;
                if (newMap[r][c] == 0) newMap[r][c] = 2;
            }
        }

        // 3
        for(int r=tr+d1;r<n;r++) {
            for(int c=0;c<tc-d1+d2;c++) {
                if (isOutOfRange(r, c)) continue;
                if (newMap[r][c] == 5) break;
                if (newMap[r][c] == 0) newMap[r][c] = 3;
            }
        }

        // 4
        for(int r=tr+d2+1;r<n;r++) {
            for(int c=n-1;c>=tc-d1+d2;c--) {
                if (isOutOfRange(r, c)) continue;
                if (newMap[r][c] == 5) break;
                if (newMap[r][c] == 0) newMap[r][c] = 4;
            }
        }

        for(int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (newMap[i][j] == 0) newMap[i][j] = 5;
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int section = newMap[i][j];
                total[section] += map[i][j];
            }
        }

        //printMap(newMap, tr, tc, d1, d2);
        int maxTotal = -1;
        int minTotal = Integer.MAX_VALUE;

        for(int i=1;i<=5;i++) {
            if (total[i] == 0) return -1;
            minTotal = Math.min(minTotal, total[i]);
            maxTotal = Math.max(maxTotal, total[i]);
        }

        return maxTotal - minTotal;
    }
}
