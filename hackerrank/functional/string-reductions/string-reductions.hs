module Main where

solve :: String -> String
solve [] = []
solve (x:xs) = x:(solve $ filter (\c -> x /= c) xs)

main :: IO ()
main = getLine >>= \l -> putStrLn (solve l)
