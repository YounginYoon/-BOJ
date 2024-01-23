#include <vector>
#include <string>
#include <iostream>
#include <algorithm>

using namespace std;
int n, m;
vector<string> trains;

void orderOne(int t, int x) {
    // t번 기차의 x번째 자리에 사람 탑승
    // 단, 이미 사람이 탑승 -> 명령 실행하지 않음
    if (trains[t][x] == '0') trains[t][x] = '1';
}

void orderTwo(int t, int x) {
    // t번 기차의 x번째 자리에 있는 사람 하차
    // 이미 빈 자리라면 명령어 실행하지 않음
    if (trains[t][x] == '1') trains[t][x] = '0';
}

void orderThree(int t) {
    // t번 기차에 있는 승객들을 -> 방향으로 한 칸씩 이동
    // 20번째에 있는 승객은 하차
    string new_str = "00000000000000000000";
    for(int i = 0; i < 19; i++) {
        new_str[i + 1] = trains[t][i];
    }
    trains[t] = new_str;
}

void orderFour(int t) {
    // t번 기차의 승객들을 <- 방향으로 한 칸씩 이동
    string new_str = "00000000000000000000";
    for(int i = 19; i > 0; i--) {
        new_str[i - 1] = trains[t][i];
    }
    trains[t] = new_str;
}


int main() {
    cin >> n >> m;
    string str = "00000000000000000000"; // 사람 in: 1, 사람 out: 0
    for(int i = 0; i < n; i++) {
        trains.push_back(str);
    }
    for(int i = 0; i < m; i++) {
        int order, t, x;
        cin >> order >> t;
        if (order == 1 || order == 2) cin >> x;
        if (order == 1) orderOne(t - 1, x - 1);
        else if (order == 2) orderTwo(t - 1, x - 1);
        else if (order == 3) orderThree(t - 1);
        else if (order == 4) orderFour(t - 1);
    }
    // 상태 확인
    sort(trains.begin(), trains.end());
    trains.erase(unique(trains.begin(), trains.end()), trains.end());
    //for(int i = 0; i < trains.size(); i++) cout << trains[i] << endl;
    cout << trains.size() << endl;
}