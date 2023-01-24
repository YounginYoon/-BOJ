#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>

#define MAX_SIZE 1001

using namespace std;

int tomato[MAX_SIZE][MAX_SIZE];
bool visited[MAX_SIZE][MAX_SIZE] = {false, };
int path[MAX_SIZE][MAX_SIZE];
queue<pair<int, int>> tomatoes;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
int N, M;

void BFS() {
    while(!tomatoes.empty()) {
        int cur_x = tomatoes.front().first;
        int cur_y = tomatoes.front().second;
        tomatoes.pop();

        //if(cur_x == N-1 && cur_y == M-1) return;
        
        for(int i=0; i<4; i++) {
            int nx = cur_x + dx[i];
            int ny = cur_y + dy[i];


            if(0<=nx && nx<N && 0<=ny && ny<M) {
                if(!visited[nx][ny] && tomato[nx][ny]==0) {
                    visited[nx][ny] = true;
                    tomato[nx][ny] = 1;
                    path[nx][ny] = path[cur_x][cur_y] + 1;
                    tomatoes.push(make_pair(nx, ny));
                }
            }
        }

    }


    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> M >> N;
    int MAX_days = 0;

    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
            cin >> tomato[i][j];
        }
    }

    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
            if(!visited[i][j] && tomato[i][j] == 1) {
                tomatoes.push(make_pair(i, j));
            }
        }
    }

    BFS();


    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
            if(!tomato[i][j]) {
                cout << "-1\n";
                return 0;
            }
        }
    }

    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
            MAX_days = max(MAX_days, path[i][j]);
        }
    }

    cout << MAX_days << "\n";
}