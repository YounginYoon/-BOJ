from sys import stdin
# strip으로 개행문자 제거
target = stdin.readline().strip()

visit = [False] * len(target)
ans = []
def dfs(left, right):
    minChar = 'a'
    minIdx = 987654321
    for i in range(left, right + 1):
        if not visit[i] and target[i] < minChar:
            minChar = target[i]
            minIdx = i
    if minChar == 'a':
        return
    visit[minIdx] = True

    tmp = ''
    for i in range(len(target)):
        if visit[i]:
            tmp += target[i]
    
    if tmp != '':
        ans.append(tmp)
            
    # 오른쪽 탐색
    dfs(minIdx + 1, right)
    dfs(left, minIdx - 1)

dfs(0, len(target) - 1)

for a in ans:
    print(a)