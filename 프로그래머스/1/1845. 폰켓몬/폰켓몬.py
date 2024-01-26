def solution(nums):
    answer = 0
    setNum = set(nums)
    maxGet = len(nums) / 2
    if maxGet < len(setNum):
        return maxGet
    return len(setNum)
