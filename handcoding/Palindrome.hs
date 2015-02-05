import Control.Monad

type Prefix = String
type Input = String
type Output = Maybe String
type Chance = Int

makePalindrome' :: Prefix -> Chance -> Input -> Output
makePalindrome' prefix chance []
  | chance < 0 = Nothing
  | chance > 0 = Nothing
makePalindrome' prefix chance input
  | chance < 0 = Nothing
  | otherwise = do r <- makePalindrome' firstCharacter chance ((init . tail) input)
                   return r
  | firstCharacter /= lastCharacter = mplus (makePalindrome' firstCharacter (chance-1) (tail input)) (makePalindrome' lastCharacter (chance-1) (init input))
  where firstCharacter = head input
        lastCharacter = last input

makePalindrome :: String -> String
makePalindrome input = case (makePalindrome' '' 1 input) of
                         Nothing -> "NA"
                         Just str -> str
