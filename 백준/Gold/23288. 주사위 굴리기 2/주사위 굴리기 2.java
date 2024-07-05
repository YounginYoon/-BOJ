import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] diceMap;
    static int[] dice;
    static int x, y, dir;
    static int[] opposite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x, y;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        diceMap = new int[n][m];
        dice = new int[6];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                diceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<6;i++) dice[i] = i + 1;
        dir = 0;
        int ans = 0;
        x = 0; y = 0;
        int[][] moveDir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        opposite = new int[4];
        opposite[0] = 2; opposite[1] = 3; opposite[2] = 0; opposite[3] = 1;
        for(int i=0;i<k;i++) {
            int nx = x + moveDir[dir][0];
            int ny = y + moveDir[dir][1];
            if (isOutOfRange(nx, ny)) {
                dir = opposite[dir];
                nx = x + moveDir[dir][0];
                ny = y + moveDir[dir][1];
            }
            x = nx; y = ny;
            int cnt = play(x, y);
            ans += (cnt * diceMap[x][y]);
            moveDice(dir);
            int a = dice[5]; int b = diceMap[x][y];
            if (a > b) {
                dir++;
                if (dir > 3) dir = 0;
            }
            else if (a < b) {
                dir--;
                if (dir < 0) dir = 3;
            }
        }
        System.out.println(ans);
    }

    public static void moveDice(int dir) {
        if (dir == 0) {
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
        else if(dir == 1) {
            // 남
            int[] newDice = new int[6];
            newDice[5] = dice[4]; newDice[4] = dice[0]; newDice[0] = dice[1]; newDice[1] = dice[5];
            newDice[3] = dice[3]; newDice[2] = dice[2];
            for(int i=0;i<6;i++) dice[i] = newDice[i];
        }

    }

    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int play(int x, int y) {
        int cnt = 1;
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();

        visited[x][y] = true;
        q.offer(new Pos(x, y));
        int tar = diceMap[x][y];

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if (diceMap[cur.x][cur.y] != tar) {
                return cnt;
            }
            int[][] moveDir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for(int d=0;d<4;d++) {
                int nx = cur.x + moveDir[d][0];
                int ny = cur.y + moveDir[d][1];
                if (!isOutOfRange(nx, ny) && !visited[nx][ny]) {
                    if (diceMap[nx][ny] == tar) {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static boolean isOutOfRange(int xx, int yy) {
        if (xx < 0 || yy < 0 || xx >= n || yy >= m) return true;
        return false;
    }

}