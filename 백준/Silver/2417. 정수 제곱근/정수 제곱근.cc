#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int main() {
    long long n;
    cin >> n;
    long long start = 0, end = sqrt(n);

    while (start <= end) {
        long long mid = (start + end) / 2;
        long long square = mid * mid;
        if (square >= n) {
            end = mid - 1;
        }
        else {
            start = mid + 1;
        }
    }
    cout << start << endl;
}