//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] A;
    static int[][] move;
    static boolean[][] disappear;
    static LinkedList<Cloud> clouds;

    static int[][] dir = {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        move = new int[m][2];
        disappear = new boolean[n][n];
        clouds = new LinkedList<>();

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int d, s;
            d = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            move[i][0] = d;
            move[i][1] = s;
        }

        // 처음 구름의 위치
        clouds.addLast(new Cloud(n-1,0));
        clouds.addLast(new Cloud(n-1,1));
        clouds.addLast(new Cloud(n-2,0));
        clouds.addLast(new Cloud(n-2,1));

        for(int i=0;i<m;i++) {
            moveCloud(move[i][0], move[i][1]);
            //printA();
        }

        int ans = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) ans += A[i][j];
        }
        System.out.println(ans);
    }

    public static void printA() {
        System.out.println("----- printA -----");
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.printf("%d ", A[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    public static boolean isOutOfRange(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n) return true;
        return false;
    }

    public static void moveCloud(int d, int s) {
        int[][] newA = new int[n][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                disappear[i][j] = false;
            }
        }

        for (Cloud cloud: clouds) {
            int nr = cloud.r;
            int nc = cloud.c;

            // 구름이 d 방향으로 s칸 이동
            for(int i=1;i<=s;i++) {
                nr += dir[d][0];
                nc += dir[d][1];
                if (nr < 0) nr = n-1;
                else if (nr >= n) nr = 0;
                if (nc < 0) nc = n-1;
                else if (nc >= n) nc = 0;
            }

            // 이동한 위치에서 물의 양 1 증가
            A[nr][nc]++;

            // 구름이 사라진 위치 표시
            disappear[nr][nc] = true;
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) newA[i][j] = A[i][j];
        }

        // 물복사버그 마법: 물이 증가한 위치에서 대각선 방향으로 거리가 1인 칸에 물이 들어있는 바구니 개수 만큼 물의 양이 증가
        int[][] cross = {{-1,1}, {-1,-1}, {1,-1}, {1,1}};
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if (disappear[i][j]) {
                    int cnt = 0;
                    for(int k=0;k<4;k++) {
                        int nr = i + cross[k][0], nc = j + cross[k][1];
                        if (!isOutOfRange(nr, nc) && A[nr][nc] > 0) cnt++;
                    }
                    newA[i][j] += cnt;
                }
            }
        }

        // 구름이 모두 사라짐
        clouds.clear();

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) A[i][j] = newA[i][j];
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if (A[i][j] >= 2 && !disappear[i][j]) {
                    // 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고
                    // 물의 양이 2 줄어듦
                    // 단, disappear가 true가 아닌 칸에만 생길 수 있음
                    A[i][j] -= 2;
                    clouds.addLast(new Cloud(i, j));
                }
            }
        }

//        System.out.println("새로운 구름");
//        for(Cloud cloud: clouds) {
//            System.out.printf("%d %d\n", cloud.r, cloud.c);
//        }
//        System.out.println("구름 이동 끝");
//        printA();
    }
}

class Cloud {
    int r, c;
    Cloud(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
