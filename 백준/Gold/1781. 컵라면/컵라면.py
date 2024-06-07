from sys import stdin
import heapq

n = int(stdin.readline())
inp = []

for _ in range(n):
    r, c = map(int, stdin.readline().split())
    inp.append((r, c))

inp.sort()
stack = []

for i in inp:
    heapq.heappush(stack, i[1])
    if i[0] < len(stack):
        heapq.heappop(stack)

print(sum(stack))