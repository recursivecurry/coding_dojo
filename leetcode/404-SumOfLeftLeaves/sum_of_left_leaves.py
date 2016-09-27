# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def sumOfLeftLeavesRec(self, root, left):
        if root is None:
            return 0
        elif root.left is None and root.right is None:
            if left:
                return root.val
            else:
                return 0
        else:
            return self.sumOfLeftLeavesRec(root.left, True) + self.sumOfLeftLeavesRec(root.right, False)
    def sumOfLeftLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        return self.sumOfLeftLeavesRec(root, False)
