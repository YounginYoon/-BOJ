from collections import deque

def outOfRange(r, c, n, m):
    if r < 0 or c < 0 or r >= n or c >= m:
        return True
    return False
    
def bfs(maps):
    sr = 0
    sc = 0
    er = len(maps) - 1
    ec = len(maps[0]) - 1
    n = er + 1
    m = ec + 1
    dir = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    
    q = deque([])
    visit = [[0 for j in range(m)] for i in range(n)]
    q.append([sr, sc, 1])
    visit[sr][sc] = 1
    
    while q:
        cur = q.popleft()
        cr = cur[0]
        cc = cur[1]
        cmove = cur[2]
        
        if cr == er and cc == ec:
            return cmove
        for d in dir:
            nr = cr + d[0]
            nc = cc + d[1]
            
            if outOfRange(nr, nc, n, m):
                continue
            if visit[nr][nc]:
                continue
            if maps[nr][nc] == 0:
                continue
            q.append([nr, nc, cmove + 1])
            visit[nr][nc] = 1
    return -1

def solution(maps):
    answer = 0
    answer = bfs(maps)
    return answer