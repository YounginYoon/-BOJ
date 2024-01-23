#include <iostream>
#include <queue>
#include <string.h>

using namespace std;
char map[13][7];
bool visit[12][6];
vector<pair<int, int>> v; // 탐색을 시작할 위치 넣음
pair<int, int> dir[4] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

typedef struct _info {
    int r, c, move;
} Info;

bool outOfRange(int r, int c) {
    if (r < 0 || c < 0 || r >= 12 || c >= 6) return true;
    return false;
}

void initVisit() {
    for(int i = 0; i < 12; i++) {
        memset(visit[i], false, sizeof(bool) * 6);
    }
}

void print() {
    cout << "\nprint\n";
    for(int i = 0; i < 12; i++) {
        for(int j = 0; j < 6; j++) {
            cout << map[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

bool bfs() { // 길이 있는지 탐색
    queue<pair<int, int>> q;

    initVisit();
    v.clear();
    for(int i = 0; i < 12; i++) {
        for(int j = 0; j < 6; j++) {
            if (map[i][j] != '.') {
                int cnt = 1;
                q.push({i, j});
                visit[i][j] = true;
                while (!q.empty()) {
                    int cr = q.front().first, cc = q.front().second;
                    q.pop();
                    for(int d = 0; d < 4; d++) {
                        int nr = cr + dir[d].first, nc = cc + dir[d].second;
                        if (outOfRange(nr, nc)) continue;
                        if (visit[nr][nc]) continue;
                        if (map[nr][nc] != map[i][j]) continue;
                        cnt++;
                        visit[nr][nc] = true;
                        q.push({nr, nc});
                    }
                }
                if (cnt >= 4) {
                    //cout << "success " << i << " " << j << endl;
                    v.push_back({i, j});
                }
            }
        }
    }
    if (v.size() > 0) return true;
    return false;
}

void remove() {
    for(int i = 0; i < v.size(); i++) {
        int sr = v[i].first, sc = v[i].second;
        queue<pair<int, int>> q;
        
        initVisit();
        q.push({sr, sc});
        visit[sr][sc] = true;
        char match = map[sr][sc];
        map[sr][sc] = '.';
        while (!q.empty()) {
            int cr = q.front().first, cc = q.front().second;
            q.pop();
            for(int j = 0; j < 4; j++) {
                int nr = cr + dir[j].first, nc = cc + dir[j].second;
                if (outOfRange(nr, nc)) continue;
                if (visit[nr][nc]) continue;
                if (map[nr][nc] != match) continue;
                visit[nr][nc] = true;
                q.push({nr, nc});
                map[nr][nc] = '.';
            }
        }
    }
}

void downPuyo() {
    for(int i = 11; i >= 0; i--) {
        for(int j = 0; j < 6; j++) {
            if (map[i][j] != '.') {
                int nr = i, nc = j;
                bool flag = false;
                while (!outOfRange(nr + 1, nc) && map[nr + 1][nc] == '.') {
                    nr++;
                    flag = true;
                }
                if (!flag) continue;
                char match = map[i][j];
                map[nr][nc] = match;
                map[i][j] = '.';
            }
        }
    }
}

int solution() {
    int cnt = 0;
    while (bfs() == true) {
        // 같은 색 지우기
        remove();
        cnt++;
        //print();
        // 빈칸에 뿌요 내리기
        downPuyo();
        //print();

    }
    return cnt;
}

int main() {
    for(int i = 0; i < 12; i++) {
        for(int j = 0; j < 6; j++) {
            cin >> map[i][j];
        }
    }
    int ans = solution();
    cout << ans << endl;
}
