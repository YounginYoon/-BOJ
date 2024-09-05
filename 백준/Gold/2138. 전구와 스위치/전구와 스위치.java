//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicStampedReference;

public class Main {
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] before = new int[n];
        int[] after = new int[n];

        String tmp = br.readLine();
        String tmp2 = br.readLine();
        for(int i=0;i<n;i++) {
            before[i] = tmp.charAt(i) - '0';
            after[i] = tmp2.charAt(i) - '0';
        }

        /*
        * i번째 스위치로 i-1번째 전구를 after의 모양과 일치시키도록 변화시키면서 순차적으로 진행
        * i-1번째 전구 상태를 바꾸고 싶으면? i번째 스위치를 누르면 됨
        * */
        // 0번째 스위치를 눌렀을 때
        int[] newBefore = before.clone();
        newBefore[0] = 1 - before[0];
        newBefore[1] = 1 - before[1];
        int press = switchOn(newBefore, after, 1, 1, n);

        // 0번째 스위치를 누르지 않았을 때
        int[] newBeforeNotPress = before.clone();
        int notPress = switchOn(newBeforeNotPress, after, 1, 0, n);
        int ans = -1;
        if (press == -1 && notPress != -1) ans = notPress;
        else if (press != -1 && notPress == -1) ans = press;
        else if (press != -1 && notPress != -1) ans = Math.min(press, notPress);
        else if (press == -1 && notPress == -1) ans = -1;
        System.out.println(ans);
    }

    public static int switchOn(int[] target, int[] ans, int idx, int cnt, int n) {
        for(int i=idx;i<n;i++) {
            if (target[i-1] != ans[i-1]) { // 상태가 다르면 스위치를 눌러서 상태 변화
                cnt++;
                target[i] = 1 - target[i];
                target[i-1] = 1 - target[i-1];
                if (i == n - 1) continue;
                else target[i + 1] = 1 - target[i+1];
            }
        }

        if (target[n-1] == ans[n-1]) return cnt;
        return -1;
    }
}
