import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n, a, b;
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            
            if (n == 0 && a == 0 && b == 0) break;
            Balloons[] teams = new Balloons[n];

            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                int cnt, dA, dB;
                cnt = Integer.parseInt(st.nextToken());
                dA = Integer.parseInt(st.nextToken());
                dB = Integer.parseInt(st.nextToken());
                teams[i] = new Balloons(cnt, dA, dB);
            }

            Arrays.sort(teams, new Comparator<Balloons>() {
                @Override
                public int compare(Balloons a, Balloons b) {
                    return Math.abs(b.dA - b.dB) - Math.abs(a.dA - a.dB);
                }
            });
            
            int ans = 0;
            for(int i=0;i<n;i++) {
                if (teams[i].dA < teams[i].dB) {
                    if (teams[i].cnt <= a) {
                        a -= teams[i].cnt;
                        ans += (teams[i].cnt * teams[i].dA);
                    }
                    else {
                        ans += (a * teams[i].dA);
                        teams[i].cnt -= a;
                        a = 0;
                        b -= teams[i].cnt;
                        ans += (teams[i].cnt * teams[i].dB);
                    }
                }
                else {
                    if (teams[i].cnt <= b) {
                        b -= teams[i].cnt;
                        ans += (teams[i].cnt * teams[i].dB);
                    }
                    else {
                        ans += (b * teams[i].dB);
                        teams[i].cnt -= b;
                        b = 0;
                        a -= teams[i].cnt;
                        ans += (teams[i].cnt * teams[i].dA);
                    }
                }
            }
            System.out.println(ans);
        }
        
    }

    static class Balloons {
        int cnt, dA, dB;
        Balloons(int cnt, int dA, int dB) {
            this.cnt = cnt;
            this.dA = dA;
            this.dB = dB;
        }
    }
}
