/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {

        if (head == null) {
            return;
        }

        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode current = head.next;

        while (current != null) {
            deque.addLast(current);
            current = current.next;
        }

        current = head;

        while (!deque.isEmpty()) {

            ListNode next = deque.pollLast();
            current.next = next;
            current = next;
            current.next = null;

            if (deque.isEmpty()) {
                break;
            }

            next = deque.pollFirst();
            current.next = next;
            current = next;
            current.next = null;
        } 
    }
}
