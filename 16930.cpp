#include <iostream>
#include <queue>
#include <string.h>

using namespace std;

int n, m, k, x1, y1, x2, y2;
char map[1001][1001];
int visit[1001][1001];
pair<int, int> dir[4] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

typedef struct _info {
    int x, y; // (r, c)
    int move;
} Info;

bool isOutofRange(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= m) return true;
    return false;
}

int BFS() {
    queue<Info> q;
    Info i;
    i.x = x1, i.y = y1, i.move = 0;
    q.push(i);
    visit[x1][y1] = 0;

    while (!q.empty()) {
        Info cur = q.front();
        q.pop();
        
        if (cur.x == x2 && cur.y == y2) {
            return cur.move;
        }
        
        int cx = cur.x, cy = cur.y, cmove = cur.move;
        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= k; j++) {
                int nx = cx + j * dir[i].first, ny = cy + j * dir[i].second;
                if (isOutofRange(nx, ny) || map[nx][ny] == '#') break;
                if (!visit[nx][ny]) {
                    visit[nx][ny] = cmove + 1;
                    q.push({nx, ny, cmove + 1});
                }
                else if (visit[nx][ny] == cmove + 1) {
                    // 이미 방문한 지점일 대 같은 가중치면 이어서 가봄
                    continue;
                }
                else break;
            }
        }
    }
    return -1;
}

int main() {
    ios::sync_with_stdio(0);
	cin.tie(0);
    cout.tie(0);
    
    cin >> n >> m >> k;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            cin >> map[i][j];
        }
    }
    cin >> x1 >> y1 >> x2 >> y2;
    x1--, y1--, x2--, y2--;
    for(int i = 0; i < 1001; i++) memset(visit[i], 0, sizeof(int) * 1001);
    int answer = BFS();
    cout << answer << endl;
}