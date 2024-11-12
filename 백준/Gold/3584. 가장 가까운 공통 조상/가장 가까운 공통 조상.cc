#include <iostream>
#include <vector>
#include <string.h>
#include <algorithm>

#define MAX_N 10002

using namespace std;
int n;
vector<int> graph[MAX_N];
int parent[MAX_N], depth[MAX_N];
bool check[MAX_N];

void init() {
    memset(depth, 0, sizeof(int) * MAX_N);
    memset(check, false, sizeof(bool) * MAX_N);
    for(int i=0;i<MAX_N;i++) graph[i].clear();
    for(int i=0;i<MAX_N;i++) parent[i] = i;
}

void dfs(int x, int d) {
    check[x] = true;
    depth[x] = d;

    for(int i=0;i<graph[x].size(); i++) {
        int child = graph[x][i]; 
        if (check[child]) continue;
        parent[child] = x;
        dfs(child, d + 1);
    }
}

int lca(int a, int b) { // a와 b의 최소 공통 조상 찾기
    // 레벨이 다르면 동일하도록 맞춤
    while (depth[a] != depth[b]) {
        if (depth[a] > depth[b]) a = parent[a];
        else b = parent[b];
    }

    // 노드가 같아지도록, 즉 조상 노드가 같아지도록
    while (a != b) {
        a = parent[a];
        b = parent[b];
    }

    return a;
}


int main() {
    vector<int> ans;
    int t;
    cin >> t;
    
    while(t--) {
        int n;
        init();
        cin >> n;
        for(int i=0;i<n-1;i++) {
            int p, c;
            cin >> p >> c;
            graph[p].push_back(c);
            parent[c] = p;
        }

        for(int i=1;i<=n;i++) {
            if (parent[i] == i) { // 루트 노드
                dfs(i, 0);
                break;
            }
        }

        int x, y;
        cin >> x >> y;
        int root = lca(x, y);
        ans.push_back(root);
    }

    for(int i=0;i<ans.size();i++) cout << ans[i] << "\n";

}