module Main where

import qualified Text.Parsec as P
import qualified Text.Parsec.String as S
import qualified Text.Parsec.Char as CH
import qualified Text.Parsec.Combinator as CB
import qualified Control.Monad as M
import qualified Data.Array as A
import Debug.Trace

type Status = Char
type Path = String
type Step = Int

data TreeNode = Branch TreeNode Status TreeNode | Leaf Status deriving Show

type Rules = A.Array Int Status
type Trees = A.Array Int TreeNode


-- TREE PARSING

value :: S.Parser Status
value = do v <- (CH.char '.') P.<|> (CH.char 'X')
           return v

leaf :: S.Parser TreeNode
leaf = do v <- value
          return (Leaf v)

branch :: S.Parser TreeNode
branch = do CH.char '('
            left <- branch P.<|> leaf
            CH.spaces
            root <- value
            CH.spaces
            right <- branch P.<|> leaf
            CH.char ')'
            return (Branch left root right)

treenode :: S.Parser TreeNode
treenode = do t <- branch
              P.many (CH.string "\n" P.<|> CH.string "\r" P.<|> CH.string "\r\n")
              return  t

parseTree :: String -> TreeNode
parseTree s = case P.parse treenode "" s of
                Left _ -> Leaf '.'
                Right t -> t

nodeValue :: TreeNode -> Status
nodeValue (Leaf n) = n
nodeValue (Branch _ n _) = n

statusToInt :: Status -> Int
statusToInt 'X' = 1
statusToInt _ =  0


-- RULE GENERATION
rule :: Int -> Rules
rule n =  A.listArray (0,15) $ reverse (toBin 16 n)

ruleValue :: Rules -> Status -> TreeNode -> Status
ruleValue r parent currentNode = case currentNode of
                                Leaf current -> r A.! ((2^3) * (statusToInt parent) + 2 * (statusToInt current))
                                Branch left current right -> r A.! ((2^3) * (statusToInt parent) + (2^2) * (statusToInt (nodeValue left)) + 2 * (statusToInt current) + (statusToInt (nodeValue right)))

toBin :: Int -> Int -> [Status]
toBin p n = reverse (helper n p)
          where helper 0 r = replicate r '.'
                helper n r | n `mod` 2 == 1 = 'X' : helper (n `div` 2) (r-1)
                           | n `mod` 2 == 0 = '.' : helper (n `div` 2) (r-1)


-- PATH PARSING
path :: S.Parser (Step, String)
path = do n <- (CB.many1 CH.digit)
          CH.spaces
          CH.char '['
          p <- (P.many (CH.oneOf "<>"))
          CH.char ']'
          return ((read n :: Int), p)


parseCondition :: String -> (Step, String)
parseCondition s = case P.parse path "" s of
                Left _ -> (-1, "")
                Right v -> v

pathValue :: TreeNode -> Path -> Status
pathValue tree [] = nodeValue tree
pathValue (Branch left current right) pps@(p:ps) = case p of
                                                     '<' -> pathValue left ps
                                                     '>' -> pathValue right ps
pathValue _ _ = undefined

-- EVALUATE
next :: Rules -> Status -> TreeNode -> TreeNode
next r parent currentNode = case currentNode of
                              Branch left value right -> Branch (next r value left) (ruleValue r parent currentNode) (next r value right)
                              Leaf value -> Leaf (ruleValue r parent currentNode)

trees :: Rules -> TreeNode -> Trees
trees rules initialTree = A.listArray (0, 1000 * 1100) treeList
                          where treeList = initialTree : map (next rules '.') treeList

main :: IO ()
main = do ruleStr <- getLine
          let selectedRules = rule (read ruleStr :: Int)
          treeStr <- getLine
          let initialTree = parseTree treeStr
          let selectedTrees = trees selectedRules initialTree
          caseStr <- getLine
          let caseNum = read caseStr :: Int
          M.foldM_ (\prevStep i -> do conditionStr <- getLine
                                      let (selectedStep, selectedPath) = parseCondition conditionStr
                                      let currentStep = prevStep + selectedStep
                                      let selectedValue = pathValue (selectedTrees A.! currentStep) selectedPath
                                      putStrLn $ [selectedValue]
                                      return currentStep) 0 [0..(caseNum-1)]
