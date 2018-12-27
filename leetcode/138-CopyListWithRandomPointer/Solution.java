/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {       
        Map<RandomListNode, RandomListNode> copiesByNodes = new HashMap<>();
      RandomListNode current = head;  
      while (current != null) {
          RandomListNode copiedNode = new RandomListNode(current.label);
          copiedNode.next = current.next;
          copiedNode.random = current.random;
          copiesByNodes.put(current, copiedNode);
          current = current.next;
        }
      current = copiesByNodes.get(head);
        while (current != null) {
          current.next = copiesByNodes.get(current.next);
          current.random = copiesByNodes.get(current.random);
          current = current.next;
        }
      return copiesByNodes.get(head);
    }
}
