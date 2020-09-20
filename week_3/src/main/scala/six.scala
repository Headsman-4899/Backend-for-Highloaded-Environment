object six {
  def sumZero(n: Int): Array[Int] = {
    val result = Array.ofDim[Int](n)
    if (n == 1) {
      return Array(0)
    }

    for (i <- 0 until n) {
      result(i) = if (i % 2 == 0) (i / 2) + 1
      else -((i / 2) + 1)
    }
    if (n % 2 == 1) {
      result(n-1) = 0
    }
    return result
  }
}
