#include <iostream>
#include <algorithm>
#define MAX_D 8

using namespace std;

int n, m;
int arr[MAX_D], ans[MAX_D];

void DFS(int depth) {
    if (depth == m) {
        for(int i = 0; i < m; i++)
            cout << ans[i] << ' ';
        cout << "\n";
        return ;
    }
    bool used[10002] = {false, };
    for(int i = 0; i < n; i++) {
        if(!used[arr[i]]) {
            ans[depth] = arr[i];
            used[arr[i]] = true;
            DFS(depth + 1);
        }
    }
}

int main() {
    cin >> n >> m;
    for(int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    sort(arr, arr + n);
    DFS(0);
}