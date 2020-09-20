object ten {
  def buildArray(target: Array[Int], n: Int): List[String] = {
    var res = List[String]()

    var ptr = 0

    for (i <- 1 until n+1) {
      if (target(ptr) == i) {
        res = res :+ "Push"
        ptr += 1
        if (ptr == target.length) return res
      }
      else {
        res = res :+ "Push" :+ "Pop"
      }
    }
    res
  }
}
