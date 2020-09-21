import scala.io.StdIn.readInt

object Main extends App {
  var x = false
  def Rec(list: List[Int], num: Int):Boolean = {
    if(list.head == num) {
      x = true
    }
    if (list.tail.size != 0) {
      x = Rec(list.tail, num)
    }
    x
  }

  val nums = List[Int](12, 22, 10, 8, 56, 34, 11)
  val num = 0
  val answer = Rec(nums ,num)
  println(answer)
}
