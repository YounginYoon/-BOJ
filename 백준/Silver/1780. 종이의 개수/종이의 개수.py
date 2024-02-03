from sys import stdin

n = int(stdin.readline())

input = [list(map(int, stdin.readline().split())) for _ in range(n)]

x = 0 # -1 개수
y = 0 # 0 개수
z = 0 # 1 개수

def cutPaper(sr, sc, size):
    global x, y, z
    if check(sr, sc, size):
        if input[sr][sc] == -1:
            x += 1
        elif input[sr][sc] == 0:
            y += 1
        else:
            z += 1
        return
    else:
        newSize = size // 3
        for i in range(0, 3):
            for j in range(0, 3):
                nr = sr + newSize * i
                nc = sc + newSize * j
                cutPaper(nr, nc, newSize)
        return


def check(sr, sc, size):
    flag = input[sr][sc]
    for i in range(sr, sr + size):
        for j in range(sc, sc + size):
            if input[i][j] != flag:
                return False
    return True

cutPaper(0, 0, n)
print(x)
print(y)
print(z)