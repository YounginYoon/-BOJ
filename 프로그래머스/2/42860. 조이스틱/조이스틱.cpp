#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

typedef struct _info {
    int move, idx;
    string mystr;
    bool visited[21];
} Info;

int find_min_move(char c) {
    string alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int f_cnt = 0, b_cnt = 1;
    
    for(int i=0;i<alphabet.size(); i++) {
        if (alphabet[i] != c) f_cnt++;
        else break;
    }
    
    for(int i=alphabet.size()-1;i>=0;i--) {
        if (alphabet[i] != c) b_cnt++;
        else break;
    }
    return min(f_cnt, b_cnt);
}

int find_left_right(string name) {
    queue<Info> qq;
    int leng = name.size();
    
    Info first;
    first.move = find_min_move(name[0]);
    first.mystr = "";
    for(int i=0;i<leng;i++) {
        first.mystr += "A";
        first.visited[i] = false;
    }
    first.mystr[0] = name[0];
    first.idx = 0;
    first.visited[0] = true;
    qq.push(first);


    while (!qq.empty()) {
        Info cur = qq.front();
        qq.pop();
        // cout << cur.mystr << endl;
        if (!(cur.mystr.compare(name))) return cur.move;
        // <- 이동
        Info next;
        next.idx = cur.idx - 1;
        if (next.idx < 0) next.idx = leng - 1;
        for(int i=0;i<leng;i++) next.visited[i] = cur.visited[i];
        next.move = cur.move + 1;
        next.mystr = cur.mystr;
        if (!next.visited[next.idx]) {
            next.move += find_min_move(name[next.idx]);
            next.mystr[next.idx] = name[next.idx];
            next.visited[next.idx] = true;
        }
        qq.push(next);
        // -> 이동
        next.idx = cur.idx + 1;
        if (next.idx >= leng) next.idx = 0;
        for(int i=0;i<leng;i++) next.visited[i] = cur.visited[i];
        next.move = cur.move + 1;
        next.mystr = cur.mystr;
        if (!next.visited[next.idx]) {
            next.move += find_min_move(name[next.idx]);
            next.mystr[next.idx] = name[next.idx];
            next.visited[next.idx] = true;
        }
        qq.push(next);
    }
    
    return 0;
}

int solution(string name) {
    int answer = 0;
    
    answer = find_left_right(name);
    
    return answer;
}

// int main() {
//     string inp;
//     cin >> inp;

//     cout << solution(inp) << endl;
// }