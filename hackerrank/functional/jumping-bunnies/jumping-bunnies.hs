module Main where


--gcd :: Int -> Int -> Int
--gcd x 0 = x
--gcd x y
--  | x < y = gcd y x
--  | otherwise = gcd y (mod x y)


--lcm :: Int -> Int -> Int
--lcm x y = let g = gcd (x y)
--           in (div x g) * (div y g)  * g


splitBy delimiter = foldr f [[]]
            where f c l@(x:xs) | c == delimiter = []:l
                               | otherwise = (c:x):xs


solve :: [Int] -> Int
solve = foldr lcm 1


main :: IO ()
main = do
  getLine
  nsStr <- getLine
  let ns = map (read::String -> Int) $ splitBy ' ' nsStr
  print $ solve ns
