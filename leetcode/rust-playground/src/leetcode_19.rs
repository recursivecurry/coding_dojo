// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode { next: None, val }
    }
    fn from_vec(values: Vec<i32>) -> Option<Box<ListNode>> {
        let mut head = None;
        for v in values.into_iter().rev() {
            let mut node = Box::new(ListNode::new(v));
            node.next = head;
            head = Some(node);
        }
        head
    }

    fn to_vec(list: Option<Box<ListNode>>) -> Vec<i32> {
        let mut result = vec![];
        let mut current = list;
        while let Some(node) = current {
            result.push(node.val);
            current = node.next;
        }
        result
    }
}

pub struct Solution {}

impl Solution {
    pub fn remove_nth_from_end(head: Option<Box<ListNode>>, n: i32) -> Option<Box<ListNode>> {
        // let (result, _) = Self::remove_nth_from_end_recur(head, n)
        // return result
        Self::remove_nth_from_end_iter_unsafe(head, n)
    }

    fn remove_nth_from_end_iter_unsafe(head: Option<Box<ListNode>>, n: i32) -> Option<Box<ListNode>> {
        let n = if n < 1 { return head } else { n as usize };

        let mut dummy = Box::new(ListNode { val: 0, next: head });

        // `fast` walks the list using an immutable raw pointer to avoid borrow aliasing with `slow`.
        let mut fast: *const Option<Box<ListNode>> = &dummy.next;
        // `slow` trails behind and is a mutable raw pointer to the link we will ultimately modify.
        let mut slow: *mut Option<Box<ListNode>> = &mut dummy.next;

        // Advance `fast` n steps ahead.
        for _ in 0..n {
            // If the list is shorter than n, no deletion occurs; just return original.
            let fref = unsafe { &*fast };
            if let Some(node) = fref {
                fast = &node.next;
            } else {
                return dummy.next;
            }
        }

        // Move both until `fast` reaches the end.
        while let Some(node) = unsafe { &*fast } {
            fast = &node.next;
            // advance `slow` by one link
            let sref = unsafe { &mut *slow };
            if let Some(snode) = sref {
                slow = &mut snode.next;
            }
        }

        // `slow` now points to the link before the target. Remove it in-place.
        let target_link = unsafe { &mut *slow };
        if let Some(mut target) = target_link.take() {
            *target_link = target.next.take();
        }

        dummy.next
    }

    fn remove_nth_from_end_iter(head: Option<Box<ListNode>>, n: i32) -> Option<Box<ListNode>> {
        let n = if n < 1 { return head } else { n as usize };

        // Use a dummy head so removing the first node is uniform
        let mut dummy = Box::new(ListNode { val: 0, next: head });

        // 1) First pass: compute list length using shared refs only
        let mut len = 0usize;
        {
            let mut cur = dummy.next.as_ref();
            while let Some(node) = cur {
                len += 1;
                cur = node.next.as_ref();
            }
        }

        // If n is longer than the list, return original list
        if n > len {
            return dummy.next;
        }

        // 2) Second pass: walk (len - n) steps from dummy to land
        // on the node *before* the one to remove, using &mut refs only
        let mut steps = len - n;
        let mut prev: &mut ListNode = &mut dummy;
        while steps > 0 {
            // Safe unwrap: steps < len implies there is a next node
            prev = prev.next.as_deref_mut().unwrap();
            steps -= 1;
        }

        // 3) Splice out the target node
        if let Some(mut target) = prev.next.take() {
            prev.next = target.next.take();
        }

        dummy.next
    }

    fn remove_nth_from_end_recur(
        head: Option<Box<ListNode>>,
        n: i32,
    ) -> (Option<Box<ListNode>>, i32) {
        match head {
            None => (None, 1),
            Some(current) => {
                let (next_node, nth) = Self::remove_nth_from_end_recur(current.next, n);
                if nth == n {
                    (next_node, nth + 1)
                } else {
                    (
                        Some(Box::new(ListNode {
                            val: current.val,
                            next: next_node,
                        })),
                        nth + 1,
                    )
                }
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_remove_middle_node() {
        // 입력: [1,2,3,4,5], n = 2
        let head = ListNode::from_vec(vec![1, 2, 3, 4, 5]);
        let result = Solution::remove_nth_from_end(head, 2);
        assert_eq!(ListNode::to_vec(result), vec![1, 2, 3, 5]);
    }

    #[test]
    fn test_remove_last_node() {
        // 입력: [1,2], n = 1
        let head = ListNode::from_vec(vec![1, 2]);
        let result = Solution::remove_nth_from_end(head, 1);
        assert_eq!(ListNode::to_vec(result), vec![1]);
    }

    #[test]
    fn test_remove_first_node() {
        // 입력: [1,2,3], n = 3 → 첫 번째 노드 제거
        let head = ListNode::from_vec(vec![1, 2, 3]);
        let result = Solution::remove_nth_from_end(head, 3);
        assert_eq!(ListNode::to_vec(result), vec![2, 3]);
    }

    #[test]
    fn test_single_node_list() {
        // 입력: [1], n = 1 → 빈 리스트
        let head = ListNode::from_vec(vec![1]);
        let result = Solution::remove_nth_from_end(head, 1);
        assert_eq!(ListNode::to_vec(result), Vec::<i32>::new());
    }
}
