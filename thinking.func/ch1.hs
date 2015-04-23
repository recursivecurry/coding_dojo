module ThinkingCh1 where

import qualified Data.List as List
import qualified Data.Char as Char

sample = ["a", "b", "d", "b", "a", "a"]

type Text = [Char]
type Word = [Char]

-- map :: (a->b) -> [a] -> [b] from Prelude
-- toLower :: Char -> Char from Data.Char
-- map toLower :: Text -> Text

sortWords :: [Word] -> [Word]
sortWords = List.sort

countRuns :: [Word] -> [(Int,Word)]
countRuns [] = []
countRuns (x:xs) = (hl, x) : countRuns t
                   where hl = 1 + (length . takeWhile (\v -> x==v)) xs
                         t = dropWhile (\v -> x==v) xs
                         
sortRuns :: [(Int,Word)] -> [(Int,Word)]
sortRuns = List.reverse . List.sort

-- take :: Int -> [a] -> [a]

showRun :: (Int,Word) -> String
showRun (x,y) = concat [y, "\t", show(x), "\n"]

-- map showRun :: [(Int,Word)] -> [String]

-- concat :: [[a]] -> [a] from Prelude
commonWords :: Int -> Text -> String
commonWords n = concat . map showRun . take n . 
                sortRuns . countRuns . sortWords . 
                words . map Char.toLower

main :: IO()
main = do putStrLn $ commonWords 2 "cc ba aa ba cc cc"

song :: Int -> String
song n = if n==0 then""
         else song (n-1) ++ verse n ++ "\n"
         
verse :: Int -> String
verse n = line1 n ++ line2 n ++ line3 n ++ line4 n

number = ["","one","two","three","four",
          "five","six","seven","eight","nine"]

man :: Int -> String
man n = number !! n ++ if n == 1 then " man" else " men"

mans :: Int -> String
mans n = (concat . List.intersperse ", " . map man . reverse)  [1..n]

cap :: String -> String
cap [] = []
cap (x:xs) = Char.toUpper x : xs

line1 :: Int -> String
line1 n = cap $ man n ++ " went to mow\n"

line2 :: Int -> String
line2 n = "Went to mow a meadow\n"

line3 :: Int -> String
line3 n = cap $ mans n ++ " and his dog\n"

line4 :: Int -> String
line4 n = "Went to mow a meadow\n" 

anagrams :: Int -> [Word] -> String
anagrams n = List.concat . List.intersperse "\n" . map showItem . groupItems . sortItems . map normalize . take n

normalize :: Word -> (Word,Word)
normalize w = (List.sort w, w)

sortItems :: [(Word,Word)] -> [(Word,Word)]
sortItems = List.sort

groupItems :: [(Word,Word)] -> [(Word,[Word])]
groupItems [] = []
groupItems xxs@((n,w):xs) = (n, hl) : groupItems t
                   where hl = (map snd . takeWhile (\(v,vs) -> n==v)) xxs
                         t = dropWhile (\(v,vs) -> n==v) xs

showItem :: (Word,[Word]) -> String
showItem (x,xs) = show . List.concat $ x:": ":(List.intersperse "," xs)

