-- ALGOSPOT - 콘서트
-- http://algospot.com/judge/problem/read/CONCERT
module Main where

import Control.Monad

concert :: Int -> Int -> Int -> [Int] -> Int
concert n vs vm vl = mf n vs vm vl (n,vm)

mf :: Int -> Int -> Int -> [Int] -> (Int,Int) -> Int
mf = undefined
--mf n vs vm vl (x,y) = (map f [(a,b)|a<-[0..n], b<-[0..vm]] !!)
--                      where f (n,vm) =

main :: IO ()
main =
    do line <- getLine
       let caseNum = read line :: Int
       subMain caseNum

subMain :: Int -> IO()
subMain caseNum =
    unless  (0 == caseNum) $
    do line1 <- getLine
       line2 <- getLine
       let (n:vs:[vm]) = [read x :: Int|x<-words line1]
           vl = [read x :: Int|x<-words line2]
       print (concert n vs vm vl)
       subMain (caseNum-1)