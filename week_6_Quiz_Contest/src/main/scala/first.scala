import javax.swing.tree.TreeNode

class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
  var value: Int = _value
  var left: TreeNode = _left
  var right: TreeNode = _right
}

object first extends App {
  def rangeSumBST(root: TreeNode, L: Int, R: Int): Int = {
    if (root == null) 0

    else if (root.value < L) {
      rangeSumBST(root.right, L, R)
    }
    else if (root.value > R) {
      rangeSumBST(root.left, L, R)
    }

    else root.value + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R)
  }
}