-- HACKERRANK: Pentagonal Numbers
-- https://www.hackerrank.com/challenges/pentagonal-numbers

module Main where

import qualified Control.Monad as M
import Data.Array


pentagonalNumbers :: Array Int Int
pentagonalNumbers = listArray (1, 100000) pentagonalNumberList
                    where pentagonalNumberList = let steps = [4, 7..]
                                                 in 1 : (zipWith (+) steps pentagonalNumberList)

solve :: IO ()
solve = do nStr <- getLine
           let answer = pentagonalNumbers ! ((read nStr :: Int))
           putStrLn $ show answer


main :: IO ()
main = do nStr <- getLine
          M.replicateM_ (read nStr :: Int) solve
