-- HACKERRANK: Fibonacci
-- https://www.hackerrank.com/challenges/fibonacci-fp

module Main where

import qualified Control.Monad as M
import Data.Array


fibNumbers :: Array Int Int
fibNumbers = listArray (0, 10000) fibNumberList
                    where fibNumberList = 0 : 1 : zipWith (\x y -> mod (x + y) 100000007) fibNumberList (drop 1 fibNumberList)

solve :: IO ()
solve = do nStr <- getLine
           let answer = fibNumbers ! ((read nStr :: Int))
           putStrLn $ show answer


main :: IO ()
main = do nStr <- getLine
          M.replicateM_ (read nStr :: Int) solve
