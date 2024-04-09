#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>

using namespace std;

bool mycmp(int a, int b) {
    return a > b;
}

int main() {
    int n;
    vector<int> trees;

    cin >> n;
    for(int i=0; i<n; i++) {
        int x;
        cin >> x;
        trees.push_back(x);
    }

    sort(trees.begin(), trees.end(), mycmp);
    vector<pair<int, int>> couple_tree;
    int i = 0;
    while ((i < n) && (i + 1 < n)) {
        if (abs(trees[i] - trees[i + 1]) <= 1) {
            couple_tree.push_back(make_pair(trees[i], trees[i + 1]));
            i += 2;
        }
        else i += 1;
    }
    reverse(couple_tree.begin(), couple_tree.end());
    long long int answer = 0;

    while (couple_tree.size() > 1) {
        pair<int, int> row_object = couple_tree.back();
        couple_tree.pop_back();
        
        // cout << "row " << row_object.first << row_object.second << endl;
        pair<int, int> col_object = couple_tree.back();
        couple_tree.pop_back();
        // cout << "row " << col_object.first << col_object.second << endl;
        int row = min(row_object.first, row_object.second);
        int col = min(col_object.first, col_object.second);
        
        answer += ((long long) row * (long long) col);
    }

    cout << answer << endl;
}