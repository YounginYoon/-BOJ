#include <string>
#include <string.h>
#include <stdio.h>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>
#include <unordered_set>

using namespace std;
int n, m;
int map[501][501];
int info[250001];

bool isOutOfRange(int r, int c) {
    return (r < 0 || c < 0 || r >= n || c >= m);
}


void bfs(vector<vector<int>> land) {
    bool visited[501][501];
    int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};
    
    for(int i=0;i<501;i++) memset(visited[i], false, sizeof(bool) * (501));
    memset(info, 0, sizeof(int) * 250001);
    int idx = 1;
    for(int i=0;i<n;i++) {
        for(int j=0;j<m;j++) {
            if (!visited[i][j] && land[i][j] == 1) {
                queue<vector<int>> q, log;
                visited[i][j] = true;
                q.push({i, j});
                int cnt = 1;
                
                while (!q.empty()) {
                    vector<int> cur = q.front();
                    q.pop();
                    log.push({cur[0], cur[1]});
                    
                    for(int d=0;d<4;d++) {
                        int nr = cur[0] + dir[d][0], nc = cur[1] + dir[d][1];
                        if (!isOutOfRange(nr, nc) && !visited[nr][nc] && land[nr][nc]) {
                            visited[nr][nc] = true;
                            q.push({nr, nc});
                            cnt++;
                        }
                    }
                }
                while (!log.empty()) {
                    vector<int> cur = log.front(); log.pop();
                    map[cur[0]][cur[1]] = idx;
                }
                info[idx] = cnt;
                idx++;
            }
        }
    }
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    n = land.size();
    m = land[0].size();
    
    for(int i=0;i<501;i++) memset(map[i], 0, sizeof(int) * (501));
    bfs(land);
    
    
    for(int col = 0; col < m; col++) {
        int cnt = 0;
        unordered_set<int> visited;
        for(int row = 0; row < n; row++) {
            if (map[row][col] > 0) {
                int idx = map[row][col];
                if (visited.find(idx) == visited.end()) {
                    cnt += info[idx];
                    visited.insert(idx);
                }
            }
        }
        //cout << col << " " << cnt << endl;
        answer = max(answer, cnt);
    }
    return answer;
}