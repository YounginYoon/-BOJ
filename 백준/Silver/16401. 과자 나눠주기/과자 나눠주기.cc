#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n, m;
vector<int> snacks;

int binarySearch(int maxSnack) {
    int left = 1, right = maxSnack;
    int ret = 0;

    while (left <= right) {
        int mid = (left + right) / 2;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            cnt += (snacks[i] / mid);
        }
        if (cnt >= m) {
            ret = mid;
            left = mid + 1;
        }
        else {
            right = mid - 1;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> m >> n;
    int maxSnack = 0;
    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;
        snacks.push_back(x);
        maxSnack = max(maxSnack, x);
    }
    cout << binarySearch(maxSnack);
}