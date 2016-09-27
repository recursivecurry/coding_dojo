# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        ret = None
        c = 0

        while True:
            if l1 is not None and l2 is not None:
                c, s = divmod(l1.val + l2.val + c, 10)
                if ret is None:
                    ret = ListNode(s)
                    prev = ret
                else:
                    prev.next = ListNode(s)
                    prev = prev.next
                l1 = l1.next
                l2 = l2.next
            elif l1 is not None:
                c, s = divmod(l1.val + c, 10)
                if ret is None:
                    ret = ListNode(s)
                    prev = ret
                else:
                    prev.next = ListNode(s)
                    prev = prev.next
                l1 = l1.next
            elif l2 is not None:
                c, s = divmod(l2.val + c, 10)
                if ret is None:
                    ret = ListNode(s)
                    prev = ret
                else:
                    prev.next = ListNode(s)
                    prev = prev.next
                l2 = l2.next
            else:
                if c == 1:
                    prev.next = ListNode(c)
                break
        return ret

