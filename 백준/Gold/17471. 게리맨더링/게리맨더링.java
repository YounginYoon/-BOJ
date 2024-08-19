//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] peoples;
    static int ans = Integer.MAX_VALUE, n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        peoples = new int[n + 1];
        map = new int[n+1][n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            peoples[i+1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j=0;j<k;j++) {
                int target = Integer.parseInt(st.nextToken());
                map[i+1][target] = 1;
            }
        }

        for(int i=1;i<n;i++) {
            combination(i, 0, 1, new LinkedList<Integer>());
        }

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);

    }

    public static void combination(int limit, int cnt, int idx, LinkedList<Integer> list) {
        if (limit == cnt) {
            bfs(list);
            return;
        }

        for(int i=idx;i<=n;i++) {
            list.addLast(i);
            combination(limit, cnt+1, i+1, list);
            list.removeLast();
        }
    }

    public static void bfs(LinkedList<Integer> ll) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        q.offer(ll.get(0));
        visited[ll.get(0)] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int nextNode: ll) {
                if (!visited[nextNode] && map[cur][nextNode] == 1) {
                    q.offer(nextNode);
                    visited[nextNode] = true;
                }
            }
        }

        for(int node:ll) {
            if (!visited[node]) return;
        }

        LinkedList<Integer> newLL = new LinkedList<>();

        for(int i=1;i<=n;i++) {
            if (!visited[i]) newLL.add(i);
        }
        bfs2(newLL, ll);
    }

    public static void bfs2(LinkedList<Integer> ll, LinkedList<Integer> ll2) {
//
//        System.out.println("ll");
//        for(int node: ll) System.out.printf("%d ", node);
//        System.out.println();
//
//        System.out.println("ll2");
//        for(int node: ll2) System.out.printf("%d ", node);
//        System.out.println();

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        q.offer(ll.get(0));
        visited[ll.get(0)] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int nextNode: ll) {
                if (!visited[nextNode] && map[cur][nextNode] == 1) {
                    q.offer(nextNode);
                    visited[nextNode] = true;
                }
            }
        }

        for(int node:ll) {
            if (!visited[node]) return; // 연결되어있지 않으면 종료
        }

        // ll과 ll2의 인원 차이 구하기
        int team1 = 0;
        int team2 = 0;

        for(int node: ll) {
            team1 += peoples[node];
        }

        for(int node: ll2) {
            team2 += peoples[node];
        }

        ans = Math.min(Math.abs(team1-team2), ans);
    }
}
