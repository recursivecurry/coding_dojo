/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapPairs(head *ListNode) *ListNode {
	prev := &head
	for (*prev != nil) && (*prev).Next != nil {
		a := *prev
		b := (*prev).Next
		a.Next = b.Next
		b.Next = a
		*prev = b
		prev = &(a.Next)
	}
	return head
}
