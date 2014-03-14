-- ALGOSPOT - Endians
-- http://algospot.com/judge/problem/read/ENDIANS
module Main where

import Control.Monad
import Data.Word
import Data.Bits

main :: IO ()
main = getLine >>= \n -> replicateM (read n) getLine >>= \acts -> mapM_ (print . convertEndian . read) acts

convertEndian :: Word32 -> Word32
convertEndian x = (shift ((.&.) x (shift 255 24)) (-24)) + (shift ((.&.) x (shift 255 16)) (-8)) + (shift ((.&.) x (shift 255 8)) (8)) + (shift ((.&.) x (shift 255 0)) (24))
