#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef struct _info {
    int start, end, cost;
} Info;

int parent[250000];
vector<int> ans;
vector<Info> edge;
int answer = 0;
int r, c;

void init() {
    for(int p=0; p < (r*c); p++) {
            parent[p] = p;
    }
    answer = 0;
    edge.clear();
}

bool compare(Info a, Info b) {
    return a.cost < b.cost;
}

int find_parent(int x) {
    if (x == parent[x]) {
        return x;
    }
    return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y) {
    int px = find_parent(x), py = find_parent(y);

    if (px < py) parent[py] = px;
    else parent[px] = py;
}

int main() {
    int t;

    cin >> t;
    for(int i=0;i<t;i++){
        cin >> r >> c;
        init();
        for(int row=0; row<r; row++) {
            for(int col=0; col<c-1;col++) {
                int start, end, cost;
                cin >> cost;

                start = row * c + col;
                end = start + 1;
                edge.push_back({start, end, cost});
            }
        }

        for(int row=0;row<r-1;row++) {
            for(int col=0; col<c; col++) {
                int start, end, cost;
                cin >> cost;

                start = row * c + col;
                end = start + c;
                edge.push_back({start, end, cost});
            }
        }
        sort(edge.begin(), edge.end(), compare);
        for (int e = 0; e < edge.size(); e++) {
            int cost = edge[e].cost, start = edge[e].start, end = edge[e].end;

            if (find_parent(start) != find_parent(end)) {
                union_parent(start, end);
                answer += cost;
            }
        }
        ans.push_back(answer);
    }

    for(int i = 0; i < t; i++) {
        cout << ans[i] << endl;
    }
}