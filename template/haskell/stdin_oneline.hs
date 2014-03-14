module Main where

main :: IO ()
main = do
    interact (unlines . map echo . lines)

echo :: String -> String
echo x = x
