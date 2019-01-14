/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumNumbers(root *TreeNode) int {
  return sum(root, 0)
}

func sum(root *TreeNode, acc int) int {
  if root == nil {
    return 0
  }
  acc = acc * 10 + root.Val
  if root.Left == nil && root.Right == nil {
    return acc
  }
  
  ret := 0
  if root.Left != nil {
    ret += sum(root.Left, acc)
  }
  if root.Right != nil {
    ret += sum(root.Right, acc)
  }
  return ret
}
