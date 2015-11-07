module Main where

import Data.Array
import Control.Monad (replicateM_)


numberOfTree :: Array Int Int
numberOfTree = listArray (0, 1000) numberOfTreeList
               where numberOfTreeList = map numberOfTree' [0..1000]
                     numberOfTree' 0 = 1
                     numberOfTree' 1 = 1
                     numberOfTree' n = mod (sum [(numberOfTree ! l) * (numberOfTree ! (n-1-l)) | l <- [0..(n-1)]]) (10^8+7)


main :: IO ()
main = getLine >>= \t -> replicateM_ (read t :: Int) (getLine >>= \n -> putStrLn $ show (numberOfTree ! (read n :: Int)))
