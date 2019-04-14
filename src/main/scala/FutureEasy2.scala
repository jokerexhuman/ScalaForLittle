import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


object FutureEasy2 {

  /**
    * copy from http://biercoff.com/easily-measuring-code-execution-time-in-scala/
    */
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1e9 + "sec")
    result
  }

  def main(args: Array[String]): Unit = {


    println()
    println("Future")
    // Проблема: если получения сока занимает долгое время  время , то как можно ускорить работу?
    // Одно из возможных решений - использовать Future, этот класс поможет сделать код асинхронным и параллельным,
    // также в нем есть методы map, flatMap, filter.


    case class Apple(color: String, size: Int)

    case class GlassOfJuice(volume: Int)

    // соковыжималка
    case class Juicer() {

      def squeezeTheJuice(apple: Apple): GlassOfJuice = {
        Thread.sleep(4000) // sleep 4 sec
        GlassOfJuice(apple.size * 100)
      }
    }

    val apples = List(Apple("green", 2), Apple("red", 3), Apple("red", 2), Apple("green", 1))


    println(apples)
    println()

    // В нашем случае Juicer().squeezeTheJuice(apple) работает долго
    //Время работы Elapsed time: 16.006446792sec
    time {
      val cupsOfJuice: List[GlassOfJuice] = apples.map(apple => Juicer().squeezeTheJuice(apple))

      val allJuice: GlassOfJuice = cupsOfJuice.reduce((juiceL, juiceR) => GlassOfJuice(juiceL.volume + juiceR.volume))

      println(allJuice)
    }

    import scala.concurrent.ExecutionContext.Implicits.global // необходим для работы Future

    //Время работы Elapsed time: 4.072226708sec
    time {
      val cupsOfJuice: List[Future[GlassOfJuice]] = apples.map(apple => Future(Juicer().squeezeTheJuice(apple)))
      //  каррирование:  reduceLeft(...)(...)
      val allJuiceInFuture: Future[GlassOfJuice] = Future.reduceLeft(cupsOfJuice)((juiceL, juiceR) => GlassOfJuice(juiceL.volume + juiceR.volume))

      // Так делать не нужно, особенно в проде !!! это для примера!!!
      // Получем результат блокируемым образом. Из ассинхронного кода - получаем синхронный.
      val allJuice: GlassOfJuice = Await.result(allJuiceInFuture, Duration.Inf) // Так делать не нужно, особенно в проде !!! это для примера!!!
      println(allJuice)
    }


    // useful https://eax.me/scala-futures/
    // useful https://twitter.github.io/scala_school/ru/basics.html
  }

}
