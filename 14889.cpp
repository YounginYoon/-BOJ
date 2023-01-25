#include <iostream>
#include <algorithm>
#define MAX_N 21

using namespace std;
int N, people[MAX_N], score[MAX_N][MAX_N], mymin = 101;
bool visited[MAX_N];

void diff() {
    int start = 0, link = 0, gap;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < i; j++) {
            if(visited[i] && visited[j])
                start += score[i][j] + score[j][i];
            else if(!visited[i] && !visited[j])
                link += score[i][j] + score[j][i];
        }
    }
    gap = abs(start - link);
    mymin = min(mymin, gap);
}

void DFS(int start, int depth) {
    if (depth == N/2) {
        diff();
        return ;
    }
    else {
        for(int i = start + 1; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> N;
    for(int i = 0 ; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> score[i][j];
        }
    }
    DFS(-1, 0);
    cout << mymin << endl;
}