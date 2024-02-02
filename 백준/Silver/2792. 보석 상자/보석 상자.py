from sys import stdin

n, m = stdin.readline().split()
n = int(n)
m = int(m)

v = []

for i in range(m):
    x = int(stdin.readline())
    v.append(x)

total = sum(v)

def binarySearch():
    left, right = 1, total
    ret = 0

    while left <= right:
        mid = (left + right) // 2
        cnt = 0
        for vi in v:
            q = vi // mid
            r = vi % mid
            cnt += q
            if r > 0:
                cnt += 1
        
        if cnt <= n:
            ret = mid
            right = mid - 1
        else:
            left = mid + 1
    return ret

print(binarySearch())
