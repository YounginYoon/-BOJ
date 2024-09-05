//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        LinkedList<Node> pp = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int cost = Integer.parseInt(st.nextToken());
            p[i] = cost;
            pp.addLast(new Node(i, cost));
        }

        int m = Integer.parseInt(br.readLine());
        Collections.sort(pp);

        int cheapestNum = pp.get(0).number;

        // 가장 싼 숫자를 최대한 많이 사서 최대 자릿수를 가진 방번호 생성
        int cost = 0;
        int[] ans = new int[100];
        int idx = 0;
        if (cheapestNum == 0) {
            if (pp.size() == 1) {
                System.out.println(0);
                return;
            }
            if (p[pp.get(1).number] <= m) {
                ans[idx++] = pp.get(1).number;
                cost += p[pp.get(1).number];
            }
            else {
                System.out.println(0);
                return;
            }
        }
        while (cost + p[cheapestNum] <= m) {
            ans[idx++] = cheapestNum;
            cost += p[cheapestNum];
        }


        // 앞자리 번호부터 큰 숫자로 변환
        // 단, 숫자를 만드는 비용은 M보다 작아야함
        for(int i=0;i<idx;i++) {
            int curCharCost = p[ans[i]];
            if (i == 0) {
                for(int j=n-1;j>0;j--) {
                    int newCost = cost - curCharCost + p[j];
                    if (newCost <= m) {
                        ans[i] = j;
                        cost = newCost;
                        break;
                    }
                }
            }
            else {
                for(int j=n-1;j>=0;j--) {
                    int newCost = cost - curCharCost + p[j];
                    if (newCost <= m) {
                        ans[i] = j;
                        cost = newCost;
                        break;
                    }
                }
            }
        }

        String ansNum = "";
        for(int i=0;i<idx;i++) {
            ansNum += Integer.toString(ans[i]);
        }
        System.out.println(ansNum);

    }

    static class Node implements Comparable<Node> {
        int number, cost;
        Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node a) {
            if (a.cost == this.cost) return this.number - a.number;
            return this.cost - a.cost;
        }
    }


}
