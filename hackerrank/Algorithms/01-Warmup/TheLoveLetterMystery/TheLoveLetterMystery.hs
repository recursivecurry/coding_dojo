-- HACKERRANK - The Love-Letter Mystery
-- https://www.hackerrank.com/challenges/the-love-letter-mystery
module Main where

import Control.Monad
import Data.Char

main :: IO ()
main =
    do line <- getLine
       let caseNum = read line :: Int
       subMain caseNum

subMain :: Int -> IO()
subMain caseNum =
    unless  (0 == caseNum) $
    do line <- getLine
       let zipped = take (div (length line) 2) (zip line (reverse line))
           count = sum [abs(ord a - ord b)|(a,b) <- zipped]
       print (id count)
       subMain (caseNum-1)