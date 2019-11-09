{-# LANGUAGE FlexibleInstances, UndecidableInstances, DuplicateRecordFields #-}

module Main where
 
helloWorldNTimes :: [String] -> [String]
helloWorldNTimes xs = xs >>= (\s -> replicate (read s :: Int) "Hello World")


main :: IO ()
main = interact (unlines . helloWorldNTimes . lines)
