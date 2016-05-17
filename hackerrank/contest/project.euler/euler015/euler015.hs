module Main where

import Data.Map
import Control.Monad
import Data.Text

pathMap :: Map (Int, Int) Int
pathMap = fromList [((x,y), paths x y) | x <- [1..500], y <- [1..500]]

paths :: Int -> Int -> Int
paths 1 1 = 2
paths 1 n = pathMap ! ((n-1), 1) + 1
paths n 1 = pathMap ! ((n-1), 1) + 1
paths m n = mod ((pathMap ! ((m-1), n)) + (pathMap ! (m, (n-1)))) 1000000007

solve :: String -> String
solve s = show $ paths (read $ unpack x) (read $ unpack y)
          where x:y:_ = splitOn (pack " ") (pack s)

main :: IO ()
main = do n <- readLn :: IO Int
          replicateM_ n (getLine >>= (putStrLn . solve))
