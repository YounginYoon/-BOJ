def solution(s):
    answer = False
    stack = []
    cnt = 0
    
    for i in range(len(s)):
        if i == 0:
            if s[i] == '(':
                stack.append(s[i])
                continue
            else:
                continue
        if s[i] == '(':
            stack.append(s[i])
        else:
            if len(stack) <= 0:
                continue
            else:
                top = stack[-1]
                if top != s[i]:
                    stack.pop()
                    cnt = cnt + 1   
    if cnt > 0 and cnt == (len(s) // 2) and (len(s) % 2 == 0):
        answer =  True
    else:
        answer = False
    return answer