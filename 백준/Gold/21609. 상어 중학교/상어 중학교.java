// package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static int BLACK = -1, RAINBOW = 0, NO = -2;
    static int[][] dir = {{0,1},{-1,0},{0,-1},{1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long ans = 0;
        while (true) {
            int score = findGroup();
            if (score == -1) break;
            ans += (score * score);

            downMap();
//            System.out.println("down 1");
//            printMap(map);

            rotateMap();
//            System.out.println("rotate");
//            printMap(map);

            downMap();
//            System.out.println("down 2");
//            printMap(map);
        }
        System.out.println(ans);
    }

    public static boolean isOutOfRange(int r, int c) {
        return (r < 0 || c < 0 || r >= n || c >= n);
    }

    public static boolean[][] initCheck(boolean[][] check) {
        // 방문한 위치 중에서 일반 블록만 방문한 것으로 남겨둠
        boolean[][] newCheck = new boolean[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if (map[i][j] > 0 && check[i][j]) newCheck[i][j] = true;
            }
        }
        return newCheck;
    }

    public static int findGroup() {
        boolean[][] check = new boolean[n][n];
        LinkedList<int[]> maxGroupInfo = new LinkedList<>();

        LinkedList<Node> nodes = new LinkedList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if (map[i][j] > 0 && !check[i][j]) {
                    //System.out.println(i + " " + j);
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i, j});
                    check[i][j] =  true;
                    int cnt = 0;
                    int rainCnt = 0;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int cr = cur[0], cc = cur[1];
                        cnt++;
                        if (map[cr][cc] == 0) rainCnt++;
                        for(int d=0;d<4;d++) {
                            int nr = cr + dir[d][0];
                            int nc = cc + dir[d][1];
                            if (!isOutOfRange(nr, nc)) {
                                if (!check[nr][nc] && (map[i][j] == map[nr][nc] || map[nr][nc] == 0)) {
                                    check[nr][nc] = true;
                                    q.add(new int[] {nr, nc});
                                }
                            }
                        }
                    }
                    // System.out.println("기준 " + tr + " " + tc + " 개수 " + cnt + " " + rainCnt);
                    nodes.addLast(new Node(i, j, cnt, rainCnt));
                    check = initCheck(check);
                }

            }
        }
        if (nodes.isEmpty()) return -1;
        Collections.sort(nodes);
        Node targetNode = nodes.get(0);

        int maxRet = targetNode.cnt;
        int mr = targetNode.r;
        int mc = targetNode.c;
        if (maxRet < 2) return -1;

        // 가장 큰 블록 지우기
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{mr, mc});
        int target = map[mr][mc];
        map[mr][mc] = NO;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int cr = cur[0], cc = cur[1];
            for(int d=0;d<4;d++) {
                int nr = cr + dir[d][0], nc = cc + dir[d][1];
                if (!isOutOfRange(nr, nc) && (map[nr][nc] != NO) && (map[nr][nc] == target || map[nr][nc] == 0)) {
                    map[nr][nc] = NO;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        return maxRet;
    }

    public static void downMap() { // 중력 작용
        // 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동 (다른 블록이나 격자의 경계를 만나기 전까지 계속 됨)
        for(int i=n-1;i>=0;i--) { // 아래에서부터 아래로 당김
            for(int j=0;j<n;j++) {
                if (map[i][j] >= 0) { // 일반블록, 무지개 블록 이동
                    int cr = i;
                    while ((cr + 1 < n) && map[cr + 1][j] == -2) { // 다른 블록을 만나거나 격자를 빠져나가지 않도록 이동
                        cr += 1;
                    }
                    if (cr != i) { // 아래로 이동을 했다면 블록을 이동시킴
                        map[cr][j] = map[i][j];
                        map[i][j] = -2; // 원래 있던 자리는 빈칸으로
                    }
                }
            }
        }

        //printMap(map);
    }

    public static void rotateMap() {
        // 반시계방향으로 90도 회전
        int[][] newMap = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                newMap[n-1-j][i] = map[i][j];
            }
        }
        map = newMap;
        // printMap(map);
    }

    public static void printMap(int[][] check) {
        System.out.println("----- print -----");
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.printf("%d ", check[i][j]);
            }
            System.out.println();
        }
    }

    static class Node implements Comparable<Node> {
        int r, c, cnt, rain;
        Node(int r, int c, int cnt, int rain) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.rain = rain;
        }

        @Override
        public int compareTo(Node x) {
            if(x.cnt == this.cnt) {
                if (x.rain == this.rain) {
                    if (x.r == this.r) return x.c - this.c;
                    return x.r - this.r;
                }
                return x.rain - this.rain;
            }
            return x.cnt - this.cnt;
        }
    }
}
