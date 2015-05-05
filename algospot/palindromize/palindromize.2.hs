module Parlindromize where


isPalindrome :: String -> Bool
isPalindrome s = s == (reverse s)

simple :: String -> Int
simple [] = 0
simple xxs@(x:xs)
  | isPalindrome xxs = length xxs
  | otherwise = 2 + simple xs

matches :: Eq a => [a] -> [a] -> [Int]
--matches ws = map length . filter (endswith ws) . inits
--matches ws = map fst . filter snd . map (fork (length, endswith ws)) . inits
--matches ws = map fst . filter snd . scanl step (0,e)
--matches ws = map fst . filter (p . snd) . scanl step (0,e)
matches ws = map fst . filter ((startswith sw) . snd) . scanl step (0, [])
             where sw = reverse ws

step :: (Int, [a]) -> a -> (Int, [a])
step (n, sx) x = (n+1, x:sx)

fork :: (a -> b, a -> c) -> a -> (b,c)
fork (f, p) x = (f x, p x)

inits :: [a] -> [[a]]
inits xs = inits' (init xs) [xs]

inits' :: [a] -> [[a]] -> [[a]]
inits' [] ys = []:ys
inits' xs ys = inits' (init xs) (xs:ys)

myscanl :: (b -> a -> b) -> b -> [a] -> [b]
myscanl op b = map (foldl op b) . inits


startswith :: Eq a => [a] -> [a] -> Bool
startswith [] _ = True
startswith _ [] = False
startswith (x:xs) (y:ys) = if x == y
                           then startswith xs ys
                           else False

endswith :: Eq a => [a] -> [a] -> Bool
endswith xs ys = startswith (reverse xs) (reverse ys)
--endswith ws = foldl op e

bm :: String -> String -> [Int]
bm = undefined
