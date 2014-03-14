-- ALGOSPOT - 0-1수열
-- http://algospot.com/judge/problem/read/ZEROONE
module Main where

import Control.Monad

mark :: [(Int, Char)] -> Int -> [Int]
mark ((_,x1):(i2,x2):xs) pos
    | x1 == x2 = pos : mark ((i2,x2):xs) pos
    | otherwise = i2 : mark ((i2,x2):xs) i2
mark (_:[]) _ = []

check :: [Int] -> Int -> Int -> String
check xs 0 ed
    | 0 == xs!!(ed-1) = "Yes"
    | otherwise = "No"
check xs st ed
    | xs!!(st-1) == xs!!(ed-1) = "Yes"
    | otherwise = "No"

main :: IO ()
main =
    do zeroOneListString <- getLine
       caseNumString <- getLine
       let caseNum = read caseNumString :: Int
           zeroOneList = mark (zip [0..] zeroOneListString) 0
       subMain zeroOneList caseNum

subMain :: [Int] -> Int -> IO()
subMain zeroOneList caseNum =
    unless  (0 == caseNum) $
    do startEndString <- getLine
       let startEnd = [read x :: Int | x <- words startEndString]
       putStrLn $ check zeroOneList (head startEnd) (last startEnd)
       subMain zeroOneList (caseNum-1)