module Main where

sumOfPowers :: Int -> Int -> Int
sumOfPowers n p = sumOfPowers' n p 1
                  where sumOfPowers' n p c = if n == 0
                                                then 1
                                                else if n < c ^ p
                                                      then 0
                                                      else (sumOfPowers' (n - c ^ p) p (c+1)) + (sumOfPowers' n p (c+1))


main :: IO ()
main = do nStr <- getLine
          pStr <- getLine
          putStrLn $ show (sumOfPowers (read nStr :: Int) (read pStr :: Int))
