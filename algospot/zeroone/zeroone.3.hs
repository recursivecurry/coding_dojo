-- ALGOSPOT - 0-1수열
-- http://algospot.com/judge/problem/read/ZEROONE
module Main where

import Data.Array
import Control.Monad

mark :: [(Int, Char)] -> Int -> Int -> [(Int, Int)]
mark ((_,x1):(i2,x2):xs) pos idx
    | x1 == x2 = (idx, pos) : mark ((i2,x2):xs) pos (idx+1)
    | otherwise = (idx, i2) : mark ((i2,x2):xs) i2 (idx+1)
mark (_:[]) _ _ = []

check :: Array Int Int -> Int -> Int -> String
check xs 0 ed
    | 0 == xs!(ed-1) = "Yes"
    | otherwise = "No"
check xs st ed
    | xs!(st-1) == xs!(ed-1) = "Yes"
    | otherwise = "No"

main :: IO ()
main =
    do zeroOneListString <- getLine
       caseNumString <- getLine
       let caseNum = read caseNumString :: Int
           zeroOneArray = array (0::Int,length zeroOneListString-1) (mark (zip [(0::Int)..length zeroOneListString-1] zeroOneListString) (0::Int) (0::Int))
       subMain zeroOneArray caseNum

subMain :: Array Int Int -> Int -> IO()
subMain zeroOneList caseNum =
    unless  (0 == caseNum) $
    do startEndString <- getLine
       let (start:end:[]) = [read x :: Int | x <- words startEndString]
       putStrLn $ check zeroOneList start end
       subMain zeroOneList (caseNum-1)