module Main where

solve :: String -> String
solve ss = let l = length ss
            in map (\(i,c) -> if (mod i (l+1) == 0) then ' ' else c) $ take ((l+1)*l) $ zip [1..] $ drop 1 $ cycle ss

main :: IO ()
main = do tStr <- getLine
          let t = read tStr :: Int
          mapM_ (\_ -> (getLine >>= \s -> putStrLn $ solve s)) [1..t]
