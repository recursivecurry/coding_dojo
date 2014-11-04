// EULER: Multiples of 3 and 5
// https://projecteuler.net/problem=1
fn main() {
    let sum = range(1i, 1000i).filter(|&x| {x % 3 == 0 || x % 5 == 0}).fold(0i, |a, b| a + b);
    println!("{}", sum);
}
