-- HACKERRANK: The Tree Of Life
-- https://www.hackerrank.com/challenges/the-tree-of-life
module Main where

import Text.Read (readPrec, lift)
import Text.ParserCombinators.ReadP (ReadP, char)
import Control.Applicative
import qualified Control.Monad as M
import qualified Data.Array as A
import Data.Bits

type Status = Char
type Path = String
type Step = Int

data TreeNode = Branch TreeNode Status TreeNode | Leaf Status deriving Show

type Trees = A.Array Int TreeNode


-- TREE PARSING

instance Read TreeNode where
  readPrec = lift tree

tree :: ReadP TreeNode
tree = Leaf <$> node
   <|> Branch <$ char '(' <*> tree <* char ' ' <*> node <* char ' ' <*> tree <* char ')'

node :: ReadP Status
node = char 'X' <|> char '.'


nv :: TreeNode -> Status
nv (Leaf n) = n
nv (Branch _ n _) = n

s2i :: Status -> Int
s2i 'X' = 1
s2i _ =  0


-- RULE GENERATION
rule :: Int -> Status -> TreeNode -> Status
rule n parent current = case current of
                          Leaf current -> if (2 ^ ((2^3) * (s2i parent) + 2 * (s2i current)) .&. n) == 0 then '.' else 'X'
                          Branch left current right -> if (2 ^ ((2^3) * (s2i parent) + (2^2) * (s2i (nv left)) + 2 * (s2i current) + (s2i (nv right))) .&. n) == 0 then '.' else 'X'


-- PATH PARSING

parseCondition :: String -> (Step, String)
parseCondition s = ((read stepStr :: Int), path)
                   where stepStr:pathStr:_ = splitBy ' ' s
                         path = (tail . init) pathStr
                         splitBy delimiter = foldr f [[]]
                                             where f c l@(x:xs) | c == delimiter = []:l
                                                                | otherwise = (c:x):xs


-- GET VALUE OF PATH
pathValue :: TreeNode -> Path -> Status
pathValue tree [] = nv tree
pathValue (Branch left current right) pps@(p:ps) = case p of
                                                     '<' -> pathValue left ps
                                                     '>' -> pathValue right ps
pathValue _ _ = undefined


-- EVALUATE
next :: (Status -> TreeNode -> Status) -> Status -> TreeNode -> TreeNode
next r parent currentNode = case currentNode of
                              Branch left value right -> Branch (next r value left) (r parent currentNode) (next r value right)
                              Leaf value -> Leaf (r parent currentNode)

trees :: (Status -> TreeNode -> Status) -> TreeNode -> Trees
trees rules initialTree = A.listArray (0, 1000 * 1100) treeList
                          where treeList = initialTree : map (next rules '.') treeList

main :: IO ()
main = do ruleStr <- getLine
          let selectedRule = rule (read ruleStr :: Int)
          treeStr <- getLine
          let initialTree = read treeStr :: TreeNode
          let selectedTrees = trees selectedRule initialTree
          caseStr <- getLine
          let caseNum = read caseStr :: Int
          M.foldM_ (\prevStep i -> do conditionStr <- getLine
                                      let (selectedStep, selectedPath) = parseCondition conditionStr
                                      let currentStep = prevStep + selectedStep
                                      let selectedValue = pathValue (selectedTrees A.! currentStep) selectedPath
                                      putStrLn $ [selectedValue]
                                      return currentStep) 0 [0..(caseNum-1)]
