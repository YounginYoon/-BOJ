#include <iostream>
#include <string.h>
#include <vector>

using namespace std;
int sum[1025][1025];
int n, m;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	vector<int> ans;
	cin >> n >> m;
	memset(sum, 0, sizeof(sum));

	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			int x;
			cin >> x;
			if (j==0) sum[i][j] = x;
			else sum[i][j] = sum[i][j-1] + x;
		}
	}
	for(int i=0;i<m;i++) {
		int sr, sc, er, ec;
		cin >> sr >> sc >> er >> ec;
		sr--,sc--,er--,ec--;
		int s = 0;
		for(int r=sr;r<=er;r++) {
			if (sc == 0) s += sum[r][ec];
			else s += (sum[r][ec] - sum[r][sc - 1]);
		}
		ans.push_back(s);
	}
	for(int i=0;i<m;i++) cout << ans[i] << "\n";
}