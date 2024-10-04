#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <iostream>
#include <map>

using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    unordered_map<string, vector<int>> info;
    unordered_map<string, int> parkingSum;
    
    for(string record: records) {
        int h = stoi(record.substr(0, 2));
        int m = stoi(record.substr(3, 2));
        int time = h * 60 + m;
        
        string carNum = record.substr(6, 4);
        if (info.find(carNum) == info.end()) {
            vector<int> t;
            t.push_back(time);
            info.insert({carNum, t});
        } 
        else {
            info[carNum].push_back(time);
        }
    }
    
    for(pair<string, vector<int>> i: info) {
        if (i.second.size() % 2 != 0) {
            int last = 23 * 60 + 59;
            i.second.push_back(last);
        }
        string carNum = i.first;
        vector<int> timeInfo = i.second;
        int total = 0;
        for(int k=0;k<timeInfo.size(); k++) {
            if (k % 2 == 1) {
                total += timeInfo[k];
            }
            else total -= timeInfo[k];
        }
        parkingSum.insert({carNum, total});
    }
    
    map<string, int> ans;
    int basicT = fees[0], basicFee = fees[1], unitTime = fees[2], unitFee = fees[3];
    for(pair<string, int> t: parkingSum) {
        string carNum = t.first;
        int time = t.second;
        if (time <= basicT) {
            int total = basicFee;
            ans.insert({carNum, total});
        }
        else {
            int remainT = time - basicT;
            int remainFee = (remainT / unitTime) * unitFee;
            if (remainT % unitTime != 0) remainFee = ((remainT / unitTime) + 1) * unitFee;
            int total = basicFee + remainFee;
            ans.insert({carNum, total});
        }
    }
    
    for(pair<string, int> x: ans) {
        answer.push_back(x.second);
    }
    return answer;
}