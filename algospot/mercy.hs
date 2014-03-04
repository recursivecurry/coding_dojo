module Main where

main :: IO ()
main = do
    interact (unlines. map hello . lines)

hello :: String -> String
hello n = let count = read n :: Integer
          in concat ["Hello Algospot!\n" | _ <- [1..count]]
