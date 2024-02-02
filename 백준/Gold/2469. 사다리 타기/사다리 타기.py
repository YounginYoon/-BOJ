from sys import stdin

alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
ret = ''

# 참가자 수
k = int(stdin.readline())
# 가로줄
n = int(stdin.readline())
tinput = stdin.readline().strip()

ladder = [list(stdin.readline().strip()) for _ in range(n)]
ladderS = []
ladderE = []


# tinput을 리스트로 쪼개기
target = []
for x in tinput:
    target.append(x)

start = sorted(target)

# ?를 기준으로 사다리 나누기
for i in range(n):
    if '?' in ladder[i]:
        ladderS = ladder[:i]
        ladderE = ladder[i + 1:]
        break

# ladderS를 따라서 사다리 타고 내려오기
for lad in ladderS:
    for i in range(k - 1):
        if lad[i] == '-':
            start[i], start[i + 1] = start[i + 1], start[i]


# ladderE를 뒤집어서 도착 지점에서 사다리 타고 올라오기
ladderE.reverse()
for lad in ladderE:
    for i in range(k - 1):
        if lad[i] == '-':
            target[i], target[i + 1] = target[i + 1], target[i]

answer = []
# 배열을 비교해서 사다리 만들기
for i in range(k - 1):
    if start[i] == target[i]:
        answer.append('*')
    else:
        if start[i] == target[i + 1]:
            answer.append('-')
        elif i != 0 and start[i] == target[i - 1]: # 위에서 사다리를 놔서 자리가 변경됐으므로 사다리를 놓지 않음
            answer.append('*')
        else:
            answer = ['x' for _ in range(k - 1)]
            break

print(''.join(answer)) # join() : 들어온 리스트의 요소를 하나하나 합쳐서 문자열로 반환