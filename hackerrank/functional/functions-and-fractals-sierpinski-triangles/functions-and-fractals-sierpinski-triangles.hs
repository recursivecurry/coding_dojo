-- HACKERRANK: Functions and Fractals: Sierpinski triangles
-- https://www.hackerrank.com/challenges/functions-and-fractals-sierpinski-triangles
module Main where

type Row = Int
type Column = Int
type Size = Int
type Step = Int

data Triangle = Triangle { row :: Int
                         , column :: Int
                         , size :: Int
                         } deriving (Show)

maxRow = 31
maxColumn = 62

splitTriangle :: Triangle -> [Triangle]
splitTriangle t = [topTriangle, leftTriangle, rightTriangle]
                  where topTriangle = Triangle (row t) (column t) splittedSize
                        leftTriangle = Triangle (row t + splittedSize) (column t - splittedSize) splittedSize
                        rightTriangle = Triangle (row t + splittedSize) (column t + splittedSize) splittedSize
                        splittedSize = div (size t) 2

triangles :: Step -> [Triangle]
triangles 0 = [Triangle 0 31 32]
triangles s = concatMap splitTriangle $ triangles (s-1)

rowText :: Row -> [Triangle] -> String
rowText r ts = [if or $ map (isIn (r, c)) ts then '1' else '_' | c <- [0..maxColumn]]
               where isIn (rp, cp) t = (row t <= rp)
                                    && (rp < (row t + size t))
                                    && (column t - (rp - (row t)) <= cp)
                                    && (cp <= column t + (rp - (row t)))

triangleText :: Step -> String
triangleText step = unlines [rowText r (triangles step) | r <- [0..maxRow]]

main :: IO ()
main = do nStr <- getLine
          let n = read nStr :: Int
          putStrLn $ triangleText n
