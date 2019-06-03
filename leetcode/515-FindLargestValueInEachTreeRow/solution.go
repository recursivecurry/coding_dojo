/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func largestValues(root *TreeNode) []int {
  if root == nil {
    return []int{}
  }
  nodes := []*TreeNode{root}
  output := make([]int, 0)
  for len(nodes) > 0 {
    max := nodes[0].Val
    next := make([]*TreeNode, 0, len(nodes)*2)
    for _, node := range nodes {
      if node.Val > max {
        max = node.Val
      }
      if node.Left != nil {
        next = append(next, node.Left)
      }
      if node.Right != nil {
        next = append(next, node.Right)
      }
    }
    nodes = next
    output = append(output, max)
  }
  return output
}
