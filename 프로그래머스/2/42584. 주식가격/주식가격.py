from collections import deque
def solution(prices):
    answer = []
    prices = deque(prices)
    
    while prices:
        cur = prices.popleft()
        count = 0
        for i in prices:
            if cur > i:
                count += 1
                break
            count += 1
        answer.append(count)
    return answer