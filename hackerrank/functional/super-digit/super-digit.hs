-- HACKERRANK: Super Digit
-- https://www.hackerrank.com/challenges/super-digit
module Main where


digits :: Integer -> [Integer]
digits 0 = []
digits n = m : rest
           where m = mod n 10
                 rest = digits (div n 10)


solve :: Integer -> Integer
solve n = if n < 10 then n else solve $ sum $ digits n


main :: IO ()
main = do nkStr <- getLine
          let (n:k:_) = map (read :: String -> Integer) $ words nkStr
          let input = sum (digits n) * k
          putStrLn $ show (solve input)
