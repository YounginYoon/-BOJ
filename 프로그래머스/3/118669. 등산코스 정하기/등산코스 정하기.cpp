#include <string>
#include <string.h>
#include <vector>
#include <queue>
#include <algorithm>
#include <climits>
#include <iostream>

using namespace std;

#define INT_MAX 2000000000
vector<int> dijkstra(int n, vector<pair<int, int>> path[], vector<int> gates, vector<int> summits) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    long long dist[n + 1];
    bool isSummit[n + 1], isGate[n + 1];
    
    fill(dist, dist + n + 1, INT_MAX);
    fill(isSummit, isSummit + n + 1, false);
    fill(isGate, isGate + n + 1, false);
    for(int i=0;i<summits.size();i++) {
        isSummit[summits[i]] = true;
    }
        
    
    for(int gate: gates) {
        isGate[gate] = true;
        dist[gate] = 0;
        pq.push({0, gate});
    }
    
    while (!pq.empty()) { // gate -> summit 까지의 최단 경로만 찾으면 summit -> gate도 동일한 경로로 가면 됨
        pair<int, int> top = pq.top(); pq.pop();
        int curNode = top.second;
        int curDist = top.first;
        //cout << curNode << " " << curDist << " " << dist[curNode] << "\n";
        if (curDist > dist[curNode]) continue;
        if (isSummit[curNode]) continue;
        for(auto next: path[curNode]) {
            int nextNode = next.first;
            int nextDist = max(next.second, curDist);
            if (isGate[nextNode]) continue;
            if (nextDist < dist[nextNode]) {
                dist[nextNode] = nextDist;
                pq.push({nextDist, nextNode});
                //cout << "push " << curNode << "-> " << nextNode << " " << nextDist << "\n";
            }
        }
    }
    vector<int> answer = {-1, INT_MAX};
    sort(summits.begin(), summits.end());
    
    for(int summit: summits) {
        if (dist[summit] < answer[1]) {
            answer[1] = dist[summit];
            answer[0] = summit;
        }
    }
    return answer;
}

vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    vector<int> answer;
    vector<pair<int, int>> pathInfo[n + 1];
    
    for(auto path: paths) {
        int i = path[0], j = path[1], w = path[2];
        pathInfo[i].push_back({j, w});
        pathInfo[j].push_back({i, w});
    }
    
    answer = dijkstra(n, pathInfo, gates, summits);
    
    return answer;
}