#include <iostream>
#include <string.h>
#include <vector>
#include <queue>

#define MAX_SIZE 20001
#define RED 1
#define BLACK 2
#define no_color 0

using namespace std;

vector<int> map[MAX_SIZE];
bool visited[MAX_SIZE];
int color[MAX_SIZE];

void BFS(int v) {
  int front, res;
  queue<int> bfs_q;
  
  for(int x = 1; x <= v; x++) {
    bfs_q.push(x);
    if(x == 1 && !visited[x]) {
      visited[x] = true;
    }
    if(color[x] == no_color) color[x] = BLACK;
    
    while(!bfs_q.empty()) {
      front = bfs_q.front();
      bfs_q.pop();
      
      for(int i = 0; i < map[front].size(); i++) {
        if(!visited[map[front][i]] && map[front][i]) {
          if(color[map[front][i]] == no_color) {
            if(color[front] == BLACK) color[map[front][i]] = RED;
            if(color[front] == RED) color[map[front][i]] = BLACK;
            bfs_q.push(map[front][i]);
            visited[map[front][i]] = true;
          }
        }
        else if (visited[map[front][i]] && map[front][i]) {
          if(color[front] == BLACK && color[map[front][i]] == BLACK) {
            cout << "NO\n";
            return;
          }
          else if(color[front] == RED && color[map[front][i]] == RED) {
            cout << "NO\n";
            return;
          }
          else continue;
        }
      }
    }
  }

  cout << "YES\n";
}

int main() {
  int K, V, E;
  cin >> K;
  for(int i = 0; i < K; i++) {
    memset(visited, false, sizeof(false) * MAX_SIZE);
    memset(color, no_color, sizeof(int) * MAX_SIZE);
    cin >> V >> E;

    for(int k = 0; k <= V; k++) map[k].clear();

    for(int j = 0; j < E; j++) {
      int x, y;
      cin >> x >> y;
      map[x].push_back(y);
      map[y].push_back(x);
    }
    BFS(V);
  }

}