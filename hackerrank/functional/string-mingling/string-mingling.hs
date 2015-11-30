module Main where

mingle :: String -> String -> String
mingle [] [] = []
mingle (x:xs) (y:ys) = x:y:(mingle xs ys)

main :: IO ()
main = do p <- getLine
          q <- getLine
          putStrLn $ mingle p q
