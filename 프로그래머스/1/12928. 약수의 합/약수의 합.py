import math
def solution(n):
    answer = 0
    def getYaksu(n):
        ret = 0
        for i in range(1, (int)(math.sqrt(n)) + 1):
            if n % i == 0:
                ret += i
                if i != n // i:
                    ret += (n // i)
        return ret
    answer = getYaksu(n)
    return answer