object Sum extends App {

  val nums = Array[Int](1, 2, 3, 4)

//  for (i <- 0 to (nums.length-1)) {
//    nums(i).readInt()
//  }

  for (i <- 1 until nums.length) {
    nums(i) = nums(i-1) + nums(i)
  }

  for (i <- nums){
    println(i)
  }
}
