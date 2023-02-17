#include <iostream>
#include <algorithm>
#define MAX_N 16

using namespace std;
char in[MAX_N], out[MAX_N];
int visited[MAX_N];
int L, C;

void solution(int depth, int start) {
  int v = 0, c = 0;
  if(depth == L) {
    for(int i = 0; i < L; i++) {
      if(out[i] == 'a' || out[i] == 'e' || out[i] == 'i' || out[i] == 'o' || out[i] == 'u') {
        v++;
      }
      else c++;
      if(v >= 1 && c >= 2) {
        sort(out, out + L);
        for(int i = 0; i < L; i++)
          cout << out[i];
        cout << "\n";
        return;
      }
      else continue;
    }
    return ;
  }
  else {
    for(int i = start; i < C; i++) {
      if(!visited[i]) {
        visited[i] = true;
        out[depth] = in[i];
        solution(depth + 1, i + 1);
        visited[i] = false;
      }
    }
  }
}

int main() {
  cin >> L >> C;
  for(int i = 0; i < C; i++)
    cin >> in[i];
  sort(in, in + C);
  solution(0, 0);
}