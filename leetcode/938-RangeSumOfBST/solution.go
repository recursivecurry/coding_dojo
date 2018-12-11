/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rangeSumBST(root *TreeNode, L int, R int) int {
  if root == nil {
    return 0
  }
  return sum(root, L, R)
}

func sum(root *TreeNode, L int, R int) int {
  if root == nil {
    return 0
  }
  total := 0
  if L <= root.Val && root.Val <= R {
    total += root.Val
  }
  if L <= root.Val {
    total += sum(root.Left, L, R)
  }
  if root.Val <= R {
    total += sum(root.Right, L, R)
  }
  return total
}
