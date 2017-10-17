/**
 * Definition for a binary tree node.
 * class TreeNode(var _value: Int) {
 *   var value: Int = _value
 *   var left: TreeNode = null
 *   var right: TreeNode = null
 * }
 */
object Solution {
    def isSameTree(p: TreeNode, q: TreeNode): Boolean = {
        if (p == null && q == null) {
            true
        } else if (p == null) {
            false
        } else if (q == null) {
            false
        } else if (p.value == q.value) {
            if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
                true
            } else {
                false
            }
        } else
            false
    }
}
