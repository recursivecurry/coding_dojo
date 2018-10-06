/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseListIter(head *ListNode) *ListNode {
    var out *ListNode
    for head != nil {
        temp := head.Next
        head.Next = out
        out = head
        head = temp
    }
    return out
}

func reverseList(head *ListNode) *ListNode {
    if head == nil || head.Next == nil{
        return head
    }
    p := reverseList(head.Next)
    head.Next.Next = head
    head.Next = nil
    return p
    
}
