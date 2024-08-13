import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] A;
    static int[][] map;
    static ArrayList<Tree> trees, liveTrees, deadTrees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());

        A = new int[n][n];
        map = new int[n][n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        trees = new ArrayList<>();
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, age));
        }

        for(int i=0;i<k;i++) {
            liveTrees = new ArrayList<>();
            deadTrees = new ArrayList<>();

            // 정렬
            Collections.sort(trees);
            spring();
            summer();
            autumn();
            winter();
        }
        System.out.println(trees.size());
    }

    public static void spring() {
        for(int i=0;i<trees.size();i++) {
            Tree t = trees.get(i);
            if (t.age > map[t.r][t.c]) deadTrees.add(t);
            else {
                map[t.r][t.c] -= t.age;
                t.age += 1;
                liveTrees.add(t);
            }
        }
        
        // 살아있는 나무로 다시 세팅하기
        trees.clear();
        trees.addAll(liveTrees);
    }

    public static void summer() { // 죽은 나무들의 양분만큼 양분을 다시 채움
        for(int i=0;i<deadTrees.size();i++) {
            Tree t = deadTrees.get(i);
            map[t.r][t.c] += (t.age / 2);
        }
    }

    public static boolean isOutOfRange(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n) return true;
        return false;
    }

    public static void autumn() {
        int[][] dir = {{-1,-1}, {-1, 0}, {-1,1}, {0,-1}, {0, 1}, {1,-1}, {1,0},{1,1}};
        
        for(int i=0;i<trees.size();i++) {
            Tree t = trees.get(i);
            if (t.age % 5 == 0) {
                for(int d=0;d<8;d++) {
                    int pr = t.r + dir[d][0];
                    int pc = t.c + dir[d][1];
                    if (!isOutOfRange(pr, pc)) trees.add(new Tree(pr, pc, 1));
                }
            }
        }
    }

    public static void winter() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                map[i][j] += A[i][j];
            }
        }
    }
}

class Tree implements Comparable<Tree> {
    int r, c;
    int age;

    Tree(int r, int c, int age) {
        this.r = r;
        this.c = c;
        this.age = age;
    }

    @Override
    public int compareTo(Tree t) {
        return this.age - t.age;
    }
}