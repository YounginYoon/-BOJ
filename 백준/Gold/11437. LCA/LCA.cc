#include <iostream>
#include <vector>
#include <string.h>

using namespace std;

vector<int> graph[50001];
int parent[50001], depth[50001];
bool check[50001];

void dfs(int x, int d) { // 깊이 측정
    check[x] = true;
    depth[x] = d;
    for(int i=0;i<graph[x].size();i++) {
        int child = graph[x][i];
        if (check[child]) continue; // 이미 깊이를 측정했으면 통과
        parent[child] = x;
        dfs(child, d + 1);
    }
}

int lca(int a, int b) {
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
    int n, m;
    vector<int> ans;
    cin >> n;

    memset(parent, 0, sizeof(int) * 50001);
    memset(depth, 0, sizeof(int) * 50001);
    memset(check, false, sizeof(bool) * 50001);
    for(int i=0;i<n - 1;i++) {
        int x, y;
        cin >> x >> y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    dfs(1, 0);
    cin >> m;
    for(int i=0;i<m;i++) {
        int x, y;
        cin >> x >> y;
        int root = lca(x, y);
        ans.push_back(root);
    }
    
    for(int a = 0; a < ans.size(); a++) {
        cout << ans[a] << endl;
    }
}

