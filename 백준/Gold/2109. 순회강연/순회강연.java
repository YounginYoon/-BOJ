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
        int n = Integer.parseInt(br.readLine());
        boolean[] days = new boolean[10001];
        LinkedList<Node> nodes = new LinkedList<>();

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodes.addLast(new Node(p, d));
        }

        Collections.sort(nodes);
        int ans = 0;
        for(Node node: nodes) {
            int d = node.d;
            int p = node.p;

            while(days[d]) d--;
            if (d > 0) {
                ans += p;
                days[d] = true;
            }


        }
        System.out.println(ans);
    }

    static class Node implements Comparable<Node> {
        int p, d;
        Node(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Node x) {
            if (x.p == this.p) return x.d - this.d;
            return x.p - this.p;
        }
    }
}
