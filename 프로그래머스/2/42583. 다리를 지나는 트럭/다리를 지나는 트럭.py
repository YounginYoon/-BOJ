from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 0
    time = 0
    bridge = deque([0] * bridge_length)
    truck_weights = deque(truck_weights)
    
    curWeight = 0
    while len(truck_weights) > 0:
        time += 1
        curWeight -= bridge.popleft()
        
        if curWeight + truck_weights[0] <= weight:
            curWeight += truck_weights[0]
            bridge.append(truck_weights.popleft())
        else:
            bridge.append(0)
    time += bridge_length # 마지막 트럭
    answer = time
    return answer