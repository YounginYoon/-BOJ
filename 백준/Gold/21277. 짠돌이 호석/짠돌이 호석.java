import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[][] map1, map2;
        int answer = Integer.MAX_VALUE;
        int n1, m1, n2, m2;

        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken()); 

        map1 = new char[101][101];
        map2 = new char[101][101];

        for(int i=0;i<n1;i++) map1[i] = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        for(int i=0;i<n2;i++) map2[i] = br.readLine().toCharArray();

        for(int i=0;i<3;i++) {
            map1 = rotation90(map1, n1, m1);
            int tmp = n1;
            n1 = m1;
            m1 = tmp;
            
            for(int j=0;j<4;j++) {
                map2 = rotation90(map2, n2, m2);
                tmp = n2;
                n2 = m2;
                m2 = tmp;

                int area = check(map1, map2, n1, n2, m1, m2);
                if (area < 0) continue;
                answer = Math.min(answer, area);
            }
        }

        System.out.println(answer);
    }

    public static char[][] rotation90(char[][] puzzle, int n, int m) {
        char[][] newMap = new char[101][101];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                newMap[j][n - 1 - i] = puzzle[i][j];
            }
        }

        return newMap;
    }

    public static int check(char[][] map1, char[][] map2, int n1, int n2, int m1, int m2) {
        char[][] checkMap = new char[101][101];
        int ret = Integer.MAX_VALUE;
        int cnt = 0;
        for(int r=0;r<101;r++) {
            for(int c=0;c<101;c++) {
                checkMap[r][c] = '0';
            }
        }

        for(int r=0;r<n1;r++) {
            for(int c=0;c<m1;c++) {
                checkMap[r][c] = map1[r][c];
            }
        }

        for(int i=0; i<101;i++) { 
            for(int j=0; j<101;j++) {
                if (map1[i][j] == '0') {
                    boolean flag = false;
                    for(int r=0; r<n2; r++) {
                        for(int c=0;c<m2;c++) {
                            int r1 = i + r;
                            int c1 = j + c;
                            if (map1[r1][c1] == '1' && map2[r][c] == '1') {
                                flag = true;
                                break;
                            }
                        }
                        if (flag == true) break;
                    }
                    
                    if (flag == true) continue;
                    for(int r=i; r<101; r++) {
                        for(int c=j; c<101;c++) {
                            if (map2[r-i][c-j] == '1') checkMap[r][c] = '1';
                        }
                    }

                    int maxRow = 0;
                    int maxCol = 0;

                    for(int c=0;c < m1 + m2  + 1; c++) {
                        int rowCnt = 0;
                        int r = 0;
                        while (checkMap[r++][c] == '1') rowCnt++;
                        maxRow = Math.max(maxRow, rowCnt);
                    }

                    for(int r=0;r<n1+n2+1;r++) {
                        int colCnt = 0;
                        int c = 0;
                        while (checkMap[r][c++] == '1') colCnt++;
                        maxCol = Math.max(maxCol, colCnt);
                    }

                    int area = maxCol * maxRow;
                    ret = Math.min(area, ret);
                    
                    
                }
            }
        }

        return ret;
    }

}
