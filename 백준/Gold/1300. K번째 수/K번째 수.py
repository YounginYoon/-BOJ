from sys import stdin

n = int(stdin.readline())
k = int(stdin.readline())

# arr = []
# for i in range(1, n + 1):
#     for j in range(1, n + 1):
#         arr.append(i * j)

left = 1
right = n * n
ret = 0

while left <= right:
    mid = (left + right) // 2
    cnt = 0
    for i in range(1, n + 1):
        cnt += min(mid // i, n)
    
    if cnt >= k:
        ret = mid
        right = mid - 1
    else:
        left = mid + 1

print(ret)