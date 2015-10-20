-- HACKERRANK: Pentagonal Numbers
-- https://www.hackerrank.com/challenges/pentagonal-numbers

module Main where

import qualified Control.Monad as M


pentagonalNumbers :: [Int]
pentagonalNumbers = let steps = [4, 7..]
                    in 1 : (zipWith (+) steps pentagonalNumbers)

solve :: IO ()
solve = do nStr <- getLine
           let answer = pentagonalNumbers !! ((read nStr :: Int) - 1)
           putStrLn $ show answer


main :: IO ()
main = do nStr <- getLine
          M.replicateM_ (read nStr :: Int) solve
