#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n, m;
vector<int> arr;

int searchMin(int target) {
    // 미만 찾기
    int left = 0, right = n - 1;
    int ret = -1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (arr[mid] >= target) right = mid - 1;
        else {
            ret = mid;
            left = mid + 1;
        }

    }
    return ret;
}

int searchMax(int target) {
    // 초과 찾기
    int left = 0, right = n - 1;
    int ret = n;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (arr[mid] <= target) left = mid + 1;
        else {
            ret = mid;
            right = mid - 1;
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    vector<int> ans;

    for(int i = 0; i < n; i++) {
        int x;
        cin >> x;
        arr.push_back(x);
    }
    sort(arr.begin(), arr.end());

    cin >> m;
    for(int i = 0; i < m; i++) {
        int x;
        cin >> x;
        int minIdx = searchMin(x);
        int maxIdx = searchMax(x);
        int cnt = maxIdx - minIdx - 1;
        ans.push_back(cnt);
    }

    for(int i = 0; i < m; i++) {
        cout << ans[i] << " ";
    }
    cout << "\n";
}