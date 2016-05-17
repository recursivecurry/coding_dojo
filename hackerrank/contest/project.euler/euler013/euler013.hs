module Euler013 where

import Control.Monad

solve :: [Integer] -> Integer
solve = foldr (\v a -> v + a) 0

main :: IO ()
main = do n <- readLn :: IO Int
          lines <- replicateM n getLine
          let answer = (solve . map read) lines
          putStrLn $ take 10 $ show answer
