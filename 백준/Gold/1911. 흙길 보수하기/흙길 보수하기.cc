#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n, l;
vector<pair<int, int> > water;

bool compare(pair<int, int> a, pair<int, int> b) {
    if (a.first == b.first) return a.second < b.second;
    return a.first < b.first;
}

int solution() {
    int cur = water[0].first;
    int cnt = 0;
    for(int i = 0; i < n; i++) {
        //cout << cur << endl;
        int start = water[i].first, end = water[i].second;
        if (start <= cur && cur <= end) {
            int len = end - cur;
            int q = len / l;
            int r = len % l;
            cur += l * q;
            cnt += q;
            if (r > 0) {
                cur += l;
                cnt++;
            }
        }
        if (cur < water[i + 1].first) cur = water[i + 1].first;
        else continue;
    }
    return cnt;
}

int main() {
    cin >> n >> l;
    for(int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        water.push_back({x, y});
    }
    sort(water.begin(), water.end(), compare);
    int ans = solution();
    cout << ans << endl;
}