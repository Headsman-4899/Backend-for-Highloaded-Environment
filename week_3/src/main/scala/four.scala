
object four {
  def repeatedNTimes(A: Array[Int]): Int = {
    for (a <- A)
      if (A.groupBy(identity).mapValues(_.size)(a) > 1)
        return a
    return -1
  }
}
