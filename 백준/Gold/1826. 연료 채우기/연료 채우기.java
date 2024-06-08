import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        OilInfo[] arr = new OilInfo[n];
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());

            arr[i] = new OilInfo(d, o);
        }
        Arrays.sort(arr, new Comparator<OilInfo>() {
            @Override
            public int compare(OilInfo a, OilInfo b) {
                return a.distance - b.distance;
            }
        });

        st = new StringTokenizer(br.readLine());
        int finish = Integer.parseInt(st.nextToken());
        int curOil = Integer.parseInt(st.nextToken());
        int idx = 0;
        boolean flag = false;
        int answer = 0;
        while (curOil < finish) {
            while (idx < n && curOil >= arr[idx].distance) {
                pq.offer(arr[idx++].oil);
            }
            if (pq.isEmpty()) {
                flag = true;
                break;
            }
            
            curOil += pq.poll();
            // System.out.println("curOil " + curOil);
            answer++;
        }
        if (flag == true) System.out.println(-1);
        else System.out.println(answer);
    }

    static class OilInfo {
        int distance, oil;
        OilInfo(int distance, int oil) {
            this.distance = distance;
            this.oil = oil;
        }
    }
}
