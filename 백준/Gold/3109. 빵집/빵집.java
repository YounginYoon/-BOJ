import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char map[][];
    static boolean visited[][];

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for(int i=0;i<r;i++) {
            map[i] = br.readLine().toCharArray();
        }
        for(int i=0;i<r;i++) {
            if(dfs(i, 0)) cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean isOutofRange(int rr, int cc) {
        if (rr < 0 || cc < 0 || rr >= r || cc >= c) return true;
        return false;
    }

    public static boolean dfs(int sr, int sc) {
        visited[sr][sc] = true;
        int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};

        if (sc == c - 1) {
            return true;
        }
        for(int d=0;d<3;d++) {
            int nr = sr + dir[d][0];
            int nc = sc + dir[d][1];

            if (!isOutofRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
                if(dfs(nr, nc))
                    return true;
            }
        }

        return false;
    }

}
