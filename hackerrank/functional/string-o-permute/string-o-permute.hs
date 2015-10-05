-- HACKERRANK: String-o-Permute
-- https://www.hackerrank.com/challenges/string-o-permute

module Main where

import qualified Control.Monad as M


solve :: String -> String
solve [] = []
solve (x1:x2:xs) = x2:x1:(solve xs)


main :: IO ()
main = getLine >>= \tStr -> M.replicateM_ ((read::String->Int) tStr) (getLine >>= \l -> putStrLn (solve l))
