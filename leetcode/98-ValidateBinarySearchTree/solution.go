/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isValidBST1(root *TreeNode) bool {
  seq := make([]int, 0)
  iter1(root, &seq)
  prev := int(math.MinInt64)
  for _, v := range seq {
    if v <= prev {
      return false
    }
    prev = v
  }
  return true
}

func iter1(root *TreeNode, seq *[]int) {
  if root == nil {
    return
  }
  if root.Left != nil {
    iter1(root.Left, seq)
  }
  *seq = append(*seq, root.Val)
  if root.Right != nil {
    iter1(root.Right, seq)
  }
}

func isValidBST2(root *TreeNode) bool {
  if root == nil {
    return true
  }
  _, _, valid := iter2(root)
  return valid
}

func iter2(root *TreeNode) (int, int, bool) {
  var l1, r2 int
  var valid bool
  if root.Left != nil {
    var l2 int
    l1, l2, valid = iter2(root.Left)
    if !valid || l2 >= root.Val {
      return 0, 0, false
    }
  } else {
    l1 = root.Val
  }
  if root.Right != nil {
    var r1 int
    r1, r2, valid = iter2(root.Right)
    if !valid || r1 <= root.Val {
      return 0, 0, false
    }
  } else {
    r2 = root.Val
  }
  return l1, r2, true
}
