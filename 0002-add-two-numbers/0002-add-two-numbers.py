# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution(object):
    def getListSize(self, l):
        cur = l
        cnt = 0

        while cur:
            cnt += 1
            cur = cur.next
        return cnt

    def addList(self, l1, l2):
        #len(l1) >= len(l2)
        l = [0] * (self.getListSize(l1) + 1)

        curS = l2
        curB = l1
        idx = 0

        while curS:
            total = curS.val + curB.val + l[idx]
            if total >= 10:
                l[idx + 1] += 1
                total -= 10
            l[idx] = total
            curS = curS.next
            curB = curB.next
            idx += 1
        
        while curB:
            curSum = l[idx] + curB.val
            if curSum >= 10:
                l[idx] -= 1
                l[idx + 1] += 1
                curSum -= 10
            l[idx] = curSum
            idx += 1
            curB = curB.next
        return l

    def pushNode(self, cur, node):
        cur.next = node
        cur = cur.next
        return cur

    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        l = []
        lenL1 = self.getListSize(l1)
        lenL2 = self.getListSize(l2)
        if lenL1 >= lenL2:
            l = self.addList(l1, l2)
        else:
            l = self.addList(l2, l1)
        
        newList = ListNode(l[0])
        cur = newList
        for i in range(1, len(l)):
            if i == len(l) - 1:
                if l[i] == 0:
                    continue
            newNode = ListNode(l[i])
            cur = self.pushNode(cur, newNode)
        return newList
            


        
