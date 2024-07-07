import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    static int[] nums;
    static char[] operators;
    static int n;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String inp = br.readLine();
        nums = new int[n / 2 + 1];
        operators = new char[n / 2];


        for(int i=0;i<n;i++) {
            if (i % 2 == 0) {
                nums[i/2] = inp.charAt(i) - '0';
            }
            else {
                operators[i/2] = inp.charAt(i);
            }
        }
        LinkedList<Integer> ll = new LinkedList<>();
        boolean[] check = new boolean[n/2];
        dfs(0, ll, check);
        System.out.println(ans);
    }
    // 괄호 먼저 계산
    public static void dfs(int idx, LinkedList<Integer> tmpList, boolean[] check) {
        if (idx > n / 2) {
//            for(int k=0;k<tmpList.size();k++) {
//                System.out.printf("%d ", tmpList.get(k));
//            }
//            System.out.println();
            // 곱셈 먼저 계산
            LinkedList<Integer> newNums = new LinkedList<>();
            LinkedList<Character> newOps = new LinkedList<>();
            for(int i=0;i < n/2;i++) {
                if (!check[i]) newOps.add(operators[i]);
            }
            int i=0;
            int ops_idx;
            LinkedList<Character> realNewOps = new LinkedList<>();
            while (i < tmpList.size()) {
                int m = tmpList.get(i);
                ops_idx = i;
                while(ops_idx < newOps.size() && newOps.get(ops_idx) == '*') {
                    m *= tmpList.get(ops_idx + 1);
                    ops_idx++;
                }
                if (ops_idx < newOps.size()) {
                    realNewOps.add(newOps.get(ops_idx));
                }
                i = ops_idx;
                newNums.add(m);
                i++;
            }
//            System.out.println("new nums");
//            for(int j=0;j<newNums.size();j++) {
//                System.out.printf("%d ", newNums.get(j));
//            }
//            System.out.println("\nops");
//            for(int j=0;j<realNewOps.size();j++) {
//                System.out.printf("%c ", realNewOps.get(j));
//            }
//            System.out.println();
            int ret = realEval(newNums, realNewOps);
            ans = Math.max(ret, ans);
            return;
        }
        // 괄호를 침
        if (idx <= n/2-1) {
            int bracket = evaluate(nums[idx], nums[idx + 1], operators[idx]);
            tmpList.add(bracket);
            check[idx] = true;
            dfs(idx + 2, tmpList, check);
            check[idx] = false;
            tmpList.removeLast();
        }
        // 괄호를 치지 않음
        tmpList.add(nums[idx]);
        dfs(idx + 1, tmpList, check);
        tmpList.removeLast();
    }

    public static int evaluate(int a, int b, char oper) {
        if (oper == '+') return a + b;
        else if(oper == '-') return a-b;
        return a*b;
    }

    public static int realEval(LinkedList<Integer> numList, LinkedList<Character> opsList) {
        int ret = numList.get(0);
        for(int i=0;i<opsList.size(); i++) {
            ret = evaluate(ret, numList.get(i + 1), opsList.get(i));
        }
        return ret;
    }
}
