def solution(array, commands):
    answer = []
    for c in commands:
        i = c[0]
        j = c[1]
        k = c[2]
        tmp = array[i - 1:j]
        tmp.sort()
        answer.append(tmp[k - 1])
    return answer