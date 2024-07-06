import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int ans = Integer.MIN_VALUE;
    static char[] operator;
    static int[] nums;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String inp = br.readLine();

        operator = new char[n / 2];
        nums = new int[n/2+1];

        for(int i=0;i<n;i++) {
            if (i % 2 == 0) {
                nums[i / 2] = inp.charAt(i) - '0';
            }
            else {
                operator[i / 2] = inp.charAt(i);
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    public static void dfs(int idx, int sum) {
        // nums[idx] (ops) sum
        // sum operator[idx - 1]= nums[idx]
        if (idx == n/2 + 1) {
            ans = Math.max(ans, sum);
            return;
        }
        char oper = '+';
        if (idx!=0) {
            oper = operator[idx - 1];
        }

        // 괄호가 쳐져 있는 경우
        // sum operator[idx-1] = (nums[idx] operator[idx] nums[idx+1])
        // 3 + (8 * 7) - 9 * 2
        if (idx <= n/2-1) {
            int ev = eval(nums[idx], nums[idx + 1], operator[idx]);
            int newSum = eval(sum, ev, oper);
            dfs(idx+2, newSum);
        }
        // sum operator[idx-1] = nums[idx]
        int newSum = eval(sum, nums[idx], oper);
        dfs(idx+1, newSum);
    }

    public static int eval(int x, int y, char oper) {
        if (oper == '+') return x + y;
        else if (oper == '-') return x - y;
        return x * y;
    }
}
