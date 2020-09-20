
//class ListNode(_x: Int = 0, _next: ListNode = null) {
//  var next: ListNode = _next
//  var x: Int = _x
//}

object two extends App {
  def getDecimalValue(head: ListNode): Int = {
    var x = 0
    var i = head
    while(i.next!=null) {
      x+=1
      i=i.next
    }
    if (head.next != null) {
      head.x*scala.math.pow(2,x).toInt+getDecimalValue(head.next)
    } else {
      head.x
    }
  }
}