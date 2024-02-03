from sys import stdin

x, y = map(int, stdin.readline().split())

left = 1
right = x
z = int((y * 100) / x)
ret = -1

while left <= right:
    mid = (left + right) // 2
    newZ = int(((y + mid) * 100) / (x + mid))
    #print("{} {} {}".format(mid, newZ, z))
    if newZ == z:
        left = mid + 1
    else:
        ret = mid
        right = mid - 1
    
print(ret)
