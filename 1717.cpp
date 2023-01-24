#include <iostream>

using namespace std;

int *rank_h; // union by rank == path compression

void initTree(int *p, int n)
{
    for(int i=0; i<= n; i++)
    {
        p[i] = i;
        rank_h[i] = 1;
    }
}

//특정 노드의 부모를 찾음
int getParent(int *p, int x)
{
    if (p[x] == x || p[x] == -1) // 최종적으로 부모 노드는 자기 자신을 가리키고 있음
        return x;
    return p[x] = getParent(p, p[x]);

}

//두 트리를 합침
void unionParent(int *p, int a, int b)
{
    a = getParent(p, a);
    b = getParent(p, b);
    if (a > b)
        p[a] = b;
    else if (a < b)
        p[b] = a;
    else {
        ++rank_h[a];
        return ;
    }
}

// 두 부모가 같은지 확인
int findParent(int *p, int a, int b)
{
    a = getParent(p, a);
    b = getParent(p, b);
    if (a == b)
        return 1;
    return 0;
}

int main()
{
    int n, m;

    cin >> n >> m;
    int *parent = new int [n + 2];
    rank_h = new int [n + 2];

    initTree(parent, n);
    while (m--)
    {
        cin.tie(0);
	    cin.sync_with_stdio(false);
	    ios_base::sync_with_stdio(false);
        int x, y, z;
        cin >> x >> y >> z;
        if (x == 0)
            unionParent(parent, y, z);
        else if (x == 1)
        {
            if (findParent(parent, y, z))
                cout << "YES\n";
            else cout << "NO\n";
        }
    }
    //delete [] parent;
    //delete [] rank_h;
    return 0;
}