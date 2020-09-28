object First extends App {

  def maxProduct(nums: Array[Int]): Int = {
    nums.sorted.slice(nums.length-2, nums.length).reduce((a, b) => (a-1) * (b-1))
  }

  val test1 = Array(3,4,5,2)
  val test2 = Array(1,5,4,5)
  val test3 = Array(3,7)

  println(maxProduct(test1))
  println(maxProduct(test2))
  println(maxProduct(test3))
}
