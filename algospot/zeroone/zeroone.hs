-- ALGOSPOT - 0-1수열
-- http://algospot.com/judge/problem/read/ZEROONE
module Main where

import Control.Monad

check :: String -> Int -> Int -> String
check input s e = same (take (e-s+1) (drop s input))

same :: String -> String
same (x:xs)
    | [] == r = "Yes"
    | otherwise = "No"
    where
        r = dropWhile (\a -> x==a) xs


main :: IO ()
main =
    do zeroOneList <- getLine
       caseNumString <- getLine
       let caseNum = read caseNumString :: Int
       subMain zeroOneList caseNum

subMain :: String -> Int -> IO()
subMain zeroOneList caseNum =
    unless  (0 == caseNum) $
    do startEndString <- getLine
       let startEnd = [read x :: Int | x <- words startEndString]
       putStrLn $ check zeroOneList (head startEnd) (last startEnd)
       subMain zeroOneList (caseNum-1)