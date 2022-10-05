import kotlin.math.abs

class Solution {
    class Heap(val ns: IntArray, val x: Int) {
        var size = ns.size

        init {
            for (n in (size / 2) - 1 downTo 0) {
                shiftDown(n)
            }
        }

        inline fun leftChild(n: Int): Int = 2 * n + 1
        inline fun rightChild(n: Int): Int = 2 * n + 2
        inline fun Int.isCloserThan(x: Int, other: Int): Boolean =
            (abs(this - x) < abs(other - x)) || (abs(this - x) == abs(other - x) && this < other)

        fun shiftDown(n: Int): Unit {
            var cur = n
            while (cur < this.size / 2) {
                val child =
                    if (rightChild(cur) < this.size) {
                        if (ns[leftChild(cur)].isCloserThan(x, ns[rightChild(cur)]))
                            leftChild(cur)
                        else
                            rightChild(cur)
                    } else {
                        leftChild(cur)
                    }
                if (ns[child].isCloserThan(x, ns[cur])) {
                    val temp = ns[cur]
                    ns[cur] = ns[child]
                    ns[child] = temp
                }
                cur = child
            }
        }

        fun pop(): Unit {
            val temp = ns[size-1]
            ns[size-1] = ns[0]
            ns[0] = temp
            size--
            shiftDown(0)
        }

    }
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        val heap = Heap(arr, x)
        for (i in 0 until k) {
            heap.pop()
        }
//        println("ns: ${heap.ns.joinToString()} k: $k, x: $x")
        return heap.ns.toList().asReversed().take(k).sorted()
    }

}
