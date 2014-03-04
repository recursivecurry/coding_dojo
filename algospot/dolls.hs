-- ALGOSPOT - 인형
-- http://algospot.com/judge/problem/read/DOLLS
module Main where

removeLess :: Int -> [Int] -> [Int]
removeLess n (_:xs) = [y | y<-xs, y > n]
removeLess _ [] = []

removeSome :: Int -> [Int] -> [Int]
removeSome x xs = [minus y x | y <- xs]
                  where minus a b
                            | a <= b = 0
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

select :: Int -> Int -> [Int] -> [Int] -> [Int] -> [Int]
select kind remained dolls (headSorted:sorted) results
    | remained >= kind * headSorted = select (length (removeLess headSorted sorted)) (remained - headSorted) (removeSome headSorted dolls) (removeLess headSorted sorted) (addSome headSorted dolls results)
    | remained < kind = addLast remained dolls results
    | otherwise = select (length (removeLess (div remained kind) sorted)) (mod remained kind) (removeSome (div remained kind) dolls) (removeLess (div remained kind) sorted) (addSome (div remained kind) dolls results)

main = undefined