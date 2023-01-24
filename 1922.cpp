#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void initParent(int *p, int n)
{
    for(int i=0; i<=n; i++)
        p[i] = i;
}

int getParent(int *p, int a)
{
    if (p[a] == a)
        return a;
    return p[a] = getParent(p, p[a]);
}

void unionParent(int *p, int a, int b)
{
    a = getParent(p, a);
    b = getParent(p, b);
    if (a > b)
        p[a] = b;
    else p[b] = a;
}

int findParent(int *p, int a, int b)
{
    a = getParent(p, a);
    b = getParent(p, b);
    if (a == b)
        return 1;
    else return 0;
}

class Edge {
    public:
        int node[2];
        int distance;
    Edge(int a, int b, int distance)
    {
        this->node[0] = a;
        this->node[1] = b;
        this->distance = distance;
    }
    bool operator <(Edge &edge)
    {
        return this->distance < edge.distance;
    }
};

int main()
{
    cin.tie(0);
	cin.sync_with_stdio(false);
	ios_base::sync_with_stdio(false);
    
    int n, m;
    vector<Edge> v;

    cin >> n >> m;
    int *set = new int [n + 1];
    initParent(set, n);

    while (m--)
    {
        int a, b, distance;
        cin >> a >> b >> distance;
        v.push_back(Edge(a, b, distance));
    }
    sort(v.begin(), v.end());

    int sum = 0;
    for(int i=0; i< v.size(); i++)
    {
        if(!findParent(set, v[i].node[0], v[i].node[1]))
        {
            sum += v[i].distance;
            unionParent(set, v[i].node[0], v[i].node[1]);
        }
    }

    delete [] set;
    cout << sum << endl;
}