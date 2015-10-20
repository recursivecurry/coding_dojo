-- HACKERRANK: Pentagonal Numbers
-- https://www.hackerrank.com/challenges/pentagonal-numbers

module Main where

import qualified Control.Monad as M


pentagonalNumbers :: [Int]
pentagonalNumbers = let steps = [4, 7..]
                    in 1 : (zipWith (+) steps pentagonalNumbers)


solve :: String -> String
solve input = let inputs = lines input
                  answers = map (\line -> show (pentagonalNumbers !! ((read line :: Int) - 1))) inputs
                  output = unlines answers
              in output


main :: IO ()
main = do getLine
          interact solve

