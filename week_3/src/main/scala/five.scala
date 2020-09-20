import scala.collection.mutable.ArrayBuffer

object five {
  def decompressRLElist(nums: Array[Int]): Array[Int] = {
    var result = ArrayBuffer[Int]()
    for (i <- 0 until nums.length by 2) {
      result ++= ArrayBuffer.fill(nums(i))(nums(i+1))
    }
    result.toArray
  }
}