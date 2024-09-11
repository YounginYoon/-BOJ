//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] map;
    static int WHITE = 0, RED = 1, BLUE = 2;
    static int[] horses;
    static Horse[] horseInfo;
    static LinkedList<Horse>[][] horseMap;
    static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        horses = new int[k];
        horseMap = new LinkedList[n][n];
        horseInfo = new Horse[k];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                horseMap[i][j] = new LinkedList<>();
            }
        }

        for (int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            horses[i] = i + 1;
            horseMap[r][c].addLast(new Horse(i + 1, r, c, d));
            horseInfo[i] = new Horse(i + 1, r, c, d);
        }

        int turn = 1;
        int idx = 1;
        while(true) {
            play(idx);
            if (turn > 1000) {
                turn = -1;
                break;
            }
            else if (finish) {
                break;
            }
            if (idx == k) turn++;
            idx++;
            if (idx > k) idx = 1;
            //settingMap();
        }

        System.out.println(turn);
    }

    public static void play(int idx) {
        Horse horse = horseInfo[idx - 1];

        // horse가 엎고 있는 모든 horse를 함께 이동해야함
        LinkedList<Horse> moved = new LinkedList<>();

        ListIterator<Horse> iterator = horseMap[horse.r][horse.c].listIterator();
        while (iterator.hasNext()) {
            Horse cur = iterator.next();
            if (cur.idx == horse.idx) {
                moved.addLast(new Horse(cur));
                iterator.remove();
                while (iterator.hasNext()) {
                    cur = iterator.next();
                    moved.addLast(new Horse(cur));
                    iterator.remove();
                }

            }
        }

        int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};

        // 이동하려는 칸이 벗어났거나 파란색이면,
        // 이동 방향을 반대로 하고, 한칸 이동
        // 단 이때 이동하는 칸이 또 파란색이면 제자리
        if (isOutOfRange(horse.r + dir[horse.d][0], horse.c + dir[horse.d][1]) || map[horse.r + dir[horse.d][0]][horse.c + dir[horse.d][1]] == BLUE) {
            if (horse.d == 0) horse.d = 1;
            else if (horse.d == 1) horse.d = 0;
            else if (horse.d == 2) horse.d = 3;
            else horse.d = 2;

            int nr = horse.r + dir[horse.d][0];
            int nc = horse.c + dir[horse.d][1];
            if (!isOutOfRange(nr, nc) && map[nr][nc] != BLUE) {
                if (map[nr][nc] == RED) {
                    Collections.reverse(moved);
                }
                while (!moved.isEmpty()) {
                    Horse cur = moved.removeFirst();
                    if (cur.idx == horse.idx) {
                        cur.d = horse.d;
                    }
                    horseMap[nr][nc].addLast(new Horse(cur.idx, nr, nc, cur.d));
                    horseInfo[cur.idx - 1] = new Horse(cur.idx, nr, nc, cur.d);
                    //System.out.printf("idx %d r %d c %d d %d\n", cur.idx, nr, nc, cur.d);
                }
                if (horseMap[nr][nc].size() >= 4) finish = true;
            }
            else {
                // 이동할 수 없으므로 원상태로 돌려둠
                while (!moved.isEmpty()) {
                    Horse cur = moved.removeFirst();
                    if (cur.idx == horse.idx) {
                        cur.d = horse.d;
                    }
                    horseMap[horse.r][horse.c].addLast(new Horse(cur.idx, cur.r, cur.c, cur.d));
                    horseInfo[cur.idx - 1] = new Horse(cur.idx, cur.r, cur.c, cur.d);
                    //System.out.printf("dont move idx %d r %d c %d d %d\n", cur.idx, cur.r, cur.c, cur.d);
                }
            }
        }
        // 이동하려는 칸이 흰색이면, 그냥 이동
        else if (map[horse.r + dir[horse.d][0]][horse.c + dir[horse.d][1]] == WHITE) {
            int nr = horse.r + dir[horse.d][0];
            int nc = horse.c + dir[horse.d][1];
            while (!moved.isEmpty()) {
                Horse cur = moved.removeFirst();
                horseMap[nr][nc].addLast(new Horse(cur.idx, nr, nc, cur.d));
                horseInfo[cur.idx - 1] = new Horse(cur.idx, nr, nc, cur.d);
                //System.out.printf("idx %d r %d c %d d %d\n", cur.idx, nr, nc, cur.d);
            }

            if (horseMap[nr][nc].size() >= 4) finish = true;
        }
        // 이동하려는 칸이 빨간 색이면, 이동 후 새로 유입된 말의 순서를 뒤집음
        // 새로 유입할 말의 순서를 뒤집어서 넣기
        else if(map[horse.r + dir[horse.d][0]][horse.c + dir[horse.d][1]] == RED){
            int nr = horse.r + dir[horse.d][0];
            int nc = horse.c + dir[horse.d][1];
            while (!moved.isEmpty()) {
                Horse cur = moved.removeLast();
                horseMap[nr][nc].addLast(new Horse(cur.idx, nr, nc, cur.d));
                horseInfo[cur.idx - 1] = new Horse(cur.idx, nr, nc, cur.d);
                //System.out.printf("idx %d r %d c %d d %d\n", cur.idx, nr, nc, cur.d);
            }
            if (horseMap[nr][nc].size() >= 4) finish = true;
        }
    }

    public static boolean isOutOfRange(int r, int c) {
        return (r < 0 || c < 0 || r >= n || c >= n);
    }

    static class Horse {
        int idx;
        int r, c, d;
        Horse(int idx, int r, int c, int d) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.d = d;
        }

        Horse(Horse copy) {
            this.idx = copy.idx;
            this.r = copy.r;
            this.c = copy.c;
            this.d = copy.d;
        }
    }
}
