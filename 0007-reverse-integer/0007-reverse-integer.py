class Solution(object):
    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """
        flag = 1
        if x < 0:
            flag = -1
            x *= -1
        tmp = str(x)
        
        ret = 0
        for i in range(len(tmp) - 1, -1, -1):
            ret *= 10
            ret += int(tmp[i])

        ans = ret * flag
        if ans > 2**31 - 1 or ans < -2**31:
            return 0
        return ans
        