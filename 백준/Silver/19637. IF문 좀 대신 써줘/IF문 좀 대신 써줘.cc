#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vector<int> vNum;
    int n, m;
    cin >> n >> m;
    vector<string> v;
    
    for(int i = 0; i < n; i++) {
        string s;
        int x;
        cin >> s >> x;
        v.push_back(s);
        vNum.push_back(x);
    }

    for(int i = 0; i < m; i++) {
        int x;
        cin >> x;
        int fIdx = lower_bound(vNum.begin(), vNum.end(), x) - vNum.begin();
        cout << v[fIdx] << "\n";
    }
    
}