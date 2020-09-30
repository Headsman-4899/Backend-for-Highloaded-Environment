//class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
//  var value: Int = _value
//  var left: TreeNode = _left
//  var right: TreeNode = _right
//}

object third extends App {
  def minDiffInBST(root: TreeNode): Int = {
    var arr = Array[Int]()

    def fun(node: TreeNode = root): Unit = {
      if (node != null) {
        fun(node.left)
        arr :+= node.value
        fun(node.right)
      }
    }

    fun()
    (0 until arr.length - 1).map(i => arr(i + 1) - arr(i)).min
  }
}
