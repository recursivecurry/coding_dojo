module Main where

isDifficult :: Int -> Int -> Int -> Int -> Int -> Int -> String
isDifficult st1 p1 t1 st2 p2 t2 = isDifficult' (time st1 p1 t1) (time st2 p2 t2)
                                  where time = \st p t -> [st+p*n | n<-[0..(t-1)]]

isDifficult' :: [Int] -> [Int] -> String
isDifficult' (x:xs) (y:ys)
  | x == y = "Difficult"
  | x < y = isDifficult' xs (y:ys)
  | otherwise = isDifficult' (x:xs) ys
isDifficult' _ _ = "Easy"

main :: IO ()
main = do putStrLn $ isDifficult 1 3 10 3 5 10
          putStrLn $ isDifficult 1 3 10 1000 5 10
          putStrLn $ isDifficult 2 2 10 3 2 10
          putStrLn $ isDifficult 2 2 10 3 2 10
