def solution(progresses, speeds):
    answer = []
    remain = []
    for i in range(len(progresses)):
        rem = (100 - progresses[i]) // speeds[i]
        if (100 - progresses[i]) % speeds[i] > 0:
            rem = rem + 1
        remain.append(rem)

    while len(remain) > 0:
        tar = remain[0]
        cnt = 0
        while len(remain) > 0 and tar >= remain[0]:
            cnt = cnt + 1
            remain.pop(0)
        answer.append(cnt)
    
    
    return answer