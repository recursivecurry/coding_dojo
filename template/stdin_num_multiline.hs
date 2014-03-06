module Main where

import Control.Monad

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
       let kind = read (head (words line1)) :: Int
           remained = read ((words line1) !! 1) :: Int
           dolls = [read x :: Int|x<-words line2]
       print (show (echo kind remained dolls))
       subMain (caseNum-1)

echo :: Int -> Int -> [Int] -> [Int]
echo x y z = x : y : z