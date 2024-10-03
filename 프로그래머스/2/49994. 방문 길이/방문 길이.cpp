#include <string>
#include <unordered_set>
#include <algorithm>
#include <iostream>
using namespace std;

int d[4][2] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

bool isOutOfRange(int r, int c) {
    return (r > 5 || c > 5 || r < -5 || c < -5);
}

int solution(string dirs) {
    int answer = 0;
    vector<int> order;
    unordered_set<string> visited;
    
    for(int i=0;i<dirs.size();i++) {
        if (dirs[i] == 'U') order.push_back(0);
        else if (dirs[i] == 'D') order.push_back(1);
        else if (dirs[i] == 'R') order.push_back(2);
        else order.push_back(3);
    }
    
    int cr = 0, cc = 0;
    
    for(int i: order) {
        int nr = cr + d[i][0], nc = cc + d[i][1];
        if (isOutOfRange(nr, nc)) continue;
        int sr = min(cr, nr), sc = min(cc, nc);
        int er = max(cr, nr), ec = max(cc, nc);
        
        string path = to_string(sr) + " " + to_string(sc) + " " + to_string(er) + " " + to_string(ec);
        if (visited.find(path) == visited.end()) {
            answer++;
            visited.insert(path);
            //cout << "not find " << path << "\n";
        }
        cr = nr, cc = nc;
    }
    return answer;
}