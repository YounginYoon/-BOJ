#include <iostream>
#include <algorithm>

using namespace std;
int **dp, R, C;
int dr[4] = {0, 1, 0, -1};
int dc[4] = {1, 0, -1, 0};

//dp에 기록되는 값: 현재 위치에서 끝 위치까지의 경로의 개수

int dfs(int cr, int cc, int **map) {
    if(cr == R - 1 && cc == C - 1)
        return 1;
    // 이미 [cr][cc]에서 끝지점까지 경로 탐색을 마쳐서 dp에 기록된 경우, 그 값을 리턴
    if(dp[cr][cc] != -1)
        return dp[cr][cc];
    dp[cr][cc] = 0;
    for(int i = 0; i < 4; i++) {
        int nr = cr + dr[i];
        int nc = cc + dc[i];
        if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
        if(map[nr][nc] < map[cr][cc]) {
            dp[cr][cc] = dp[cr][cc] + dfs(nr, nc, map); // 경로의 개수 갱신
        }
    }
    return dp[cr][cc];
}

int main() {
    cin >> R >> C;

    int **map = new int*[R];
    dp = new int*[R];

    for(int i = 0; i < R; i++) {
        map[i] = new int[C];
        dp[i] = new int[C];
    }

    for(int i = 0; i < R; i++) {
        for(int j = 0; j < C; j++) {
            cin >> map[i][j];
            dp[i][j] = -1;
        }
    }
    cout << dfs(0, 0, map);

    cout << "\n" << "\n";
    for(int i = 0; i < R; i++) {
        for(int j = 0; j < C; j++) {
            cout << dp[i][j] << " ";
        }
        cout << "\n";
    }
}