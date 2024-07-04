import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] diceMap;
    static int[] order;
    static int[] dice;
    static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x, y;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        diceMap = new int[n][m];
        dice = new int[6];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                diceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        order = new int[k];

        for(int i=0;i<k;i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }


        for(int i=0;i<k;i++) {
            int[][] moveDir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            int nx = x + moveDir[order[i] - 1][0];
            int ny = y + moveDir[order[i] - 1][1];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            x = nx; y = ny;
            int up = play(order[i], x, y);
            System.out.println(up);
        }
    }

    public static void moveDice(int dir) {
        if (dir == 1) {
            // 동
            int[] newDice = new int[6];
            newDice[4] = dice[4]; newDice[1] = dice[1];
            newDice[5] = dice[2]; newDice[2] = dice[0]; newDice[3] = dice[5]; newDice[0] = dice[3];
            for(int i=0;i<6;i++) dice[i] = newDice[i];
        }
        else if(dir == 2) {
            // 서
            int[] newDice = new int[6];
            newDice[5] = dice[3]; newDice[3] = dice[0]; newDice[0] = dice[2]; newDice[2] = dice[5];
            newDice[4] = dice[4]; newDice[1] = dice[1];
            for(int i=0;i<6;i++) dice[i] = newDice[i];
        }
        else if(dir == 3) {
            // 북
            int[] newDice = new int[6];
            newDice[5] = dice[1]; newDice[1] = dice[0]; newDice[0] = dice[4]; newDice[4] = dice[5];
            newDice[3] = dice[3]; newDice[2] = dice[2];
            for(int i=0;i<6;i++) dice[i] = newDice[i];
        }
        else if(dir == 4) {
            // 남
            int[] newDice = new int[6];
            newDice[5] = dice[4]; newDice[4] = dice[0]; newDice[0] = dice[1]; newDice[1] = dice[5];
            newDice[3] = dice[3]; newDice[2] = dice[2];
            for(int i=0;i<6;i++) dice[i] = newDice[i];
        }

    }

    public static int play(int dir, int xx, int yy) {
        // 이동
        moveDice(dir);

        // 이동한 칸의 숫자가 0이면 주사위 바닥면의 숫자를 복사
        if (diceMap[xx][yy] == 0) {
            diceMap[xx][yy] = dice[5];
        }
        else { // 이동한 칸의 숫자가 0이 아닐 경우, 칸의 숫자가 주사위 바닥면으로 복사, 칸의 숫자는 0이 됨
            dice[5] = diceMap[xx][yy];
            diceMap[xx][yy] = 0;
        }

        return dice[0];
    }

}