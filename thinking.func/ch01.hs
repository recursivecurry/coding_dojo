module ThinkingFunctionally where

commonWordsSample = "  the: 154\n  of: 50\n  a: 18\n  and: 12\n  in: 11\n"

type Text = [Char]
type Word = [Char]

commonWords :: Int -> Text -> String
commonWords n = concat . map showRun . take n .
                sortRuns . countRuns. sortWords . words . toLower

showRun :: (Int, Word) -> Word
showRun = undefined

toLower :: Word -> Word
toLower = undefined

sortWords :: [Word] -> [Word]
sortWords = undefined

countRuns :: [Word] -> [(Int, Word)]
countRuns = undefined

sortRuns :: [(Int, Word)] -> [(Int, Word)]
sortRuns = undefined

main :: IO()
main = do putStrLn "Hello"
