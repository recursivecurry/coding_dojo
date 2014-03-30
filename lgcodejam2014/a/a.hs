module Main where

import qualified Data.List as List
import qualified Data.Map as Map
import Control.Applicative


readRectList :: Int -> IO [(Int,Int,Int,Int)]
readRectList num = if 0 == num
                     then return []
                     else do
                      l <- getLine
                      let ax:ay:bx:by:[] = [read x :: Int| x <- words l]
                      ((ax,ay,bx,by):) <$> readRectList (num-1)

x1 :: (Int,Int,Int,Int) -> Int
x1 (x,_,_,_) = x

y1 :: (Int,Int,Int,Int) -> Int
y1 (_,y,_,_) = y

x2 :: (Int,Int,Int,Int) -> Int
x2 (_,_,x,_) = x

y2 :: (Int,Int,Int,Int) -> Int
y2 (_,_,_,y) = y

main :: IO ()
main =
    do line <- getLine
       let rectNum = read line :: Int
       rectList <- readRectList rectNum
       let (headRect:tailRects) = List.sort rectList
           (count,size) = solve [headRect] tailRects (Map.fromList [((x1 headRect, x2 headRect),0)]) (y1 headRect) [y2 headRect] (0, 0)
       putStr (show count)
       putStr " "
       print size

solve :: [(Int,Int,Int,Int)] -> [(Int,Int,Int,Int)] -> Map.Map (Int,Int) Int -> Int -> [Int] -> (Int,Int) -> (Int,Int)
solve [lr] [] area prev [y] (count,size) = (count+1, max size (((y-prev)* (x2 lr-x1 lr))+prevSize))
                                           where prevSize = head (Map.elems area)
--solve active rect:rects area prev y (count,size)
--    | y1 rect < head y = solve nextActive rects nextArea nextPrev nextY nextRet
--                         where nextActive = -- (head y) 에 해당하는 것을 active에서 제거하고 해당되는
--                               nextArea
--    | y1 rect > head y = solve nextActive rect:rects nextArea nextPrev nextY nextRet
--                         where nextA