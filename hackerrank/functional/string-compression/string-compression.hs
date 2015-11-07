module Main where

import qualified Data.List as L


compress :: String -> String
compress s = concat $ compress' s
             where compress' [] = [""]
                   compress' xss@(x:xs) = [x]:(if xNum == 1 then "" else show xNum):compress' (dropWhile (== x) xss)
                                          where xNum = length $ takeWhile (== x) xss

compress2 :: String -> String
compress2 s = concat $ map convert groupedList
              where groupedList = L.group s
                    convert [c] = [c]
                    convert css@(c:cs) = c:(show $ length css)

main :: IO ()
main = interact compress2
