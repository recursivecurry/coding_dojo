use std::cmp::Ordering;

#[derive(Debug)]
struct Solution {}

impl Solution {
    pub fn reorder_log_files(logs: Vec<String>) -> Vec<String> {
        let mut cloned = logs.clone();
        cloned.sort_by(|a, b|
                {
                    let (ai, ad) = a.split_at(a.find(' ').unwrap()+1);
                    let (bi, bd) = b.split_at(b.find(' ').unwrap()+1);
                    let alpha: (bool, bool) = (ad.chars().any(char::is_alphabetic), bd.chars().any(char::is_alphabetic));

                    let result = match alpha {
                        (true, true) => {
                            if ad.cmp(bd) == Ordering::Equal {
                                ai.cmp(bi)
                            } else {
                                ad.cmp(bd)
                            }
                        },
                        (true, false) => Ordering::Less,
                        (false, true) => Ordering::Greater,
                        (false, false) => Ordering::Equal,
                    };
                    result
                }
            );
        return cloned;
    }
}

// ["8 fj dzz k", "5r 446 6 3", "63 gu psub", "5 ba iedrz", "6s 87979 5", "3r 587 01", "jc 3480612", "bb wsrd kp", "b aq cojj", "r5 6316 71"]
fn main() {
    println!("{:?}", "wsrd kp".cmp("ba iedrz"));
    println!("{:?}", Solution::reorder_log_files(vec!{
        String::from("8 fj dzz k"),
        String::from("5r 446 6 3"),
        String::from("63 gu psub"),
        String::from("5 ba iedrz"),
        String::from("6s 87979 5"),
        String::from("3r 587 01"),
        String::from("jc 3480612"),
        String::from("bb wsrd kp"),
        String::from("b aq cojj"),
        String::from("r5 6316 71"),
    }));
}
