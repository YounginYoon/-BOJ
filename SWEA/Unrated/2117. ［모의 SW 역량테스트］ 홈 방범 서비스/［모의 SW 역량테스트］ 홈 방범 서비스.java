import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
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
			HashSet<String> map = new HashSet<>();
			int ans = 0;
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					int home = Integer.parseInt(st.nextToken());
					if (home == 1) {
						String position = Integer.toString(i) + " " + Integer.toString(j);
						map.add(position);
					}
				}
			}
			
		
			
			int k = n + 1;
			while (k > 0) {
				int cnt = check(map, n, k);
				int money = cnt * m - (k * k + (k-1) * (k-1));
				if (money >= 0) ans = Math.max(ans, cnt);
				k--;
			}
			System.out.println("#" + test_case + " " + ans);
		}
	}
	
	public static int check(HashSet<String> map, int n, int k) {
		int ret = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int t=0;
				int cnt = 0;

				for(String position: map) {
					StringTokenizer st = new StringTokenizer(position);
					int rr = Integer.parseInt(st.nextToken()), cc = Integer.parseInt(st.nextToken());
					int distance = Math.abs(rr - i) + Math.abs(cc - j);
					if (distance < k) cnt++;
				}
				
				ret = Math.max(ret,  cnt);
			}
		}
		return ret;
	}
}



