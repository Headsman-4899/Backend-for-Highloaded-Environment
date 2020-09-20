object three {
  def smallerNumbersThanCurrent(nums: Array[Int]): Array[Int] = {
    nums.map(
      num => nums.count(i => num > i)
    )
  }
}
