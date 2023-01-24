#include <iostream>
#include <algorithm>
#define NUM 101

using namespace std;
int N, mymax = -1000000000, mymin = 1000000000;
int oper[4], operand[NUM];

void calculator(int res, int cnt) {
    if (cnt == N) {
        mymin = min(res, mymin);
        mymax = max(res, mymax);
        return ;
    }
    for(int i = 0; i < 4; i++) {
        if (oper[i] > 0) {
            oper[i]--;
            if (i == 0) calculator(res + operand[cnt], cnt + 1);
            else if (i == 1) calculator(res - operand[cnt], cnt + 1);
            else if (i == 2) calculator(res * operand[cnt], cnt + 1);
            else calculator(res / operand[cnt], cnt + 1);
            oper[i]++;
        }
    }
}

int main() {
    cin >> N;
    for(int i  = 0; i < N; i++) {
        cin >> operand[i];
    }
    for(int i = 0; i < 4; i++)
        cin >> oper[i];
    calculator(operand[0], 1);
    cout << mymax << "\n";
    cout << mymin << "\n";
}