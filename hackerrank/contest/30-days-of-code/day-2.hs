module Main where

main :: IO ()
main = do meal <- getLine
          tip <- getLine
          tax <- getLine
          let [mv, tv, xv] = map (read :: String -> Float) [meal, tip, tax]
          let pay = show $ round (mv + (mv * tv / 100) + (mv * xv / 100))
          putStrLn $ concat ["The final price of the meal is $", pay, "."]
