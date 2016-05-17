module Main where

import Control.Monad


solve :: String -> String
solve ps =
  balance ps ""
  where balance :: String -> String -> String
        balance [] [] = "YES"
        balance [] _ = "NO"
        balance (p:ps) sst = case sst of
                             [] -> balance ps [p]
                             (s:st) -> if match p s
                                       then balance ps st
                                       else balance ps (p:s:st)
        match :: Char -> Char -> Bool
        match '}' '{' = True
        match ']' '[' = True
        match ')' '(' = True
        match _ _ = False

main :: IO ()
main = do n <- readLn :: IO Int
          replicateM_ n $ getLine >>= (putStrLn . solve)
