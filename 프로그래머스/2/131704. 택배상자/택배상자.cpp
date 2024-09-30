#include <string>
#include <vector>
#include <queue>
#include <stack>
#include <iostream>

using namespace std;

int solution(vector<int> order) {
    int answer = 0;
    stack<int> st;
    queue<int> q;
    
    for(int i = 1; i <= order.size(); i++) q.push(i);
    int cnt = 0;
    for(int i = 0; i < order.size(); i++) {
        bool flag = false;
        if (!st.empty()) {
            if (order[i] == st.top()) {
                st.pop();
                flag = true;
                cnt++;
            }
        }    
        if (!flag) {
            while (!q.empty()) {
                int front = q.front();
                q.pop();
                if (front == order[i]) {
                    flag = true;
                    cnt++;
                    break;
                }
                else {
                    st.push(front);
                }                 
            }
        }
        if (!flag) break;
    }
    answer = cnt;
    return answer;
}