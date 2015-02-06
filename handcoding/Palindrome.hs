import Control.Monad

type Suffix = String
type Input = String
type Output = Maybe String
type Chance = Int


makePalindrome' :: Suffix -> Chance -> Input -> Output
makePalindrome' _ (-1) _ = Nothing
makePalindrome' suffix chance input = case input of
  (_:_:_) -> if (head input) /= (last input)
             then mplus (makePalindrome' ((head input):suffix) (chance-1) (tail input))
                        (makePalindrome' ((last input):suffix) (chance-1) (init input))
             else makePalindrome' ((head input):suffix) chance ((init . tail) input)
  [ic] -> Just $ (reverse suffix) ++ (replicate (chance+1) ic) ++ suffix
  [] -> Just $ (reverse suffix) ++ (replicate chance 'a') ++ suffix


makePalindrome :: String -> String
makePalindrome input = case (makePalindrome' "" 1 input) of
                         Nothing -> "NA"
                         Just str -> str
