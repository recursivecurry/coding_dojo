module Main where

import qualified Data.List as List
import Control.Applicative


readPacketList :: Int -> IO [(Int, Int)]
readPacketList num = if 0 == num
                     then return []
                     else do
                      l <- getLine
                      let a:b:[] = [read x :: Int| x <- words l]
                      ((a,b) :) <$> readPacketList (num-1)

findPrev :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
findPrev (_,_) [] = []
findPrev (a,b) ((0,0):ps) = (a,b):findPrev (a,b) ps
findPrev (a,b) ((0,pb):ps) = (a,pb+b-1):findPrev (a,b) ps
findPrev (a,b) ((pa,0):ps) = (pa+a-1,b):findPrev (a,b) ps
findPrev (a,b) ((pa,pb):ps) = (pa+a,pb+b-1):(pa+a-1,pb+b):findPrev (a,b) ps

main :: IO ()
main =
    do line <- getLine
       let timeNum = read line :: Int
       packetList <- readPacketList timeNum
       let (fsta,fstb) = head packetList
           others = tail packetList
       print $ findMax others [(fsta,fstb)] (max fsta fstb)

findMax :: [(Int,Int)] -> [(Int,Int)] -> Int -> Int
findMax [] _ maxVal = maxVal
findMax (p:ps) prev maxVal = findMax ps uniquePrev nextMaxVal
                             where uniquePrev = List.nub (findPrev p prev)
                                   nextMaxVal = max maxVal $ minimum [max a b|(a,b)<-uniquePrev]

