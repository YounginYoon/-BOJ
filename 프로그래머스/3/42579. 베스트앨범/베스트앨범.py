from functools import reduce

def solution(genres, plays):
    answer = []
    table = {}
    
    idx = 0
    for g, p in zip(genres, plays):
        if g not in table:
            table[g] = []
        table[g].append((p, idx))
        idx += 1
    
    # 속한 노래의 재생횟수 구해서 total 값으로 key값 변경
    newTable = {}
    for g in table:
        total = sum(tuple[0] for tuple in table[g])
        newTable[total] = table[g]
    
    newTable = dict(sorted(
        newTable.items(),
        reverse = True
    ))

    # 재생 횟수 기준 내림차순 정렬
    for g in newTable:
        tmp = sorted(
            newTable[g],
            key = lambda item: (-item[0], item[1])
        )
        newTable[g] = tmp

    for g, item in newTable.items():
        answer.append(item[0][1])
        if len(item) > 1:
            answer.append(item[1][1])
            
    return answer