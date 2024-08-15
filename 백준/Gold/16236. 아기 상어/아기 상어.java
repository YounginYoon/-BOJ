//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, sr, sc;
    static int[][] map;
    static int BABY_SHARK, ans, eat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        BABY_SHARK = 2;
        ans = 0;
        map = new int[n][n];
        eat = 0;

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    sr = i; sc = j;
                }

            }
        }

        while (true) {
            ArrayList<EatFish> canEatFish = bfs(sr, sc);

            if (canEatFish.size() == 0) {
                System.out.println(ans);
                return;
            }

            sr = canEatFish.get(0).r;
            sc = canEatFish.get(0).c;
            int d = canEatFish.get(0).dist;

            eat++;
            if (BABY_SHARK == eat) {
                BABY_SHARK++;
                eat = 0;
            }
            map[sr][sc] = 0;
            ans += d;
        }
    }

    public static boolean isOutOfRange(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n) return true;
        return false;
    }

    public static ArrayList<EatFish> bfs(int sharkX, int sharkY) {
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];

        Queue<SharkPos> q = new LinkedList<>();
        ArrayList<EatFish> CanEatFishList = new ArrayList<>();
        int[][] dir = {{-1,0}, {0,-1},{0,1},{1,0}};

        q.offer(new SharkPos(sharkX, sharkY));
        visited[sharkX][sharkY] = true;

        while(!q.isEmpty()) {
            SharkPos cur = q.poll();

            for(int d=0;d<4;d++) {
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];

                if (!isOutOfRange(nr, nc) && !visited[nr][nc] && map[nr][nc] <= BABY_SHARK) {
                    visited[nr][nc] = true;
                    dist[nr][nc] = dist[cur.r][cur.c] + 1;
                    q.offer(new SharkPos(nr, nc));

                    if (map[nr][nc] > 0 && map[nr][nc] < BABY_SHARK) {
                        CanEatFishList.add(new EatFish(dist[nr][nc], nr, nc));
                    }
                }
            }
        }

        Collections.sort(CanEatFishList);
        return CanEatFishList;
    }
}

class SharkPos {
    int r, c;
    SharkPos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class EatFish implements Comparable<EatFish> {
    int dist, r, c;
    EatFish(int dist, int r, int c) {
        this.dist = dist;
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(EatFish e) {
        if (this.dist == e.dist) {
            if (this.r == e.r) {
                return this.c - e.c;
            }
            return this.r - e.r;
        }
        return this.dist - e.dist;
    }
}
