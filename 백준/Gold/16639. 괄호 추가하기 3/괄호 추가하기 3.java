import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] nums;
    static char[] operators;
    static int n;
    static int ans = Integer.MIN_VALUE;
    static int[][] dpMax, dpMin;

    public static void main(String[] args) throws IOException  {
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

        int maxLength = n/2+1;
        dpMax = new int[maxLength][maxLength];
        dpMin = new int[maxLength][maxLength];

        for(int i=0;i<maxLength;i++) dpMax[i][i] = dpMin[i][i] = nums[i];

        for(int l=1;l<maxLength;l++) {
            for(int i=0;i+l<maxLength;i++) {
                int j=i+l;
                dpMin[i][j] = Integer.MAX_VALUE; dpMax[i][j] = Integer.MIN_VALUE;
                for(int k=i;k<j;k++) {
                    int[] evals = new int[4];
                    char oper = operators[k];
                    // dpMax[i][k] dpMin[k+1][j]
                    evals[0] = eval(dpMax[i][k], dpMin[k+1][j], oper);
                    // dpMax[i][k] dpMax[k+1][j]
                    evals[1] = eval(dpMax[i][k], dpMax[k+1][j], oper);
                    // dpMin[i][k] dpMin[k+1][j]
                    evals[2] = eval(dpMin[i][k], dpMin[k+1][j], oper);
                    // dpMin[i][k] dpMax[k+1][j]
                    evals[3] = eval(dpMin[i][k], dpMax[k+1][j], oper);

                    dpMax[i][j] = Math.max(dpMax[i][j], Arrays.stream(evals).max().getAsInt()); // 배열에서 최대값을 뽑음
                    dpMin[i][j] = Math.min(dpMin[i][j], Arrays.stream(evals).min().getAsInt());
                }
            }
        }

        System.out.println(dpMax[0][maxLength - 1]);
    }

    public static int eval(int x, int y, char oper) {
        if (oper == '+') return x + y;
        else if (oper == '-') return x - y;
        return x * y;
    }
}
