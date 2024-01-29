import datetime

def solution(a, b):
    answer = ''
    days = ['FRI','SAT','SUN','MON','TUE','WED','THU']
    firstDay = datetime.date(2016, 1, 1)
    targetDay = datetime.date(2016, a, b)
    remainDay = targetDay - firstDay
    remainDay = remainDay.days
    tmp = remainDay % 7
    answer = days[tmp]
    
    return answer