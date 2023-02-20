#include <iostream>
#include <queue>
#define MAX_V 10002

using namespace std;

typedef struct _edge {
  int to, weight;
} edge;

vector<edge> list[MAX_V];
bool visit[MAX_V];
int v, e;

struct compare {
    bool operator()(edge a, edge b) {
        return a.weight > b.weight;
    }
};

int prim() {
  int ans = 0, ed = 0;
  priority_queue<struct _edge, vector<struct _edge>, compare> min_heap;
  for(int i = 0; i < list[1].size(); i++) min_heap.push(list[1][i]);
  visit[1] = true;

  while(ed < v - 1) {
    edge cur_from = min_heap.top();
    min_heap.pop();
    if(!visit[cur_from.to]) {
      ans += cur_from.weight;
      visit[cur_from.to] = true;
      ed++;
      for(int i = 0; i < list[cur_from.to].size(); i++) {
        if(!visit[list[cur_from.to][i].to]) {
          min_heap.push(list[cur_from.to][i]);
        }
      }
    }
  }
  return ans;
}

int main() {
  cin >> v >> e;
  for(int i = 1; i <= e; i++) {
    int from, to, weight;
    edge newEdge;
    cin >> from >> to >> weight;

    newEdge.to = to;
    newEdge.weight = weight;
    list[from].push_back(newEdge);
    newEdge.to = from;
    list[to].push_back(newEdge);
  }
  cout << prim() << "\n";

}