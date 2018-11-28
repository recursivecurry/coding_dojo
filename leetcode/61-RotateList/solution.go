/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil {
		return head
	}
	begin, _, _ := solve(head, head, 0, k)
	return begin
}

func solve(begin, cur *ListNode, count, k int) (*ListNode, int, int) {
	if cur == nil {
		if k % count == 0 {
			return begin, -1, count
		} else {
			return nil, k % count, count
		}
	}

	ret, remained, total := solve(begin, cur.Next, count+1, k)
	if remained == k % total {
		cur.Next = begin
		if remained == 1 {
			return cur, remained - 1, total
		} else {
			return nil, remained -1, total
		}
	} else if remained == -1 {
		return ret, -1, total
	} else if remained == 1 {
		return cur, 0, total
	} else if remained == 0 {
		cur.Next = nil
		return ret, -1, total
	} else {
		return nil, remained - 1, total
	}
}
