class Solution {
    class Some () {
        val parent = IntArray(26) {it}
        val rank = IntArray(26) {it}

        fun union(a: Int, b: Int): Unit {
            val pa = find(a)
            val pb = find(b)
            if (rank[a] > rank[b]) {
                parent[pb] = pa
                rank[pa] += rank[pb]
            } else {
                parent[pa] = pb
                rank[pb] += rank[pa]
            }

        }

        fun find (n: Int): Int {
            var p = parent[n]
            while (p != parent[p]) {
                parent[p] = parent[parent[p]]
                p = parent[p]
            }
            return p
        }
    }
    fun equationsPossible(equations: Array<String>): Boolean {
        val s = Some()
        for (eq in equations) {
            if (eq[1] == '=') {
                s.union(eq[0] - 'a', eq[3] - 'a')
            }
        }
        for (eq in equations) {
            if (eq[1] == '!') {
                val a = s.find(eq[0] - 'a')
                val b = s.find(eq[3] - 'a')
                if (a == b) {
                    return false
                }
            }
        }
        return true
    }
}

//class Solution {
//    fun CharArray.replace(from: Char, to: Char) {
//        for (i in this.indices) {
//            if (this[i] == from) {
//                this[i] = to
//            }
//        }
//    }
//
//
//    fun equationsPossible(equations: Array<String>): Boolean {
//        val vars = "abcdefghijklmnopqrstuvwxyz".toCharArray()
//        val vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
//        for (rel in equations) {
//            val v1 = rel[0].toInt() - 'a'.toInt();
//            val v2 = rel[3].toInt() - 'a'.toInt();
//            if (rel[1] == '=') {
//                if (vals[v1] != vals[v2]) {
//                    vals.replace(vals[v1], vals[v2])
//                }
//            }
//        }
//        for (rel in equations) {
//            val v1 = rel[0].toInt() - 'a'.toInt();
//            val v2 = rel[3].toInt() - 'a'.toInt();
//            if (rel[1] == '!') {
//                if (vals[v1] == vals[v2]) {
//                    return false
//                }
//            }
//        }
//        return true
//    }
//}
