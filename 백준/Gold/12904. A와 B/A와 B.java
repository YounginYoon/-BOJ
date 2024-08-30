//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class Main {
    static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        System.out.println(solution());
    }

    public static int solution() {
        StringBuffer newT = new StringBuffer(T);
        while (!newT.toString().isEmpty()) {
            if (newT.toString().equals(S)) return 1;
            int lastIdx = newT.length() - 1;
            if (newT.charAt(lastIdx) == 'A') {
                // 문자열 뒤에 A라면 A 제거
                newT = newT.deleteCharAt(lastIdx);
            }
            else if (newT.charAt(lastIdx) == 'B') {
                // 문자열 뒤에 B가 있으면 B 제거 후 문자열 뒤집기
                newT = newT.deleteCharAt(lastIdx);
                newT = newT.reverse();
            }
        }
        return 0;
    }
    
}
