class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode turtle = head;
        ListNode rabbit = head;

        while (true) {
            if (rabbit == null || rabbit.next == null || rabbit.next.next == null) {
                break;
            }
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if (rabbit == turtle) {
                return true;
            }
        }
        return false;
    }
}
