module Parlindromize (simple, kmp) where


isPalindrome :: String -> Bool
isPalindrome s = s == (reverse s)

simple :: String -> Int
simple [] = 0
simple xxs@(x:xs)
  | isPalindrome xxs = length xxs
  | otherwise = 2 + simple xs

matches :: Eq a => [a] -> [a] -> [Int]
matches ws = map length . filter (endswith ws) . inits

inits :: [a] -> [[a]]
inits xs = inits' (init xs) [xs]

inits' :: [a] -> [[a]] -> [[a]]
inits' [] ys = []:ys
inits' xs ys = inits' (init xs) (xs:ys)

endswith = undefined

kmp :: String -> String -> [Int]
kmp = undefined

bm :: String -> String -> [Int]
bm = undefined
