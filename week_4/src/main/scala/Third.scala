object Third extends App {
  val res = Array("Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday")
  val mon = Array(0,31,28,31,30,31,30,31,31,30,31,30,31)

  def dayOfTheWeek(day: Int, month: Int, year: Int): String = {
    var days = 0
    days += days_before_year(year)
    if (is_leap(year)) {
      mon(2) = 29
    }
    days += days_before_month(month)
    days += day - 1

    res(days % 7)
  }

  def days_before_month(m: Int): Int = {
    var sum = 0
    for (i <- 1 until m) {
      sum += mon(i)
    }
    sum
  }

  def days_before_year(y: Int): Int = {
    var sum = 0
    for (i <- 1971 until y) {
      sum += (if (is_leap(i)) 366 else 365)
    }
    sum
  }

  def is_leap(y:Int): Boolean = {
    if (y % 400 == 0) return true
    if (y % 4 == 0) return true
    false
  }


  println(dayOfTheWeek(18, 7, 1999))
}
