#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int parent[1001];
bool remove_edge[10001];

typedef struct _edge {
    int from, to, cost;
} Edge;

bool mycmp(Edge a, Edge b) {
    return a.cost < b.cost;
}

int find_parent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y) {
    int px = find_parent(x), py = find_parent(y);

    if (px < py) parent[py] = px;
    else parent[px] = py;
}

int main() {
    int n, m, k;

    cin >> n >> m >> k;
    vector<Edge> edges;

    for(int i = 0; i < m; i++) {
        int f, t;
        cin >> f >> t;
        edges.push_back({f, t, i + 1});
    }
    vector<int> ans;
    // edges 오름차순 정렬
    sort(edges.begin(), edges.end(), mycmp);
    for(int i=1;i<=n; i++) {
        remove_edge[i] = false;
    }
    while(k) {
        // parent 초기화
        for(int i=1;i<=n; i++) {
            parent[i] = i;
        }
        
        int sum = 0;

        // edges를 돌면서 제거 안한 edge이면 mst 만들기
        for(int i=0; i < edges.size(); i++) {
            if (remove_edge[i] == true) continue;
            if (find_parent(edges[i].from) != find_parent(edges[i].to)) {
                //cout << "union " << edges[i].from << " " << edges[i].to << " " << i << endl;
                union_parent(edges[i].from, edges[i].to);
                sum += edges[i].cost;
            }
            
        }
        
        // // parent 확인
        // for(int i=1;i<=n; i++) {
        //     cout << parent[i] << " ";
        // }
        // cout << endl;

        // mst인지 확인
        bool check = false;
        int target = find_parent(1);
        for(int i=2; i<=n; i++) {
            if(find_parent(i) != target) {
                check = true;
                break;
            }
        }

        if (check == true) {
            for(int i=0; i<k; i++) {
                ans.push_back(0);
            }
            for(int j=0; j<ans.size(); j++) {
                cout << ans[j] << " ";
            }
            cout << endl;
            return 0;
        }
        else {
            ans.push_back(sum);
        }

        // 가중치가 가장 작은 간선 제거
        for(int i=0;i<edges.size(); i++) {
            if (remove_edge[i] == false) {
                remove_edge[i] = true;
                break;
            }
        }
        // cout << "check edges\n";
        // for(int i=0;i<edges.size(); i++) {
        //     cout << remove_edge[i] << " ";
        // }
        // cout << endl;
        k--;
    }

    for(int i=0; i< ans.size(); i++) {
        cout << ans[i] << " ";
    }
}