def solution(phone_book):
    answer = True
    phone_book.sort()
    
    for i in range(len(phone_book)):
        if i == len(phone_book) - 1:
            answer = True
        else:
            fIdx = phone_book[i + 1].find(phone_book[i])
            if fIdx == 0:
                answer = False
                break
    return answer