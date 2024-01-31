from sys import stdin

# n = 점, m = 선분 개수
n, m = map(int, stdin.readline().split())

# 점 입력 받기
dots = list(map(int, stdin.readline().split()))
dots.sort()

def searchLessThan(target):
    # 미만 찾기
    left, right = 0, n - 1
    ret = -1

    while left <= right:
        mid = (left + right) // 2
        if dots[mid] >= target:
            right = mid - 1
        else:
            ret = mid
            left = mid + 1
    return ret

def searchBiggerThan(target):
    #초과 찾기
    left, right = 0, n - 1
    ret = n

    while left <= right:
        mid = (left + right) // 2
        if dots[mid] <= target:
            left = mid + 1
        else:
            ret = mid
            right = mid - 1
    return ret

answer = []
for i in range(m):
    s, e = map(int, stdin.readline().split())
    less = searchLessThan(s)
    bigger = searchBiggerThan(e)
    cnt = bigger - less - 1
    answer.append(cnt)

for i in answer:
    print(i)