type Book = Int
type BookWeight = Int
type UpperWeight = Int
type TotalWeight = Int

replaceAt :: Int -> a -> [a] -> [a]
replaceAt i v xs = pre ++ (v:suf)
                   where (pre, _:suf) = splitAt i xs

calcMinimumWeight :: [Book] -> [BookWeight] -> TotalWeight
calcMinimumWeight bs = calcMinimumWeight' (replicate (length bs) 0) bs

calcMinimumWeight' :: [UpperWeight] -> [Book] -> [BookWeight] -> TotalWeight
calcMinimumWeight' _ [] _ = 0
calcMinimumWeight' uws (b:bs) bws = uws !! b + calcMinimumWeight' uws' bs bws
                                    where uws' = replaceAt b 0 $ map (\x -> x + bws !! b) uws
