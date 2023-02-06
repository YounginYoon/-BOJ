#include <iostream>
#include <algorithm>
#include <vector>
#define MAX_NUM 51

using namespace std;
int N, M;

struct Chicken {
    int row, col;
    bool visited;
};

struct Home {
    int row, col;
};

vector<Chicken> Chickens;
vector<Home> Homes;
int DIST_MIN = INT_MAX, map[MAX_NUM][MAX_NUM];

void calculate_Dist() {
    int dist, sum = 0;

    for(int j = 0; j < Homes.size(); j++) {
        int min_dist = INT_MAX;
        for(int i = 0; i < Chickens.size(); i++) {
            if (Chickens[i].visited) {
                dist = abs(Homes[j].row - Chickens[i].row) + abs(Homes[j].col - Chickens[i].col);
                min_dist = min(min_dist, dist);
            }
        }
        sum += min_dist;
    }
    DIST_MIN = min(DIST_MIN, sum);
}

void pickChickens(int cnt, int idx) {
    if (cnt == M) {
        calculate_Dist();
        return ;
    }
    else {
        for(int i = idx; i < Chickens.size(); i++) {
            if(!Chickens[i].visited) {
                Chickens[i].visited = true;
                pickChickens(cnt + 1, i + 1);
                Chickens[i].visited = false;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;
    
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> map[i][j];
            if (map[i][j] == 1) {
                struct Home tmp;
                tmp.row = i;
                tmp.col = j;
                Homes.push_back(tmp);
            }
            else if (map[i][j] == 2) {
                struct Chicken tmp;
                tmp.row = i;
                tmp.col = j;
                tmp.visited = false;
                Chickens.push_back(tmp);
            }
        }
    }
    pickChickens(0, 0);
    cout << DIST_MIN << endl;
}