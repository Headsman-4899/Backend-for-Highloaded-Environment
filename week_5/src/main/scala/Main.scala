import scala.util.matching.Regex
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import scala.io.Source

object Main extends App {

  //val source = Source.fromFile("C:\\Users\\user\\Downloads\\raw.txt").getLines.toList

  case class Fun(number: Int, productName: String , times: String, total: Double)


  val data = Array[Fun]()

  data(0) = Fun(1, "Натрия хлорид 0,9% 200 мл, фл", "2,000 x 154,00", 308.00)
  data(1) = Fun(2, "Борный спирт 3%, 20 мл, фл.", "1,000 x 51,00", 51.00)
  data(2) = Fun(3, "Шприц 2 мл, 3-х комп. (Bioject)", "2,000 x 16,00", 32.00)
  data(3) = Fun(4, "Система для инфузии Vogt Medical", "2,000 x 60,00", 120.00)
  data(4) = Fun(5, "Naturella прокладки Классик макси №8", "1,000 x 310,00", 310.00)
  data(5) = Fun(6, "AURA Ватные диски №150", "1,000 x 461,00", 461.00)
  data(6) = Fun(7, "Чистая линия скраб мягкий 50 мл", "1,000 x 381,00", 310.00)
  data(7) = Fun(8, "Чистая линия скраб очищающий абрикос 50 мл", "1,000 x 386,00", 386.00)
  data(8) = Fun(9, "Чистая линия скраб мягкий 50 мл", "1,000 x 381,00", 381.00)
  data(9) = Fun(10, "Carefree салфетки Алоэвоздухопроницаемые №20", "1,000 x 414,00", 414.00)
  data(10) = Fun(11, "Pro Series Шампунь яркий цвет 500мл", "1,000 x 841,00", 841.00)
  data(11) = Fun(12, "Pro Series бальзам-ополаскивательдля длител ухода за окрашеннымиволосами Яркий цвет 500мл", "1,000 x 841,00", 841.00)
  data(12) = Fun(13, "Clear шампунь Актив спорт 2в1мужской 400 мл", "1,000 x 1200,00", 1200.00)
  data(13) = Fun(14, "Bio World (HYDRO THERAPY)Мицеллярная вода 5в1, 445мл", "1,000 x 1 152,00", 1152.00)
  data(14) = Fun(15, "Bio World (HYDRO THERAPY) Гель-муссдля умывания с гиалуроновойкислотой, 250мл", "1,000 x 1 152,00", 1152.00)
  data(15) = Fun(16, "[RX]-Натрия хлорид 0,9%, 100 мл, фл.", "1,000 x 168,00", 168.00)
  data(16) = Fun(17, "[RX]-Дисоль р-р 400 мл, фл.", "1,000 x 163,00", 163.00)
  data(17) = Fun(18, "Тагансорбент с иономи серебра №30,пор.", "1,000 x 1526,00", 1526.00)
  data(18) = Fun(19, "[RX]-Церукал 2%, 2 мл, №10, амп.", "2,000 x 396,00", 792.00)
  data(19) = Fun(20, "[RX]-Андазол 200 мг, №40, табл.", "1,000 x 7 330,00", 7330.00)

//
//  for (i <- 1 to data.length) {
//    Encoder[Fun].apply(data(i)).toString()
//  }
//  Encoder[Fun].apply(data(0)).toString()
////  println(data)
}


