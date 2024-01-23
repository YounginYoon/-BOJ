#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(int a, int b) {
    return a > b;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    vector<int> A, B;
    int n, ans = 0;
    cin >> n;
    
    for(int i=0;i<n;i++) {
        int x;
        cin >> x;
        A.push_back(x);
    }
    for(int i=0;i<n;i++) {
        int x;
        cin >> x;
        B.push_back(x);
    }
    sort(A.begin(), A.end());
    sort(B.begin(), B.end(), compare);
    for(int i=0;i<n;i++) {
        ans += A[i] * B[i];
    }
    cout << ans << endl;
}