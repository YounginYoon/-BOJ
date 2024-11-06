#include <iostream>
#include <vector>
#include <cstring>
#include <climits>
#include <algorithm>

#define LOG 21
#define MAXN 100002

using namespace std;

int n;
int parent[MAXN][LOG], depth[MAXN];
bool check[MAXN];
vector<pair<int, int>> graph[MAXN];
int minDist[MAXN][LOG], maxDist[MAXN][LOG];

void dfs(int x, int d) {
    check[x] = true;
    depth[x] = d;

    for (int i = 0; i < graph[x].size(); i++) {
        int child = graph[x][i].first;
        int dist = graph[x][i].second;
        if (check[child]) continue;
        parent[child][0] = x;
        minDist[child][0] = dist;
        maxDist[child][0] = dist;
        dfs(child, d + 1);
    }
}

void set_parent() {
    dfs(1, 0);

    for (int i = 1; i < LOG; i++) {
        for (int j = 1; j <= n; j++) {
            if (parent[j][i - 1] != 0) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
                minDist[j][i] = min(minDist[j][i - 1], minDist[parent[j][i - 1]][i - 1]);
                maxDist[j][i] = max(maxDist[j][i - 1], maxDist[parent[j][i - 1]][i - 1]);
            }
        }
    }
}

pair<int, int> lca(int a, int b) {
    int minD = INT_MAX, maxD = INT_MIN;

    if (depth[a] > depth[b]) swap(a, b);

    for (int i = LOG - 1; i >= 0; i--) {
        int diff = depth[b] - depth[a];
        if (diff >= (1 << i)) {
            minD = min(minD, minDist[b][i]);
            maxD = max(maxD, maxDist[b][i]);
            b = parent[b][i];
        }
    }

    if (a == b) return make_pair(minD, maxD);

    for (int i = LOG - 1; i >= 0; i--) {
        if (parent[a][i] != parent[b][i]) {
            minD = min(minD, min(minDist[a][i], minDist[b][i]));
            maxD = max(maxD, max(maxDist[a][i], maxDist[b][i]));
            a = parent[a][i];
            b = parent[b][i];
        }
    }

    minD = min(minD, min(minDist[a][0], minDist[b][0]));
    maxD = max(maxD, max(maxDist[a][0], maxDist[b][0]));
    return make_pair(minD, maxD);
}

int main() {
    vector<pair<int, int>> ans;
    cin >> n;
    memset(depth, 0, sizeof(int) * MAXN);
    memset(check, false, sizeof(bool) * MAXN);

    for (int i = 0; i < MAXN; i++) {
        for (int j = 0; j < LOG; j++) {
            minDist[i][j] = INT_MAX;
            maxDist[i][j] = INT_MIN;
            parent[i][j] = 0;
        }
    }

    for (int i = 0; i < n - 1; i++) {
        int x, y, d;
        cin >> x >> y >> d;
        graph[x].emplace_back(y, d);
        graph[y].emplace_back(x, d);
    }

    set_parent();

    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        pair<int, int> dist = lca(x, y);
        ans.push_back(dist);
    }

    for (const auto &p : ans) {
        cout << p.first << " " << p.second << "\n";
    }

    return 0;
}
