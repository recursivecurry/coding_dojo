module Main where
import Control.Monad

main :: IO ()
main = getLine >>= \n -> replicateM (read n) getLine >>= \acts -> mapM_ (print . echo . read) acts

-- echo :: Int -> Int
echo :: String -> String
echo x = x
