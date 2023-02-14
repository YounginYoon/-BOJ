#include <iostream>
#include <queue>

using namespace std;

int main() {
  int N, num, sum = 0;
  priority_queue<int, vector<int>, greater<>> pq;
  cin >> N;
  for(int i = 0; i < N; i++) {
    cin >> num;
    pq.push(num);
  }
  while(pq.size() > 1) {
    int tmp1 = pq.top();
    pq.pop();
    int tmp2 = pq.top();
    pq.pop();
    int tmp = tmp1 + tmp2;
    sum += tmp;
    pq.push(tmp);
  }
  cout << sum;
}