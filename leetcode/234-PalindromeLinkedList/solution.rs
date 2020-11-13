#[derive(Debug)]
struct Solution {}

// Definition for singly-linked list.
#[derive(PartialEq, Eq, Clone, Debug)]
pub struct ListNode {
    pub val: i32,
    pub next: Option<Box<ListNode>>,
}

impl ListNode {
    #[inline]
    fn new(val: i32) -> Self {
        ListNode {
            next: None,
            val,
        }
    }
}

fn length(head: &Option<Box<ListNode>>) -> isize {
    let mut length = 0;
    let mut head = head;
    while let Some(val) = head {
        length += 1;
        head = &val.next;
    }
    length
}

fn split(mut head: Option<Box<ListNode>>, length: isize) -> (Option<Box<ListNode>>, Option<Box<ListNode>>) {
    let mut reversed = None as Option<Box<ListNode>>;
    for _ in 0..length {
        let mut node = head.unwrap();
        head = node.next.take();
        node.next = reversed;
        reversed = Some(node);
    }
    (head, reversed)
}

fn compare(head: &Option<Box<ListNode>>, tail: &Option<Box<ListNode>>) -> bool {
    let mut h = head;
    let mut t = tail;
    while h.is_some() && t.is_some() {
        println!("compare {:?} {:?}", h, t);
        if h.as_ref().unwrap() != t.as_ref().unwrap() {
            return false;
        }
        h = &h.as_ref().unwrap().next;
        t = &t.as_ref().unwrap().next;
    }
    true
}

impl Solution {
    pub fn is_palindrome(head: Option<Box<ListNode>>) -> bool {
        let mut len = length(&head);
        let (front, reversed) = split(head, len/2);
        if len % 2 == 1 {
            compare(&front.unwrap().next, &reversed)
        } else {
            compare(&front, &reversed)
        }
    }
}


fn main() {
    let sample =
        Some(Box::new(ListNode {
            val: 1,
            next: Some(Box::new(ListNode {
                val: 2,
                next: None as Option<Box<ListNode>>,
            })),
        }));
    // Some(Box::new(ListNode {
    //     val: 1,
    //     next: Some(Box::new(ListNode {
    //         val: 2,
    //         next: Some(Box::new(ListNode {
    //             val: 2,
    //             next: Some(Box::new(ListNode {
    //                 val: 1,
    //                 next: None as Option<Box<ListNode>>
    //             })),
    //         })),
    //     })),
    // }));
    println!("{:?}", Solution::is_palindrome(sample));
}
