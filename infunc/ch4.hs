-- 4.2 Variable-length Arithmatic
-- page 78

module Main where

base :: Int
base = 1000

type BigInt = Int
type VInt = [BigInt]

strep :: VInt -> VInt
strep [] = [0]
strep xs = dropWhile (0==) xs

align :: VInt -> VInt -> (VInt, VInt)
align xs ys = if 0 < num
              then (copy 0 num ++ xs, ys)
              else (xs, copy 0 (-num) ++ ys)
              where num = length ys - length xs
                    copy x n = [x|_<-[1..n]]

--vcompare ([BigInt] -> [BigInt] -> t) -> [BigInt] -> [BigInt] -> t
vcompare op xs ys = op us vs
                    where (us, vs) = align xs ys

veq = vcompare (==)
vleq = vcompare (<=)
vless = vcompare (<)

carry :: BigInt -> [BigInt] -> [BigInt]
carry x (c:xs) = div (x + c) base : mod (x + c) base : xs

main :: IO ()
main = undefined