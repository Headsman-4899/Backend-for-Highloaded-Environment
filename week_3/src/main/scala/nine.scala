object nine {
  def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    //nums1.filter(nums2.contains(_)).distinct
    var a = Array[Int]()

    for (i <- 0 until nums1.size) {
      for (j <- 0 until nums2.size) {
        if (nums1(i) == nums2(j)) {
          a = a :+ nums2(j)
        }
      }
    }
    a.distinct
  }
}
