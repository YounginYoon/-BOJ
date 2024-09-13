import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int WAIT = 0, ALIVE = 1, DIE = 2;
	public static void main(String args[]) throws Exception
	{
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
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// HashMap<Integer, DNA> dna = new HashMap<>();
			PriorityQueue<DNA> dna = new PriorityQueue<DNA>((o1,o2)->Integer.compare(o2.originX, o1.originX));
			HashSet<String> map = new HashSet<>();
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<m;j++) {
					int x = Integer.parseInt(st.nextToken());
					if (x > 0) {
						dna.offer(new DNA(x, WAIT, i, j, x));
						String position = Integer.toString(i) + " " + Integer.toString(j);
						map.add(position);
					}
				}
			}
			int dead = 0;
			while(k-- > 0) {
				changeX(dna);	
				makeNewMap(dna, map); // dna의 상태가 변했는지확인
				dead += updateState(dna);
			}
			
			int ans = 0;
			while (!dna.isEmpty()) {
				DNA cur = dna.poll();
				if (cur.state != DIE) ans++;
			}
			
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	static class DNA {
		int x, state, r, c;
		int originX;
		DNA(int x, int state, int r, int c, int originX) {
			this.x = x;
			this.state = state;
			this.r = r;
			this.c = c;
			this.originX = originX;
		}
	}
	
	public static void changeX(PriorityQueue<DNA> dna) { // 활성화 또는 죽임 
		PriorityQueue<DNA> newDna = new PriorityQueue<DNA>((o1,o2)->Integer.compare(o2.originX, o1.originX));
		while (!dna.isEmpty()) {
			DNA cur = dna.poll();
			if (cur.state != DIE && cur.x > 0) {
				cur.x--;
			}
			newDna.offer(cur);
		}
		dna.clear();
		dna.addAll(newDna);
	}
	
	public static int updateState(PriorityQueue<DNA> dna) { // 활성화 또는 죽임 
		PriorityQueue<DNA> newDna = new PriorityQueue<DNA>((o1,o2)->Integer.compare(o2.originX, o1.originX));
		int dead = 0;
		while (!dna.isEmpty()) {
			DNA cur = dna.poll();
			if (cur.x == 0 && (cur.state == WAIT || cur.state == ALIVE)) {
				if (cur.state == WAIT) {
					cur.state = ALIVE;
					cur.x = cur.originX;
					newDna.offer(cur);
				}
				else {
					cur.state = DIE;
					dead++;
				}
			}
			else newDna.offer(cur);
		}
		dna.clear();
		dna.addAll(newDna);
		return dead;
	}
	
	public static void makeNewMap(PriorityQueue<DNA> dna, HashSet<String> map) { // 세포를 번식하기 위해 더 큰 지도를 만들고 세포 번식
		//HashMap<String, Integer> newDna = new HashMap<>();
		//HashSet<String> newMap = new HashSet<>(map);
		PriorityQueue<DNA> newDna = new PriorityQueue<DNA>((o1,o2)->Integer.compare(o2.originX, o1.originX));
		int[][] dir = {{0, 1}, {1, 0}, {0,-1}, {-1,0}};
		while (!dna.isEmpty()) {
			DNA cur = dna.poll();
			if (cur.state == WAIT) { // 활성상태가 아니면 번식하지 않음
				newDna.offer(cur);
				continue;
			}
			
			for(int d=0;d<4;d++) {
				int nr = cur.r + dir[d][0], nc = cur.c + dir[d][1];
				String position = Integer.toString(nr) + " " + Integer.toString(nc);
				if (!map.contains(position)) {
					newDna.offer(new DNA(cur.originX, WAIT, nr, nc, cur.originX));
					map.add(position);
					// System.out.println(cur.r + " " + cur.c + "->" + position);
				}
			}
			newDna.offer(cur);
		}
		dna.clear();
		dna.addAll(newDna);
	}
	
	
	public static void printMap(HashSet<String> map) {
		for(String position: map) {
			System.out.printf("%s, ", position);
		}
		System.out.println();
	}
	
	public static void printDNA(PriorityQueue<DNA> dna) {
		PriorityQueue<DNA> tmp = new PriorityQueue<DNA>((o1,o2)->Integer.compare(o2.originX, o1.originX));
		tmp.addAll(dna);
		System.out.printf("DNA: ");
		while (!tmp.isEmpty()) {
			DNA d = tmp.poll();
			System.out.printf("(x: %d, state: %d, originX: %d, r: %d, c: %d) ", d.x, d.state, d.originX, d.r, d.c);
		}
		System.out.println();
	}

}