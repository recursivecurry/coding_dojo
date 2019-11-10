module Main where

listReplication :: [String] -> [String]
listReplication (n:ls) = concatMap (replicate (read n :: Int)) ls

main :: IO ()
main = interact (unlines . listReplication . lines)
