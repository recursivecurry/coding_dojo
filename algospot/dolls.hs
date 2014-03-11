-- ALGOSPOT - 인형
-- http://algospot.com/judge/problem/read/DOLLS
module Main where

import Data.List (sort)
import Control.Monad

removeLess :: Int -> [Int] -> [Int]
removeLess _ [] = []
removeLess n xs = [y-n | y<-xs, y > n]

removeSome :: Int -> [Int] -> [Int]
removeSome x xs = [minus y x | y <- xs]
                  where minus a b
                            | a < b = 0
                            | otherwise = a - b

addSome :: Int -> [Int] -> [Int] -> [Int]
addSome n dolls results = [add (x,y)| (x,y) <- zip dolls results]
                         where add (doll,result)
                                   | doll >= n = result + n
                                   | otherwise = result + doll

addLast :: Int -> [Int] -> [Int] -> [Int]
addLast remained (doll:dolls) (result:results)
    | (&&) (remained > 0) (doll > 0) = (result+1) : addLast (remained-1) dolls results
    | remained > 0 = result : addLast remained dolls results
    | otherwise = result:results
addLast _ _ xs = xs

_select :: Int -> Int -> [Int] -> [Int] -> [Int] -> [Int]
_select kind remained dolls (headSorted:sorted) results
    | remained >= kind * headSorted = _select (length (removeLess headSorted sorted)) (remained - (kind*headSorted)) (removeSome headSorted dolls) (removeLess headSorted sorted) (addSome headSorted dolls results)
    | remained < kind = addLast remained dolls results
    | otherwise = _select (length (removeLess (div remained kind) (headSorted:sorted))) (remained - (div remained kind * kind)) (removeSome (div remained kind) dolls) (removeLess (div remained kind) (headSorted:sorted)) (addSome (div remained kind) dolls results)
_select _ _ _ [] xs = xs

select :: Int -> Int -> [Int] -> [Int]
select kind remained dolls = _select kind remained dolls (sort dolls) [0|_<-dolls]

printList :: [Int] -> IO ()
printList [x] = print x
printList (x:xs) = do putStr (show x ++ " ")
                      printList xs

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
           remained = read (words line1 !! 1) :: Int
           dolls = [read x :: Int|x<-words line2]
       printList (select kind remained dolls)
       subMain (caseNum-1)