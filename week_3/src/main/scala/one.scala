object one {
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): Array[Boolean] = {
    val max = candies.max
    candies.map { candy => candy + extraCandies >= max }
  }
}