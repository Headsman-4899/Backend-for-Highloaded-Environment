object Fourth extends App {
  def findPairs(nums: Array[Int], k: Int): Int = {
    val sortedNums = nums.sorted
    val util = scala.collection.mutable.HashSet[(Int, Int)]();

    for (i <- 0 until sortedNums.length) {
      for (j <- i until sortedNums.length) {
        if (i != j && sortedNums(j) - sortedNums(i) == k) {
          util += sortedNums(j) -> sortedNums(i)
        }
      }
    }
    util.size
  }

  val a = Array(1,2,3,4,5)
  val k = 1
  println(findPairs(a, k))
}
