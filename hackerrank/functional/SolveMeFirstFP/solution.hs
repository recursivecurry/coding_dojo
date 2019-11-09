module Main where
  
main :: IO ()
main = do
  readLn >>= \first -> readLn >>= \second -> print (first + second)
