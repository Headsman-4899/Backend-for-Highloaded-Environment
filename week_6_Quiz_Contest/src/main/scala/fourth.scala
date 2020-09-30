//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}

object fourth {
  def longestUnivaluePath(root: TreeNode): Int = {
    var maxUnivaluePath = 0
    def longestUnivaluePathStartAt(root: TreeNode): Int = {
      if (root == null) {
        return 0
      }
      var Left = longestUnivaluePathStartAt(root.left)
      var Right = longestUnivaluePathStartAt(root.right)

      if (root.left != null && root.value == root.left.value) Left += 1
      else Left = 0

      if (root.right != null && root.value == root.right.value) Right += 1
      else Right = 0

      maxUnivaluePath = Math.max(maxUnivaluePath, Left + Right)

      return Math.max(Left, Right)
    }

    longestUnivaluePathStartAt(root)
    maxUnivaluePath
  }

}
