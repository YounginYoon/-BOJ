from sys import stdin

n = int(stdin.readline())
m = int(stdin.readline())
x = list(map(int, stdin.readline().split()))

# 이전 가로등이 있는 위치가 비춘 오른쪽 지점과 현재 가로등 위치에서 비춘 왼쪽 지점 비교
# 이전 오른쪽 >= 현재 왼쪽이면 가능한 것이므로 lightPoint를 현재 오른쪽으로 갱신
def check(height):
    lightPoint = 0
    for xi in x:
        if xi - height <= lightPoint:
            lightPoint = xi + height
        else:
            return False
    # 마지막 지점도 가로등이 비출 수 있는지 확인해야 함
    # 마지막 가로등이 비추는 오른쪽 끝 지점에서 굴다리의 끝 좌표를 뺐을 때
    # 0보다 작거나 같아야 마지막 지점도 비춰짐
    if n - lightPoint <= 0:
        return True
    return False

left = 1
right = n
ret = 0
while left <= right:
    mid = (left + right) // 2
    if check(mid) == False:
        left = mid + 1
    else:
        ret = mid
        right = mid - 1
    
print(ret)


