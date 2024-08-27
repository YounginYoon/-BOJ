//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        LinkedList<Integer> ans = new LinkedList<>();

        for(int i=0;i<t;i++) {
            int n = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[n];
            LinkedList<Pirate> pirates = new LinkedList<Pirate>();
            int scoreA = 0, scoreB = 0;
            for(int j=0;j<n;j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                pirates.add(new Pirate(a, b));
            }
            Collections.sort(pirates);

            for(int j=0;j<n;j++) {
                if (j % 2 == 0) {
                    // A
                    Pirate p = pirates.removeFirst();
                    scoreA += p.a;
                }
                else {
                    // B
                    Pirate p = pirates.removeFirst();
                    scoreB += p.b;
                }
            }
            ans.addLast(scoreA - scoreB);
        }

        for(Integer i: ans) System.out.println(i);
    }

    static class Pirate implements Comparable<Pirate>{
        int a, b;
        Pirate(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pirate p) {
            return (p.a + p.b) - (this.a + this.b);
        }
    }

}
