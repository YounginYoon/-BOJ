from sys import stdin

def check():
    # i번째 줄이 i번째 열에 도착하는지 확인
    for i in range(n):
        now = i
        for j in range(h):
            if board[j][now]: # 가로선이 오른쪽에 존재하면
                # 오른쪽으로 이동
                now += 1
            elif now > 0 and board[j][now - 1]: # 가로선이 왼쪽에 존재하면
                # 왼쪽으로 이동
                now -= 1
        if now != i:
            return False
    return True

def dfs(cnt, x, y):
    global ans
    if check(): # 길이 조건에 맞으면 종료
        ans = min(ans, cnt)
        return
    elif cnt == 3 or ans <= cnt: # 3을 넘거나 최솟값을 넘는 경우라면 종료
        return
    
    for i in range(x, h):
        if i == x: # 행이 변경되지 않았으면 이어서 탐색
            now = y
        else:
            # 행이 변경됐으면 0열부터 탐색
            now = 0

        for j in range(now, n - 1):
            if not board[i][j] and not board[i][j + 1]:
                # 오른쪽에 사다리가 없을 때
                if j > 0 and board[i][j - 1]:
                    #왼쪽에 사다리가 있으면 넘어감
                    continue
                board[i][j] = True
                dfs(cnt + 1, i, j + 2)
                board[i][j] = False


n, m, h = map(int, stdin.readline().split())
board = [[False] * n for _ in range(h)]

for _ in range(m):
    a, b = map(int, stdin.readline().split())
    board[a-1][b-1] = True # 사다리 놓기

ans = 4
dfs(0, 0, 0)
print(ans if ans < 4 else -1)
