#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
 
int N, M, ans;
const int max_n = 51;
int map[max_n][max_n];
 
int dir[5][2]={{0,0},{-1,0},{1,0},{0,-1},{0,1}};
vector<pair<int,int>> position;
vector<pair<int,int>> magic;
 
void make_position(int n){
    int mk_poistion_dir[4][2]={{0,-1},{1,0},{0,1},{-1,0}};
    int d=1;
    int idx=0;
    int x=(n+1)/2;
    int y=(n+1)/2;
 
    //상어 위치 입력
    position.push_back({x,y});
    //다음 위치 이동 후 방향 전환
    x+=mk_poistion_dir[idx][0];
    y+=mk_poistion_dir[idx][1];
    idx++;
 
    //1칸 짜리 입력
    position.push_back({x,y});
    //다음 위치 이동후 방향 전환 + 길이 증가
    x+=mk_poistion_dir[idx][0];
    y+=mk_poistion_dir[idx][1];
    idx++;
    d++;
 
    //이후 같은 길이로 두번씩 반복된다.
    while(d<n){
        for(int i=0;i<d;i++){
            int nx = x + mk_poistion_dir[idx][0]*i;
            int ny = y + mk_poistion_dir[idx][1]*i;
            position.push_back({nx,ny});
            if(i==d-1) {
                x=nx+mk_poistion_dir[idx][0]; 
                y=ny+mk_poistion_dir[idx][1];
            }
        }
 
        idx=(idx+1)%4;
 
        for(int i=0;i<d;i++){
            int nx = x + mk_poistion_dir[idx][0]*i;
            int ny = y + mk_poistion_dir[idx][1]*i;
            position.push_back({nx,ny});
            if(i==d-1) {
                x=nx+mk_poistion_dir[idx][0]; 
                y=ny+mk_poistion_dir[idx][1];
            }
        }
 
        idx=(idx+1)%4;
        d++;
    }
 
    //마지막 부분은 한번 수행된다.
    for(int i=0;i<d;i++){
        int nx = x + mk_poistion_dir[idx][0]*i;
        int ny = y + mk_poistion_dir[idx][1]*i;
        position.push_back({nx,ny});
        if(i==d-1) {
            x=nx+mk_poistion_dir[idx][0]; 
            y=ny+mk_poistion_dir[idx][1];
        }
    }
 
}
 
void Input(){
    cin >> N >> M;
    for(int i=1;i<=N;i++){
        for(int j=1;j<=N;j++){
            cin >> map[i][j];
        }
    }
    make_position(N);
    int d,s;
    for(int i=0;i<M;i++){
        cin >> d >> s;
        magic.push_back({d,s});
    }
}
 
 
void blizzard(int d, int s){
    int x=position[0].first;
    int y=position[0].second;
    for(int i=1;i<=s;i++){
        x+=dir[d][0];
        y+=dir[d][1];
        map[x][y]=0;
    }
}
 
void reload(){
    vector<int> values;
    for(int i=0;i<position.size();i++){
        int x=position[i].first;
        int y=position[i].second;
        if(map[x][y]==0) continue;
        values.push_back(map[x][y]);
    }
 
    memset(map,0,sizeof(map));
 
    for(int i=0;i<values.size();i++){
        int x=position[i+1].first;
        int y=position[i+1].second;
        map[x][y]=values[i];
    }
}
 
 
bool explosion(){
    bool flag = false;
    vector<pair<int,int>> del_list;
    int del_v = 0;
    del_list.clear();
    for(int i=1;i<position.size();i++){
        int x=position[i].first;
        int y=position[i].second;
        //비어있으면 그냥 넣는다
        if(del_list.empty()){
            del_v = map[x][y];
            del_list.push_back({x,y});
        }
        //비어있지 않으면 들어있는 값과 비교
        else{
            //일치하면 넣는다.
            if(del_v == map[x][y]){
                del_list.push_back({x,y});
            }
            //일치하지 않으면 새로운 벡터로 만들어야한다.
            //지금까지 저장된 수를 확인해서 처리한다.
            else{
                if(del_list.size()>=4){
                    for(int j=0;j<del_list.size();j++){
                        int nx=del_list[j].first;
                        int ny=del_list[j].second;
                        map[nx][ny]=0;
                    }
                    ans += del_list.size() * del_v;
                    flag = true;
                }
 
                del_list.clear();
                del_v= map[x][y];
                del_list.push_back({x,y});
            }
        }
    }
    return flag;
}
 
void change(){
    vector<int> temp;
    int v = map[position[1].first][position[1].second];
    int cnt = 1;
    for(int i=2;i<position.size();i++){
        int x=position[i].first;
        int y=position[i].second;
        if(map[x][y]==v){
            cnt++;
        }
        else{
            temp.push_back(cnt);
            temp.push_back(v);
            v = map[x][y];
            cnt=1;
        }
    }
    memset(map,0,sizeof(map));
    for(int i=0;i<temp.size();i++){
        //범위 조심. 그 이상 담긴 것은 버린다.
        if(i+1>=N*N) break;
        int x=position[i+1].first;
        int y=position[i+1].second;
        map[x][y]=temp[i];
    }
}
 
void solve(){
    for(int i=0;i<M;i++){
        int d = magic[i].first;
        int s = magic[i].second;
        blizzard(d,s);
        reload();
        while(true){
            if(!explosion()) break;
            reload();
        }
        change();
    }
 
    cout << ans;
}
 
int main(){
    Input();
    solve();
    return 0;
}