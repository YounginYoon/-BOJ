#include <iostream>
#include <vector>
#include <string.h>
#define LOG 21 // 2^20 = 1,000,000

using namespace std;
vector<int> graph[100002];
int parent[100002][LOG], depth[100002];
bool check[100002];
int n;

void dfs(int x, int d) {
    check[x] = true;
    depth[x] = d;

    for(int i=0;i<graph[x].size(); i++) {
        int child = graph[x][i];
        if (check[child]) continue;
        parent[child][0] = x;
        dfs(child, d + 1);
    }
}

void set_parent() {
    dfs(1, 0);
    for(int i=1;i<LOG;i++) {
        for(int j=1;j<n+1;j++) {
            parent[j][i] = parent[parent[j][i-1]][i-1]; // 내 바로 윗 조상의 조상 -> 2^1칸 위 조상
        }
    }
}

int lca(int a, int b) { // a와 b의 최소 공통 조상 찾기
    // b가 더 깊도록 설정
    if (depth[a] > depth[b]) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    // 깊이가 동일하도록 설정
    for(int i=LOG-1;i>=0;i--) {
        int diff = depth[b] - depth[a];
        if (diff >= (1 << i)) { // 깊이 차이가 2^i만큼 충분히 크다면
            b = parent[b][i]; // 2^i 만큼 줄면서 조상을 가리키도록
        }
    }

    // 부모가 같아지도록
    if (a == b) return a;
    for(int i=LOG-1;i>=0;i--) {
        // 거슬러 올라가면서 같은 조상 찾기
        if (parent[a][i] != parent[b][i]) {
            a = parent[a][i];
            b = parent[b][i];
        }
    }

    return parent[a][0];
}

int main() {
    int m;
    vector<int> ans;
    cin >> n;

    for(int i=0;i<100002;i++) memset(parent[i], 0, sizeof(int) * LOG);
    memset(depth, 0, sizeof(int) * 100002);
    memset(check, false, sizeof(bool) * 100002);
    for(int i=0;i<n-1;i++) {
        int x, y;
        cin >> x >> y;
        graph[x].push_back(y);
        graph[y].push_back(x);
    }

    set_parent();
    cin >> m;
    for(int i=0;i<m;i++) {
        int x, y;
        cin >> x >> y;
        int root = lca(x, y);
        ans.push_back(root);
    }

    for(int i=0;i<ans.size();i++) cout << ans[i] << "\n";
}