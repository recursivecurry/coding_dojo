-- HACKERRANK: Prefix Compression
-- https://www.hackerrank.com/challenges/prefix-compression

module Main where


solve :: String -> String -> (Int,String,Int,String,Int,String)
solve [] ys = (0,[],0,[],length ys,ys)
solve xs [] = (0,[],length xs,xs,0,[])
solve xss@(x:xs) yss@(y:ys) = if x == y
                                 then let (n1,p1,n2,p2,n3,p3) = solve xs ys
                                       in (1+n1, (x:p1), n2, p2, n3, p3)
                                 else (0,[],length xss,xss,length yss,yss)


main :: IO ()
main = do
  l1 <- getLine
  l2 <- getLine
  let (n1, p1, n2, p2, n3, p3) = solve l1 l2
  putStr (show n1)
  putStr " "
  putStrLn (p1)
  putStr (show n2)
  putStr " "
  putStrLn (p2)
  putStr (show n3)
  putStr " "
  putStrLn (p3)
