from sys import stdin

n = int(stdin.readline())

input = [list(map(int, stdin.readline().split())) for _ in range(n)]

blue = 0
white = 0

def cutPaper(sr, sc, size):
    global blue
    global white
    if check(sr, sc, sr + size, sc + size):
        if input[sr][sc] == 1:
            blue += 1
        else:
            white += 1
        return
    else:
        cutPaper(sr, sc, size // 2)
        cutPaper(sr + size // 2, sc, size // 2)
        cutPaper(sr, sc + size // 2, size // 2)
        cutPaper(sr + size // 2, sc + size // 2, size // 2)


def check(sr, sc, er, ec):
    flag = input[sr][sc]
    for i in range(sr, er):
        for j in range(sc, ec):
            if input[i][j] != flag:
                return False
    return True

cutPaper(0, 0, n)
print(white)
print(blue)