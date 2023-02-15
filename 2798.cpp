#include <iostream>
#include <algorithm>
#define MAX_N 101

using namespace std;
int MAX_SUM = 0;

int main() {
  int N, M, sum = 0;
  int num[MAX_N];

  cin >> N >> M;
  for(int i = 0; i < N; i++) {
    cin >> num[i];
  }

  for(int i = 0; i < N; i++) {
    sum = 0;
    for(int j = i + 1; j < N; j++) {
      for(int k = j + 1; k < N; k++) {
        sum = num[i] + num[j] + num[k];
        if(sum > M) continue;
        else {
          MAX_SUM = max(MAX_SUM, sum);
        }
      }
    }
  }

  cout << MAX_SUM << endl;
}