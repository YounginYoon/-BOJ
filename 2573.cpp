#include <iostream>
#include <string.h>
#include <queue>
#define MAX_NUM 301

using namespace std;

queue<pair<int, int>> IceBerg;
int drow[4] = {1, 0, -1, 0}, dcol[4] = {0, 1, 0, -1};
int N, M, map[MAX_NUM][MAX_NUM];
bool visit[MAX_NUM][MAX_NUM], visit_DFS[MAX_NUM][MAX_NUM];

void initVisit() {
  for(int i = 0; i < N; i++) {
    memset(visit[i], false, sizeof(bool) * M);
  }
}

void meltingIce() { // BFS로 빙하 녹이기
  int sea = 0;

  for(int i = 0; i < N; i++) {
    for(int j = 0; j < M; j++) {
      if(map[i][j] > 0) {
        IceBerg.push(make_pair(i, j));
        visit[i][j] = true;
      }
    }
  }

  while(!IceBerg.empty()) {
    int cur_row = IceBerg.front().first;
    int cur_col = IceBerg.front().second;
    IceBerg.pop();
    sea = 0;

    for(int k = 0; k < 4; k++) {
      int nr = cur_row + drow[k];
      int nc = cur_col + dcol[k];

      if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
      if(!visit[nr][nc] && map[nr][nc] == 0) sea++;
    }
    if(map[cur_row][cur_col] - sea <= 0) map[cur_row][cur_col] = 0;
    else map[cur_row][cur_col] -= sea;
  }
}

void DFS(int r, int c) {
  visit_DFS[r][c] = true;

  for(int i = 0; i < 4; i++) {
    int nr = r + drow[i];
    int nc = c + dcol[i];

    if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

    if(map[nr][nc] && !visit_DFS[nr][nc]) DFS(nr, nc);
  }

}

int countIceBerg() { // dfs로 빙하가 분리된 개수 세기
  int cnt = 0;

  //visit_DFS 함수 초기화
  for(int i = 0; i < N; i++) {
    memset(visit_DFS[i], false, sizeof(bool) * M);
  }

  for(int i = 0; i < N; i++) {
    for(int j = 0; j < M; j++) {
      if(map[i][j] && !visit_DFS[i][j]) {
        DFS(i, j);
        cnt++;
      }
    }
  }
  return cnt;
}

bool isMapEmpty() { // true면 비어있음, false면 비어있지 않음
  bool check = true;

  for(int i = 0; i < N; i++) {
    for(int j = 0; j < M; j++) {
      if(map[i][j] > 0) {
        check = false;
        return check;
      }
    }
  }
  return check;
}

int main() {
  int year = 0, cnt = 0;

  cin >> N >> M;

  for(int i = 0; i < N; i++) {
    for(int j = 0; j < M; j++) {
      cin >> map[i][j];
    }
  }

  cout << "\n";
  while((cnt = countIceBerg()) < 2) {
    if(isMapEmpty() || cnt == 0) {
      break;
    }
    initVisit();
    meltingIce();
    year++;

    for(int i = 0; i < N; i++) {
      for(int j = 0; j < M; j++) {
        cout << map[i][j] << " ";
      }
      cout << "\n";
    }
    cout << "\n";
  }

  if(cnt == 0) cout << "0";
  else cout << year;

}