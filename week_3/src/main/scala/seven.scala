object seven {
  def kWeakestRows(mat: Array[Array[Int]], k: Int): Array[Int] = {

    var soldiers = Array.ofDim[Int](mat.size)

    for (column <- 0 until mat.size; row <- 0 until mat.size) {
      if (mat(column)(row) == 1) soldiers(column) += 1
    }
    //var sortSoldiers = soldiers.sorted

    soldiers.zipWithIndex.sortBy(c => c._1).take(k).unzip._2
  }
}
