object fifth extends App {
  def subarraySum(nums: Array[Int], k: Int): Int = {
    var cnt = 0

    for (i <- 0 to nums.length-1) {
      var sum = 0
      for (j <- i to nums.length-1) {
        sum += nums(j)
        if (sum == k) {
          cnt += 1
        }
      }
    }
    cnt
  }

  val test1 = Array(1,1,1)
  val k = 2
  println(subarraySum(test1, k))
}
