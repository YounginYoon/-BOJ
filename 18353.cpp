#include <iostream>
#include <algorithm>

using namespace std;

int *dp;

void solution(int n, int *w) {
    for(int i = 1; i < n; i++) {
        for(int j = 0; j < i; j++) {
            if (w[j] > w[i]) dp[i] = max(dp[i], dp[j] + 1);
        }
    }
    int res = 0;
    for(int i = 0; i < n; i++) {
        res = max(res, dp[i]);
    }

    cout << n - res;
} 

int main() {
    int n, *w;

    cin >> n;

    w = new int[n];
    dp = new int[n];

    for(int i = 0; i < n; i++) {
        cin >> w[i];
        dp[i] = 1;
    }
    solution(n, w);
}