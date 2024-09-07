//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.ClassDefinition;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        order = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution());
    }

    public static int solution() {
        int[] tab = new int[n];
        boolean[] used = new boolean[k+1];
        int idx = 0;
        int cnt = 0;
        for(int i=0;i<k;i++) {
            if (used[order[i]]) continue; // 이미 사용중인 전기용품인 경우, pass
            else if (idx < n && tab[idx] == 0) { // 비어있는 경우 그냥 꽂음
                tab[idx++] = order[i];
                used[order[i]] = true;
            }
            else if (idx >= n) { // 멀티탭을 모두 사용하고 있을 경우
                // 멀티탭에 꽂혀있는 전기용품 중 가장 나중에 사용하거나 더이상 사용하지 않을 전기용품을 뽑는다
                int[] laterUsing = new int[k+1];
                for(int j=0;j<n;j++) {
                    int using = tab[j];
                    for(int x=i+1;x<k;x++) {
                        if (order[x] == using) {
                            laterUsing[order[x]] = x;
                            break;
                        }
                    }
                }
                int where = -1;
                int tmp = -1;
                for(int j=0;j<n;j++) {
                    int using = tab[j];
                    if (laterUsing[using] == 0) {
                        where = j;
                        break;
                    }
                    else {
                        if(laterUsing[using] > tmp) {
                            tmp = laterUsing[using];
                            where = j;
                        }
                    }
                }
                int tar = tab[where];
                used[tar] = false;
                tab[where] = order[i];
                used[order[i]] = true;
                cnt++;
            }
        }
        return cnt;
    }
}
