import java.io.*;
import java.util.*;abstract
public class Solution {
	public static void main(String args[]) throws Exception
	{
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
			HashMap<String, Integer> map = new HashMap<>();
			int maxHeight = 0;
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					int x = Integer.parseInt(st.nextToken());
					String position = Integer.toString(i) + " " + Integer.toString(j);
					map.put(position, x);
					maxHeight = Math.max(maxHeight, x);
				}
			}
			
			int ans = 0;
			for(String position: map.keySet()) {
				int h = map.get(position);
				if (h == maxHeight) {
					int[] pos = tokenizerPosition(position, 2);
					HashSet<String> visited = new HashSet<>();
					if (visited.contains(position)) continue;
					visited.add(position);
					int ret = findLoad(map, pos[0], pos[1], k, visited, 1, 0);
					visited.remove(position);
					ans = Math.max(ret, ans);
				}
			}
			System.out.printf("#%d %d\n", test_case, ans);
		}
	}
	
	public static int[] tokenizerPosition(String position, int x) {
		int[] ret = new int[x];
		StringTokenizer st = new StringTokenizer(position);
		
		for(int i=0;i<x;i++) {
			int y = Integer.parseInt(st.nextToken());
			ret[i] = y;
		}
		
		return ret;
	}
	
	public static String makePositionToString(int r, int c) {
		return Integer.toString(r) + " " + Integer.toString(c);
	}
	
	public static int findLoad(HashMap<String, Integer> map, int r, int c, int k, HashSet<String> visited, int curDist, int maxDist) {
		int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
		int h = map.get(makePositionToString(r, c));
		
//		if (curDist == 6) {
//			for(String position: visited) {
//				System.out.printf("%s: %d -> ", position, map.get(position));
//			}
//			System.out.println();
//		}
		if (k == 0) { // 4방향으로 갈 수 없으면 종료 -> 마지막 위치에서 4방향으로 살펴보며 더 작은 값이 없다면 종료 
			boolean flag = false;
			for(int d=0;d<4;d++) {
				String newPos = makePositionToString(r + dir[d][0], c + dir[d][1]);
				if (map.containsKey(newPos) && !visited.contains(newPos)) { 
					int nh = map.get(newPos);
					if (nh < h) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				return maxDist = Math.max(maxDist, curDist);
			}
		}
		
		int ret = 0;
		for(int d=0;d<4;d++) {
			int nr = r + dir[d][0], nc = c + dir[d][1];
			String newPos = makePositionToString(nr, nc);
			if (map.containsKey(newPos) && !visited.contains(newPos)) {
				int nh = map.get(newPos);
				if (nh < h) {
					visited.add(newPos);
					ret = findLoad(map, nr, nc, k, visited, curDist + 1, maxDist);
					maxDist = Math.max(maxDist, ret);
					visited.remove(newPos);
					//System.out.println("originPos " + r + " " + c + " " + newPos);
				}
				for(int i=1;i<=k;i++) {
					if ((nh - i < h) && map.containsKey(newPos) && !visited.contains(newPos)) {
						visited.add(newPos);
						map.put(newPos, nh - i);
						ret = findLoad(map, nr, nc, 0, visited, curDist + 1, maxDist);
						maxDist = Math.max(maxDist, ret);
						map.put(newPos, nh);
						visited.remove(newPos);
						//System.out.println("originPos " + r + " " + c + " " + newPos);
					}
				}
			}
		}
		return maxDist;
	}
}
