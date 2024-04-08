#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int parent[250000];

void init_parent() {
    for(int p=0; p<250000; p++) {
            parent[p] = p;
    }
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
    vector<int> ans;

    cin >> t;
    for(int i=0;i<t;i++){
        int r, c;
        vector<vector<int>> edge;

        cin >> r >> c;
        init_parent();
        for(int row=0; row<r; row++) {
            for(int col=0; col<c-1;col++) {
                int start, end, cost;
                cin >> cost;

                start = row * c + col;
                end = start + 1;
                vector<int> tmp;
                tmp.push_back(cost);
                tmp.push_back(start);
                tmp.push_back(end);
                edge.push_back(tmp);
            }
        }

        for(int row=0;row<r-1;row++) {
            for(int col=0; col<c; col++) {
                int start, end, cost;
                cin >> cost;

                start = row * c + col;
                end = start + c;
                vector<int> tmp;
                tmp.push_back(cost);
                tmp.push_back(start);
                tmp.push_back(end);
                edge.push_back(tmp);
            }
        }
        sort(edge.begin(), edge.end());
        int answer = 0;
        for (int e = 0; e < edge.size(); e++) {
            int cost = edge[e][0], start = edge[e][1], end = edge[e][2];

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