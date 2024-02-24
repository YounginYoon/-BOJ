from sys import stdin
from collections import deque

def check():
    if r - 1 >= cur_r or c - 1 >= cur_c:
        return False
    if arr[r - 1][c - 1] == k:
        return True
    return False

def R():
    global cur_r, cur_c
    max_c = 0
    for i, r in enumerate(arr):
        # 숫자 등장 횟수 카운트
        count = {}
        for c in r:
            if c  == 0:
                continue
            if c not in count:
                count[c] = 1
            else:
                count[c] += 1
                
        sorted_dict = sorted(count.items(), key=lambda item: (item[1], item[0]))
        new_list = []
        for key, value in sorted_dict:
            new_list.append(key)
            new_list.append(value)
        arr[i] = new_list
        length_list = len(new_list)
        max_c = max(max_c, length_list)
    cur_c = max_c
    
    for i, r in enumerate(arr):
        if len(r) < cur_c:
            for _ in range(cur_c - len(r)):
                arr[i].append(0)
    cur_r = len(arr)
    cur_c = len(arr[0])
        

def C():
    global cur_r, cur_c
    global arr
    max_c = 0
    
    # rxc -> cxr로 만들기
    new_arr = []
    for c in range(cur_c):
        push_arr = []
        for r in range(cur_r):
            push_arr.append(arr[r][c])
        new_arr.append(push_arr)
    
    for i, r in enumerate(new_arr):
        # 숫자 등장 횟수 카운트
        count = {}
        for c in r:
            if c == 0:
                continue
            if c not in count:
                count[c] = 1
            else:
                count[c] += 1
                
        sorted_dict = sorted(count.items(), key=lambda item: (item[1], item[0]))
        new_list = []
        for key, value in sorted_dict:
            new_list.append(key)
            new_list.append(value)
        new_arr[i] = new_list
        length_list = len(new_list)
        max_c = max(max_c, length_list)
    
    for i, r in enumerate(new_arr):
        if len(r) < max_c:
            for _ in range(max_c - len(r)):
                new_arr[i].append(0)

    new_arr_col = len(new_arr[0])
    new_arr_reverse = []
    new_arr_row = len(new_arr)

    for c in range(new_arr_col):
        tmp = []
        for r in range(new_arr_row):
            tmp.append(new_arr[r][c])
        new_arr_reverse.append(tmp)

    arr = new_arr_reverse
    cur_r = len(arr)
    cur_c = len(arr[0])


r, c, k = map(int, stdin.readline().split())
arr = []
cur_r = 3
cur_c = 3
cnt = 0

for i in range(3):
    new_list = list(map(int, stdin.readline().split()))
    arr.append(new_list)
    

while check() == False:
    if cur_r >= cur_c:
        R()
    else:
        C()
    cnt += 1

    if cnt > 100:
        cnt = -1
        break

print(cnt)
