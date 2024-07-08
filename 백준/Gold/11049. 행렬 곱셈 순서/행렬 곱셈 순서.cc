#include <iostream>
#define MAX_N 501

using namespace std;
int matrix[MAX_N][2];
int dp[MAX_N][MAX_N];

int chained_matrix(int n)
{
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<n-i; j++)
        {
            int a = j;
            int b = j + i;
            if(a==b)
                dp[a][b] = 0;
            else
            {
                dp[a][b] = 2147483647;
                for(int k=a; k<b; k++)
                {
                    dp[a][b] = min(dp[a][b], dp[a][k] + dp[k+1][b] + matrix[a][0] * matrix[k][1] * matrix[b][1]);
                }
            }
        }
    }
    return dp[0][n-1];
}

int main()
{
    int n;
    cin >> n;
    for(int i=0; i<n; i++)
    {
        cin >> matrix[i][0] >> matrix[i][1];
    }

    cout << chained_matrix(n) << endl;
}