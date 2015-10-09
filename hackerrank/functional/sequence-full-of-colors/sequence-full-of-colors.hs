module Main where

check :: Maybe (Int,Int,Int,Int) -> Char -> Maybe (Int,Int,Int,Int)
check Nothing c = Nothing
check (Just (r,g,y,b)) c
    | 1 < (abs (nr - ng)) = Nothing
    | 1 < (abs (ny - nb)) = Nothing
    | otherwise = Just (nr, ng, ny, nb)
  where (nr,ng,ny,nb) = case c of
                          'R' -> (r+1,g,y,b)
                          'G' -> (r,g+1,y,b)
                          'Y' -> (r,g,y+1,b)
                          'B' -> (r,g,y,b+1)

check2 :: Maybe (Int,Int,Int,Int) -> Maybe Bool
check2 Nothing = Nothing
check2 (Just (r,g,y,b)) = if r == g && y == b then Just True else Nothing

main :: IO ()
main = do tStr <- getLine
          let t = read tStr :: Int
          mapM_ (\_ -> getLine >>= \s -> let r = check2 $ foldl check (Just (0,0,0,0)) s
                                          in case r of
                                               Just _ -> putStrLn "True"
                                               Nothing -> putStrLn "False"
                                               ) [1..t]
