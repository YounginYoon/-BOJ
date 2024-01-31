from sys import stdin

money = int(stdin.readline())
stock = list(map(int, stdin.readline().split()))

def BNP():
    remain = money
    getStock = 0
    for i in range(14):
        if stock[i] <= remain:
            buy = remain // stock[i]
            getStock += buy
            remain -= (buy * stock[i])
        if remain == 0:
            break
    ret = remain + stock[13] * getStock
    return ret

def TIMING():
    remain = money
    getStock = 0
    i = 0
    
    increase = 0
    decrease = 0

    for i in range(14):
        # 주가 상승 카운트
        if increase == 3:
            if getStock > 0:
                #전량 매도
                remain += (getStock * stock[i])
                getStock = 0
                increase -= 1
        if i < 13 and stock[i] < stock[i + 1]:
            increase += 1
        else:
            increase = 0

        if decrease == 3:
            # 전량 매수
            buy = remain // stock[i]
            getStock += buy
            remain -= (buy * stock[i])
            decrease -= 1
        if i < 13 and stock[i] > stock[i + 1]:
            decrease += 1
        else:
            decrease = 0
    ret = remain + getStock * stock[13]
    return ret

j = BNP()
s = TIMING()

if j > s:
    print("BNP")
elif j < s:
    print("TIMING")
else:
    print("SAMESAME")

