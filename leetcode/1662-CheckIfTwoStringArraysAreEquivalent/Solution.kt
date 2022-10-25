class Solution {
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        val ws1 = word1.flatMap {
            it.asIterable()
        }.iterator()
        val ws2 = word2.flatMap {
            it.asIterable()
        }.iterator()
        val result = sequence {
            while (ws1.hasNext() && ws2.hasNext()) {
                yield(Pair(ws1.next(), ws2.next()))
            }

            if (ws1.hasNext() || ws2.hasNext()) {
                yield(Pair('a', 'b'))
            }
        }
//        val result ws1.zip(ws2) // zip doesn't work for the lists of different length
        return result.all {
            it.first == it.second
        }
    }
}
