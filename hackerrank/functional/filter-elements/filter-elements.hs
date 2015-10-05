-- HACKERRANK: Filter Elements
-- https://www.hackerrank.com/challenges/filter-elements
module Main where

--import qualified Data.List.Split as S
import qualified Data.Set as S
import Data.List
import qualified Control.Monad as M


splitBy delimiter = foldr f [[]]
            where f c l@(x:xs) | c == delimiter = []:l
                               | otherwise = (c:x):xs


solve :: Int -> [Int] -> S.Set Int-> [Int]
solve _ [] _ = []
solve k xss@(x:xs) ys = if S.notMember x ys && selected
                        then x : (solve k xs (S.insert x ys))
                        else solve k xs ys
                          where
                            selected = check x 0 xss
                            check x c [] = False
                            check x c (a:as) = if x == a
                                                  then if k-1<=c
                                                          then True
                                                          else check x (c+1) as
                                                  else check x c as


solveCase :: IO ()
solveCase = do
  nkStr <- getLine
  let (_:k:_) = map (read::String->Int) $ splitBy ' ' nkStr
  asStr <- getLine
  let as = map (read::String->Int) $ splitBy ' ' asStr
  let rs = solve k as (S.empty)
  putStrLn $ if rs == [] then "-1" else intercalate " " (map show rs)


main :: IO ()
main = do
  ts <- getLine
  M.replicateM_ (read ts) solveCase
--main = getLine >>= \n -> replicateM (read n) getLine >>= \acts -> mapM_ (print . echo . read) acts
