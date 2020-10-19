import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import scala.io.Source
import scala.util.matching.Regex


object SecondMain extends App {

  val filename = "C:\\Users\\user\\Downloads\\raw1.txt"

  sealed trait Check
  case class Product(productName: String, amountAndCost: String, total: String) extends Check

  var ans = Array[Check]()
  var keyVal: Regex = "[0-9]*[.]".r
  val line = scala.io.Source.fromFile(filename).mkString
  var lines = Array[String]()
  lines = line.split("\n")


  for(i <- 0 until lines.length) {
    if(keyVal.matches(lines(i))) {
      val p: Check = Product(lines(i+1),lines(i+2),lines(i+3))
      ans = ans :+ p
    }
  }
  val json = ans.asJson
  println(json)

//  val decodedJson = decode[Check](json)
//  println(decodedJson)


}
