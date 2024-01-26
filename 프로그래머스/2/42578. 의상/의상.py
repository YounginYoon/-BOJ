def solution(clothes):
    answer = 1
    hashmap = {}
    
    for name, category in clothes:
        if category not in hashmap:
            hashmap[category] = 0
        hashmap[category] += 1
    
    for category in hashmap:
        answer *= (hashmap[category] + 1)

    return answer - 1