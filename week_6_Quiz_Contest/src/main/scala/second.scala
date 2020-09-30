object second extends App {
  def tribonacci(n: Int): Int = {
    if (n < 2) n

    else {
      var array = Array(0,1,1)

      for(i <- 2 until n) {
        array = array :+ (array(i) + array(i-1) + array(i-2))
      }
      array(n)
    }
  }

  println(tribonacci(25))
}
