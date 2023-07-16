#include <iostream>
#include <stack>

using namespace std;

int **dp, R, C;
int dr[4] = {0, 1, 0, -1};
int dc[4] = {1, 0, -1, 0};

int dfs(int **map) {
    stack<pair<int, int>> s;
    s.push(make_pair(0, 0));

    while(!s.empty()) {
        pair<int, int> cur = s.top();
        int cr = cur.first;
        int cc = cur.second;
        dp[cr][cc] = 0;

        if(cr == R - 1 && cc == C - 1) {

        }
        if(dp[cr][cc] != -1) {

        }
    }
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
}