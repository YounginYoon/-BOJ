import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] bags = new int[k];
        Jewel[] jewelries = new Jewel[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewel(w, p);
        }

        for(int i=0;i<k;i++) {
            int bagWeight = Integer.parseInt(br.readLine());
            bags[i] = bagWeight;
        }
        Arrays.sort(bags);
        Arrays.sort(jewelries, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel a, Jewel b) {
                if (a.weight == b.weight)
                    return b.price - a.price;
                return a.weight - b.weight;
            }
        });
        int j = 0;
        long answer = 0;
        for(int i=0;i<k;i++) {
            // 현재 가방의 무게보다 작거나 같은 보석을 모두 우선순위 큐에 넣음
            while (j < n && jewelries[j].weight <= bags[i]) {
                pq.offer(jewelries[j++].price);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
    static class Jewel {
        int weight, price;

        Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
