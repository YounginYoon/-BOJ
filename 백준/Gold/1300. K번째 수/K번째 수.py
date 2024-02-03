from sys import stdin

n = int(stdin.readline())
k = int(stdin.readline())

'''
1. 1부터 n*n까지 mid를 설정하면서 이분탐색을 한다.
2. 2차원 배열로 봤을 때, i행의 수들은 i의 배수이다.
3. 즉, mid보다 작은 수의 개수 == (mid / i)
4. count >= k 이면, 더 작은 수의 mid를 찾기 위해 end를 줄이고
    count < k 이면, 더 큰 수의 mid를 찾기 위해 left를 늘인다.
'''

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