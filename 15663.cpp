#include <iostream>
#include <algorithm>
#define MAX_D 9

using namespace std;
bool visited[MAX_D];
int n, m, ans[MAX_D], arr[MAX_D];

void DFS(int cnt) {
    if (cnt == m) {
        for(int i = 0 ; i < m ; i++)
            cout << ans[i] << ' ';
        cout << "\n";
        return ;
    }
    bool used[10002] = {false, };
    for(int i = 0; i < n; i++) {
        if(!used[arr[i]] && !visited[i]) {
            visited[i] = true;
            used[arr[i]] = true;
            ans[cnt] = arr[i];
            DFS(cnt + 1);
            visited[i] = false;
        }
    }
}

int main() {
    cin >> n >> m;
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    sort(arr, arr + n);
    DFS(0);
}