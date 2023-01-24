#include <iostream>
#include <vector>
#include <string.h>

#define MAX_N 102
#define MAX_K 100002

using namespace std;
int dp[MAX_N][MAX_K];
int w[MAX_N] = {0, };
int v[MAX_N] = {0, };
int n, k;

int Knapsack_Problem() {
    for(int j = 1; j <= k; j++) {
        for(int i = 1; i <= n; i++) {
            if(w[i] > j) {
                dp[i][j] = dp[i - 1][j];
            }
            else {
                dp[i][j] = max(dp[i - 1][j - w[i]] + v[i], dp[i - 1][j]);
            }
        }
    }
    return dp[n][k];
}

int main() {
    int weight, value;

    cin >> n >> k;
    for(int i = 1; i <= n; i++) {
        cin >> w[i] >> v[i];
    }
    
    for(int i = 0; i <= n; i++)
        dp[i][0] = 0;
    
    for(int j = 0; j <= k; j++)
        dp[0][j] = 0;
    
    cout << Knapsack_Problem() << endl;

}