#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>

using namespace std;
int N, L, R;
int map[51][51];
bool visit[51][51];
vector<vector<pair<int, int>>> v;
pair<int, int> dir[4] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

void print() {
    cout << "\nprint\n";
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cout << map[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}
void init() {
    for(int i = 0; i < 51; i++) fill(visit[i], visit[i] + 51, 0);
    v.clear();
}

bool outOfRange(int r, int c) {
    if (r < 0 || c < 0 || r >= N || c >= N) return true;
    return false;
}

bool bfs() {
    vector<pair<int, int>> bfs_v;
    bool flag = false;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if (visit[i][j]) continue;

            queue<pair<int, int>> q;
            q.push({i, j});
            visit[i][j] = true;
            bfs_v.push_back({i, j});
            while (!q.empty()) {
                pair<int, int> cur = q.front();
                q.pop();
                for(int d = 0; d < 4; d++) {
                    int nr = cur.first + dir[d].first, nc = cur.second + dir[d].second;
                    if (outOfRange(nr, nc)) continue;
                    if (visit[nr][nc]) continue;
                    int diff = abs(map[cur.first][cur.second] - map[nr][nc]);
                    if (L <= diff && diff <= R) {
                        visit[nr][nc] = true;
                        bfs_v.push_back({nr, nc});
                        q.push({nr, nc});
                    }
                }
            }
            if (bfs_v.size() > 1) {
                v.push_back(bfs_v);
                flag = true;
            }
            bfs_v.clear();
        }
    }
    if (flag) return true;
    return false;
}

void calculateUnit() {
    for(int i = 0; i < v.size(); i++) {
        int sum = 0;
        int vSize = v[i].size();
        for(int j = 0; j < vSize; j++) {
            int r = v[i][j].first, c = v[i][j].second;
            sum += map[r][c];
        }
        int x = sum / vSize;
        for(int j = 0; j < vSize; j++) {
            int r = v[i][j].first, c = v[i][j].second;
            map[r][c] = x;
        }
    }
}

int solution() {
    bool flag = false;
    int cnt = 0;
    while (1) {
        init();
        // 1. 국경선을 열 통로 찾기 (L <= 인접 칸의 인구 수 차 <= R)
        flag = bfs();
        if (!flag) break;
        // 2. 연합 국가 인구 수 계산
        calculateUnit();
        //print();
        cnt++;
    }
    return cnt;
}

int main() {
    cin >> N >> L >> R;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) cin >> map[i][j];
    }
    int ans = solution();
    cout << ans << endl;
}