//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        MyArray[] myArrays = new MyArray[10];
        boolean[] isFirst = new boolean[26];
        int[] number = new int[10];

        for(int i=0;i<10;i++) {
            number[i] = -1;
            myArrays[i] = new MyArray(i, 0);
        }

        LinkedList<String> inputs = new LinkedList<String>();
        for(int i=0;i<n;i++) {
            String inp = br.readLine();
            inputs.addLast(inp);
            isFirst[inp.charAt(0)-'A'] = true;
            for(int j=0;j<inp.length();j++) {
                long x = (long) Math.pow(10, inp.length() - j - 1);
                myArrays[inp.charAt(j)-'A'].idx = inp.charAt(j)-'A';
                myArrays[inp.charAt(j) - 'A'].cnt += x;
            }
        }

        Arrays.sort(myArrays);

        // 맨앞에 오지 않으면서 가장 적게 등장하는 숫자에 0을 넣어주기
        int unique = 0;
        for(int i=0;i<10;i++) {
            if (myArrays[i].cnt > 0) unique++;
        }

        if (unique == 10) {
            for(int i=9;i>=0;i--) {
                int idx = myArrays[i].idx;
                if (!isFirst[idx] && myArrays[i].cnt > 0) {
                    number[idx] = 0;
                    break;
                }
            }
        }

        int num = 9;
        for(int i=0;i<10;i++) {
            if (num == 0) break;
            if (myArrays[i].cnt == 0) continue;
            int idx = myArrays[i].idx;
            if (number[idx] != -1) continue;
            number[idx] = num--;
        }

//        for(int i=0;i<10;i++) System.out.printf("%d ", number[i]);
//        System.out.println();

        long ans = 0;
        for(String inp: inputs) {
            long real = 0;
            for(int j=0;j<inp.length();j++) {
                real *= 10;
                real += number[inp.charAt(j) - 'A'];
            }
            ans += real;
        }
        System.out.println(ans);

    }

    static class MyArray implements Comparable<MyArray> {
        long cnt;
        int idx;
        MyArray(int idx, long cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(MyArray a) {
            return Long.compare(a.cnt, this.cnt); // 내림차순 정렬을 위해 a.cnt를 첫 번째로
        }
    }
}


