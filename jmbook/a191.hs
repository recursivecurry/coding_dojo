module Main where

import Debug.Trace

reverse_quardtree :: [Char] -> ([Char], [Char])
reverse_quardtree [] = ([], [])
reverse_quardtree (x:xs) = case x of
                 'x' -> (['x'] ++ left_top ++ right_top ++ left_bottom ++ right_bottom, xs'''')
                   where
                     (left_bottom, xs') = reverse_quardtree xs
                     (right_bottom, xs'') = reverse_quardtree xs'
                     (left_top, xs''') = reverse_quardtree xs''
                     (right_top, xs'''') = reverse_quardtree xs'''
                 _ -> ([x], xs)

solve :: [Char] -> [Char]
solve xs = fst $ reverse_quardtree xs

main :: IO ()
main = do s <- getLine
          print $ solve s
