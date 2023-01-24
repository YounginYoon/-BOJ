#include <iostream>
#include <cstdlib>
#include <string.h>

#define MAX_N 15

using namespace std;
int n, cnt = 0;
int x_board[MAX_N];

bool ispromising(int y) {
    int i = 0;
    while (i < y) {
        if (x_board[i] == x_board[y]) return false;
        else if (abs(y  - i) == abs(x_board[i] - x_board[y])) return false;
        i++;
    }
    return true;
}

void n_queens(int y) {
    int x = 0;
    if (y == n) {
        cnt++;
        return ;
    }
    while (x < n) {
        x_board[y] = x;
        if (ispromising(y)) n_queens(y + 1);
        x++;
    }
}

int main() {
    cin >> n;

    memset(x_board, 0, sizeof(int) * MAX_N);
    n_queens(0);
    cout << cnt << endl;
}
