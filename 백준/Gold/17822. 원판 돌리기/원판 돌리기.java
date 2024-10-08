import java.util.*;
import java.io.*;


public class Main {
	static int n, m, t;
	static LinkedList<Integer>[] boards;
	static int[][] orders;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		boards = new LinkedList[n + 1];
		orders = new int[t + 1][3];
		
		for(int i=0;i<n;i++) {
			boards[i] = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				boards[i].addLast(Integer.parseInt(st.nextToken()));
			}
		}
		
		//System.out.println("here");
		for(int i=0;i<t;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			orders[i][0] = x;
			orders[i][1] = d;
			orders[i][2] = k;
		}
		
		//System.out.println("here2");
		
		for(int i=0;i<t;i++) {
			int[] ord = orders[i];
			rotation(ord[0], ord[1], ord[2]);
			remove();
			//printBoard();
		}
		int answer = 0;
		for(int i=0;i<n;i++) {
			LinkedList<Integer> board = boards[i];
			ListIterator<Integer> li = board.listIterator();
			while(li.hasNext()) {
				int x = li.next();
				if (x != -1) answer += x;
			}
		}
		System.out.println(answer);
	}
	
	public static void rotation(int x, int d, int k) {
		Queue<Integer> q = new LinkedList<>();
		int num = x;
		while (num <= n) {
			q.add(num);
			num += x;
		}
		
		while (!q.isEmpty()) {
			int idx = q.poll() - 1;
			//System.out.println(idx);
			LinkedList<Integer> board = boards[idx];
			for(int i=0;i<k;i++) {
				if (d == 0) { // 시계 방향 회전 -> tail => head
					int tail = board.removeLast();
					board.addFirst(tail);
				}
				else { // 반시계 방향 -> head => tail
					int head = board.removeFirst();
					board.addLast(head);
				}
			}
		}
		//System.out.println("rotation fin");
	}
	
	public static void remove() {
		// 연결리스트를 배열로 변환
//		System.out.println("remove!");
//		printBoard();
		int[][] boardMap = new int[n+1][];
		int[][] tmpMap = new int[n + 1][n];
		
		for(int i=0;i<n;i++) {
			boardMap[i] = boards[i].stream().mapToInt(Integer::intValue).toArray();
			tmpMap[i] = boardMap[i].clone();
		}
		
		boolean flag = false;
		double avg = 0;
		int cnt = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int num = boardMap[i][j];
				if (num == -1) continue;
				avg += num;
				cnt++;
				// 인접한 수 중 같은 수 찾기
				boolean find = false;
				int[][] dir = {{0, -1}, {0, 1}, {-1,0},{1,0}};
				for(int d=0;d<4;d++) {
					int nr = i + dir[d][0], nc = j + dir[d][1];
					if (nr < 0 || nr >= n) continue;
					
					if (nc < 0) nc = m - 1;
					else if (nc >= m) nc = 0;
					
					int compare = boardMap[nr][nc];
					if (compare == -1) continue;
					if (num == compare) {
						flag = true;
						find = true;
						tmpMap[nr][nc] = -1;
					}
				}
				if (find) {
					tmpMap[i][j] = -1;
				}
			}
		}

		if (!flag) {
			avg = avg / cnt;
			//System.out.println("cnt " + cnt + " avg " + avg);
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if (tmpMap[i][j] == -1) continue;
					if (Double.compare(tmpMap[i][j], avg) > 0) tmpMap[i][j] -= 1;
					else if (Double.compare(tmpMap[i][j], avg) < 0) tmpMap[i][j] += 1;
				}
			}
		}
		
		LinkedList<Integer>[] newBoard = new LinkedList[n + 1];
		for(int i=0;i<n;i++) {
			newBoard[i] = new LinkedList<>();
			for(int j=0;j<m;j++) newBoard[i].addLast(tmpMap[i][j]);
		}
		boards = newBoard;
		//System.out.println("remove fin");
	}
	
	public static void printBoard() {
		System.out.println("===== print =====");
		for(int i=0;i<n;i++) {
			LinkedList<Integer> board = boards[i];
			for(int j=0;j<board.size();j++) {
				System.out.printf("%d ", board.get(j));
			}
			System.out.println();
		}
	}
}




