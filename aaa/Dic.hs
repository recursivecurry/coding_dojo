module Dic where

countK1 :: Int -> Int -> String
countK1 = undefined

type Start = Int
type End = Int
type Index = Int
type AreaStart = Int
type AreaEnd = Int
type Count = Int
type NumberWord = Int

countK2 :: Start -> Index -> End -> NumberWord
countK2 = countK2' 0 1 1 1

countK2' :: Count -> Index -> AreaStart -> AreaEnd -> Start -> End -> NumberWord
countK2' c idx as ae st ed
  | (st <= as && as <= ed) || (st <= ae && ae <= ed) =
  | ae < st = countK2' c idx (as * 10) (ae * 10 + 9) st ed
  | idx < 9 = countK2' c (idx+1) (idx+1) (idx+1) st ed
  | otherwise = undefined
