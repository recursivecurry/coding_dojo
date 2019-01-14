/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxPathSum(root *TreeNode) int {
  mv, _ := search(root)
  return mv
}

func search(root *TreeNode) (maxValue, maxPath int) {
  if root == nil {
    return math.MinInt64, 0
  } 
  if root.Left == nil && root.Right == nil {
    return root.Val, max(root.Val, 0)
  }
  
  lv, lp := search(root.Left)
  rv, rp := search(root.Right)
  
  maxPath = max(max(lp + root.Val, rp + root.Val), 0)
  maxValue = max(max(lv, rv), lp+rp+root.Val)
  return
}

func max(a, b int) int {
  if a > b {
    return a
  }
  return b
}
