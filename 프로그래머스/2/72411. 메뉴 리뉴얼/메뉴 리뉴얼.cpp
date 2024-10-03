#include <string>
#include <vector>
#include <string.h>
#include <queue>
#include <algorithm>
#include <iostream>
#include <unordered_map>

using namespace std;

struct Node {
    string order;
    int cnt;
    int nextIdx;
    bool visited[26];
};

struct AnsNode {
    string menu;
    int cnt;
};

bool alphabet[26];

bool sortForAnsNode(AnsNode a, AnsNode b) {
    return a.cnt > b.cnt;    
}

vector<string> bfs(vector<int> course) {
    vector<string> ret;

    for(int cnt: course) {
        queue<Node> q;
        Node firstNode;
        
        firstNode.order = "";
        firstNode.cnt = 0;
        firstNode.nextIdx = 0;
        memset(firstNode.visited, false, sizeof(bool) * 26);
        q.push(firstNode);
        while (!q.empty()) {
            Node cur = q.front(); q.pop();
            
            if (cur.cnt == cnt) {
                ret.push_back(cur.order);
                continue;
            }
            
            for(int cIdx = cur.nextIdx; cIdx < 26; cIdx++) {
                if (alphabet[cIdx] && !cur.visited[cIdx]) {
                    Node newNode;
                    newNode.order = cur.order + (char) (cIdx + 'A');
                    newNode.cnt = cur.cnt + 1;
                    copy(cur.visited, cur.visited + 26, newNode.visited);
                    newNode.visited[cIdx] = true;
                    newNode.nextIdx = cIdx + 1;
                    q.push(newNode);
                }
            }
        }
    }
    
    return ret;
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    memset(alphabet, false, sizeof(bool) * 26);
    for(string order: orders) {
        for(int i=0;i<order.size(); i++) {
            int c = order[i] - 'A';
            alphabet[c] = true;
        }
    }
    
    vector<string> menus = bfs(course);
    vector<AnsNode> tmp[11];
    
    for(string menu: menus) {
        int cnt = 0;
        for(string order: orders) {
            bool check[26];
            memset(check, false, sizeof(bool) * 26);
            for(char c: order) check[c - 'A'] = true;
            bool flag = false;
            for(char c: menu) {
                if (!check[c - 'A']) {
                    flag = true;
                    break;
                }
            }
            if (!flag) cnt++;
        }
        if (cnt >= 2) {
            tmp[menu.size()].push_back({menu, cnt});
        }
    }
    
    for (int i : course) {
        if (!tmp[i].empty()) {
            sort(tmp[i].begin(), tmp[i].end(), sortForAnsNode);
            int maxCnt = tmp[i][0].cnt;
            for (int j = 0; j < tmp[i].size(); j++) {
                if (tmp[i][j].cnt == maxCnt) {
                    answer.push_back(tmp[i][j].menu);
                } else {
                    break;
                }
            }
        }
    }

    
    sort(answer.begin(), answer.end());
    return answer;
}
