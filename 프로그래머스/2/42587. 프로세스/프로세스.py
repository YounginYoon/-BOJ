def solution(priorities, location):
    answer = 0
    qIdx = [i for i in range(len(priorities))]
    qPro = [i for i in priorities]
    check = [0 for i in range(len(priorities))]
    
    cnt = 1
    while len(qIdx) > 0:
        cIdx = qIdx.pop(0)
        cPro = qPro.pop(0)
        flag = False
        if (len(qIdx) == 0):
            check[cIdx] = cnt
            break
        for x in qPro:
            if cPro < x:
                qIdx.append(cIdx)
                qPro.append(cPro)
                flag = True
                break
        if flag == False:
            check[cIdx] = cnt
            cnt = cnt + 1
    
    answer = check[location]
    return answer