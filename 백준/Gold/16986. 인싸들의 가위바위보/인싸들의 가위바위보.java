//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, table[][], num[][];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        table = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        num = new int[3][20];
        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 20; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        if (N < K) {
            System.out.println(0);
            return;
        }
        
        visited = new boolean[N + 1];
        perm(0);
        System.out.println(0);
    }
    
    public static void perm(int cnt) {
        if (cnt == N) {
            fight();
            return;
        }
        for(int i=1;i<=N;i++) {
            if (!visited[i]) {
                visited[i]=true;
                num[0][cnt]=i;
                perm(cnt+1);
                visited[i]=false;
            }
        }
    }
    
    public static void fight() {
        int[] winCnt = new int[3];
        int[] idx = new int[3];
        
        int player1 = 0;
        int player2 = 1;
        
        while (!(winCnt[0] == K || winCnt[1] == K || winCnt[2] == K)) {
            int winner;
            int v1 = num[player1][idx[player1]];
            int v2 = num[player2][idx[player2]];
            
            if (v1 == 0 || v2 == 0) return;
            if (table[v1][v2] == 2) winner = player1;
            else if (table[v1][v2] == 1) winner = Math.max(player1, player2);
            else winner = player2;
            
            winCnt[winner]++;
            idx[player1]++;
            idx[player2]++;
            
            player2 = 3 - player1 - player2;
            player1 = winner;
        }
        
        if (winCnt[0] == K) {
            System.out.println(1);
            System.exit(0);
        }
    }

}
