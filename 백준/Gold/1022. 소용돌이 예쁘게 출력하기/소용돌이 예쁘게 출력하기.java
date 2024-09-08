//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int r1, c1, r2, c2;
    static int max = 0;
    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r1 = sc.nextInt();
        c1 = sc.nextInt();
        r2 = sc.nextInt();
        c2 = sc.nextInt();

        map = new int[r2-r1+1][c2-c1+1];
        fill();
        print();
    }

    public static void fill() {
        int x = 0, y = 0, d = 0;
        int num = 1, len = 1, cnt = 0;

        while (!endCheck()) {
            if (x>=r1 && x<=r2 && y>=c1 && y<=c2) map[x-r1][y-c1] = num;
            num++;
            cnt++;
            x += dir[d][0];
            y += dir[d][1];
            if (cnt == len) {
                cnt = 0;
                if (d == 1 || d == 3) len++;
                d = (d + 1) % 4;
            }
        }
        max = num - 1;
    }

    public static boolean endCheck() {
        return map[0][0] != 0 && map[r2-r1][0] != 0 && map[0][c2-c1] != 0 && map[r2-r1][c2-c1] != 0;
    }

    public static void print() {
        int maxLen = (int) Math.log10(max);
        int len;

        for(int i=0;i<=r2-r1;i++) {
            for(int j=0;j<=c2-c1;j++) {
                len = maxLen - (int) Math.log10(map[i][j]);
                for(int k=0;k<len;k++) System.out.printf(" ");
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }
}
