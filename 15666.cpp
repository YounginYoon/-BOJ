#include <iostream>
#include <algorithm>
#define MAX_D 9

using namespace std;

int n, m, inp[MAX_D], out[MAX_D];

void getResult(int depth, int start) {
    if(depth == m) {
        for(int i = 0; i < m; i++)
            cout << out[i] << ' ';
        cout << "\n";
        return ;
    }
    int prev = -1;
    for(int i = start; i < n; i++) {
        if(prev != inp[i]) {
            out[depth] = inp[i];
            prev = inp[i];
            getResult(depth + 1, i);
        }
    }
}

int main() {
    cin >> n >> m;
    for(int i = 0; i < n; i++)
        cin >> inp[i];
    sort(inp, inp + n);
    getResult(0, 0);
}