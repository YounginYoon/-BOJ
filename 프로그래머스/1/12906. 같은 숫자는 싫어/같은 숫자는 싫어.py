def solution(arr):
    answer = []
    answer.append(arr[0])
    for i in range(len(arr)):
        top = answer[-1]
        if top == arr[i]:
            continue
        answer.append(arr[i])
    return answer