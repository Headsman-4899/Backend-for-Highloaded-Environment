
object eight {
  def findSolution(customfunction: CustomFunction, z: Int): List[List[Int]] = {

    var a = List[List[Int]]()

    for (i <- 1 until 1000; j <- 1 until 100) {
      if (customfunction.f(i, j) == z) {
        a = a :+ List(i, j)
      }
    }
    a
  }
}
