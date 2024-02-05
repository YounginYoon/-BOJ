from sys import stdin

k = int(stdin.readline())

dp = [0] * 31

for i in range(31):
    dp[i] = 2 ** (i + 1)

i = 0
num = []
while k > 0:
    q = k // (2**i)
    r = k % (2**i)

    if r > 0:
        q += 1
    if q % 2 == 0:
        num.append(7)
    else:
        num.append(4)
    k -= dp[i]
    i += 1

ret = 0
num.reverse()
for n in num:
    ret = ret * 10 + n

print(ret)