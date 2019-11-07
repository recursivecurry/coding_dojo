module Main where

import qualified Control.Monad as M
import qualified Data.List as L
import qualified Debug.Trace as D

readMapping :: IO (String, String)
readMapping = do
  l <- getLine
  let [k, v] = words l
    in return (k, v)

comp :: (String, String) -> Maybe (String, String) -> Maybe (String, String)
comp (a1, a2) (Just (b1, b2)) =
  if a1 == b1
  then if a2 == b2 then Just (a1, a2) else Nothing
  else Just (a1, a2)
comp _ _ = Nothing

check :: [(String, String)] -> String
check xs =  case (foldr comp (Just ("", "")) xs) of
  Just _ -> "YES"
  _ -> "NO"

solve :: IO String
solve = do
  l <- getLine
  ms <- M.replicateM (read l :: Int) readMapping
  return $ check (L.sort ms)


main :: IO ()
main = do
  l <- getLine
  result <- M.replicateM (read l :: Int) solve
  mapM_ putStrLn result
