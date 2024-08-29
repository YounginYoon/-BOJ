//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.sql.ShardingKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int r, c, m;
    static LinkedList<Shark> sharks;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static SharkInfo[][] map;
    static int kc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new SharkInfo[r][c];
        kc = 0;
        sharks = new LinkedList<>();

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int rr = Integer.parseInt(st.nextToken()) - 1;
            int cc = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            Shark shark = new Shark(i + 1, rr, cc, s, d, z);
            map[rr][cc] = new SharkInfo(i+1, z);
            sharks.add(shark);
        }
        if (m == 0) System.out.println(0);
        else System.out.println(play());
    }

    static class Shark {
        int idx;
        int r, c;
        int s, d, z; // s: 속력, d: 이동방향, z: 크기
        Shark(int idx, int r, int c, int s, int d, int z) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static class SharkInfo {
        int idx, size;
        SharkInfo(int idx, int size) {
            this.idx = idx;
            this.size = size;
        }
    }

    public static int play() {
        int ans = 0;
        while (kc < c) {
            // printMap(map);
            // 낚시왕이 있는 열에 있는 상어 중 행이 제일 작은 상어를 잡음
            int tar = -1;
            for(int rr=0;rr<r;rr++) {
                if (map[rr][kc] != null) {
                    ans += map[rr][kc].size;
                    tar = map[rr][kc].idx;
                    //map[rr][kc] = null;
                    break;
                }
            }

            if (tar > -1) {
                for(Shark shark: sharks) {
                    if (shark.idx == tar) {
                        shark.idx = -1;
                        break;
                    }
                }
            }

            // 상어 이동
            SharkInfo[][] newMap = new SharkInfo[r][c];
            boolean[] dead = new boolean[sharks.size() + 1];
            for(Shark shark: sharks) {
                if (shark.idx == -1) continue; // 먹힌 상어들
                else if (dead[shark.idx]) continue;

                // 상어 이동
                int nr = shark.r;
                int nc = shark.c;
                int move = shark.s % ((r-1) * 2);
                if (shark.d >= 2) move = shark.s % ((c-1) * 2);

                while(move > 0) {
                    if (isOutOfRange(nr + dir[shark.d][0], nc + dir[shark.d][1])) {
                        if (shark.d == 0) shark.d = 1;
                        else if (shark.d == 1) shark.d = 0;
                        else if (shark.d == 2) shark.d = 3;
                        else if (shark.d == 3) shark.d = 2;
                    }

                    nr += dir[shark.d][0];
                    nc += dir[shark.d][1];
                    move--;
                }
                //stem.out.printf("move %d %d,%d->%d,%d\n", shark.idx, shark.r, shark.c, nr, nc);
                if (newMap[nr][nc] != null) {
                    // 이미 상어가 존재하면, 현재 상어와 크기를 비교해서
                    // 더 큰 상어가 작은 상어를 잡아먹음
                    if (newMap[nr][nc].size < shark.z){ // 기존에 잇던 상어보다 사이즈가 크다면
                        // 기존에 있던 상어는 잡아먹힘
                        // 잡아먹힌 상어 사라짐
                        dead[newMap[nr][nc].idx] = true;

                        // 지금 상어가 잡아먹고 자리 차지
                        newMap[nr][nc] = new SharkInfo(shark.idx, shark.z);
                        shark.r = nr;
                        shark.c = nc;
                    }
                    else shark.idx = -1;
                }
                else { // 자리가 비어있으면 현재 상어를 삽입
                    newMap[nr][nc] = new SharkInfo(shark.idx, shark.z);
                    shark.r = nr;
                    shark.c = nc;
                }
            }

            for(Shark shark: sharks) {
                if (shark.idx > -1 && dead[shark.idx]) shark.idx = -1;
            }

            map = newMap;
            kc++;

//            System.out.println("print");
//            for (Shark shark : sharks) {
//                if (shark.idx == -1) continue;
//                System.out.printf("dir %d idx %d\n", shark.d, shark.idx);
//            }
        }

        return ans;
    }

    public static boolean isOutOfRange(int rr, int cc) {
        if (rr < 0 || cc < 0 || rr >= r || cc >= c) return true;
        return false;
    }

    public static void printMap(SharkInfo[][] map) {
        System.out.println(" ----- !map! ----- ");
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if (map[i][j] == null) System.out.printf("-1 ");
                else System.out.printf("%d ", map[i][j].size);
            }
            System.out.println();
        }
    }
}
