import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<List<Integer>> path = new ArrayList<>();
        for(int i=0;i<n+1;i++) path.add(new ArrayList<>());
        for(int i=0;i<roads.length;i++) {
            int[] road = roads[i];
            int a = road[0], b = road[1];
            path.get(a).add(b);
            path.get(b).add(a);
        }
        
        int[] dist = dijkstra(path, destination, n);
        for(int i=0;i<sources.length;i++) {
            int x = sources[i];
            if (dist[x] == Integer.MAX_VALUE) answer[i] = -1;
            else answer[i] = dist[x];
        }
        return answer;
    }
    
    public int[] dijkstra(List<List<Integer>> path, int start, int n) {
        int[] dist = new int[n + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);    
        Queue<Integer> q = new PriorityQueue<>(); // 0: dist, 1: node number
        q.add(start);
        dist[start] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int next: path.get(cur)) {
                if (dist[cur] + 1 < dist[next]) {
                    dist[next] = dist[cur] + 1;
                    q.add(next);
                }
            }
        }
        
        return dist;
    }
}