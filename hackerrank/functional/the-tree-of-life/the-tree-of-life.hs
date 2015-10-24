module Main where

import qualified Text.Parsec as P
import qualified Text.Parsec.String as S
import qualified Text.Parsec.Char as CH
import qualified Text.Parsec.Combinator as CB
import qualified Control.Monad as M
import qualified Data.Array as A


-- TREE PARSING
data TreeNode = Branch TreeNode Bool TreeNode | Leaf Bool deriving Show


value :: S.Parser Bool
value = do v <- (CH.char '.') P.<|> (CH.char 'X')
           return (if v == 'X' then True else False)

leaf :: S.Parser TreeNode
leaf = do v <- value
          return (Leaf v)

branch :: S.Parser TreeNode
branch = do CH.char '('
            left <- branch P.<|> leaf
            root <- value
            right <- branch P.<|> leaf
            CH.char ')'
            return (Branch left root right)

treenode :: S.Parser TreeNode
treenode = do t <- branch
              P.many (CH.string "\n" P.<|> CH.string "\r" P.<|> CH.string "\r\n")
              return  t

parseTree :: String -> Maybe TreeNode
parseTree s = case P.parse treenode "" s of
                Left _ -> Nothing
                Right t -> Just t

-- RULE GENERATION
rule :: (Num i, A.Ix i) => Int -> A.Array i Bool
rule n =  A.listArray (0,15) $ reverse (toBin 16 n)

toBin :: Int -> Int -> [Bool]
toBin p 0 = replicate p False
toBin p n = reverse (helper n p)
          where helper 0 r = replicate r False
                helper n r | n `mod` 2 == 1 = True : helper (n `div` 2) (r-1)
                           | n `mod` 2 == 0 = False : helper (n `div` 2) (r-1)


-- PATH PARSING
path :: S.Parser (Int, String)
path = do n <- (CB.many1 CH.digit)
          CH.spaces
          CH.char '['
          p <- (P.many (CH.oneOf "<>"))
          CH.char ']'
          return ((read n :: Int), p)


parsePath :: String -> Maybe (Int, String)
parsePath s = case P.parse path "" s of
                Left _ -> Nothing
                Right v -> Just v

-- EVALUATE
evaluate ::(Num i, A.Ix i) => A.Array i Bool -> TreeNode -> TreeNode
evaluate = undefined

main :: IO ()
main = do ruleStr <- getLine
          pathStr <- getLine
          caseStr <- getLine
          putStrLn caseStr

