import scala.annotation.tailrec

object Beginning1 {

  def main(args: Array[String]): Unit = {

    // т.з. : нам нужно получить сок из ящика яблок (списка яблок)

    // Что такое яблоко?
    println()
    println("Apple")

    //class - это чертеж, по чертежу можно сделать экземпляр класса
    // class Apple - это чертеж яблока, по этому чертежу можно сделать яблоко!(вот это поворот картинка )
    case class Apple(color: String, size: Int)

    val apple1: Apple = Apple("green", 2) //Создали первое яблоко

    // тип на самом деле не обязательно указывать, мы будем указывать для лучшего понимания
    val apple2 = Apple("red", 3) //Создали второе яблоко

    println(apple1) //Распечатали первое яблоко
    println(apple2) //Распечатали второе яблоко

    // Что такое ящик?
    println()
    println("Box as List")

    //Список - это класс который хранит несколько элементов одного типа
    //Допустим, что ящик это список
    val listEmpty: List[Nothing] = List() // создали пустой список
    val listInt: List[Int] = List(1, 2, 10) //создали список чисел
    val listString: List[String] = List("Hello", "world") //создали список строк
    val listApple: List[Apple] = List(Apple("green", 2), Apple("red", 3)) //создали список яблок


    println(listEmpty)
    println(listInt)
    println(listString)
    println(listApple)

    //как из списка яблок получить сок?
    //1) нужно каждого яблока порцию сока
    //2) нужно все порции сока обьединить

    // картинка ка мы будет это делать!!!!!
    // вход список яблок - выход один сок
    // расписать на картинке 2 варианта как можно сделать это, сказать что мы будем делать одним из варинатов более сложным

    //Что нам еще может пригодиться?

    //Класс GlassOfJuice хранит обьем сока
    case class GlassOfJuice(volume: Int)

    // соковыжималка
    case class Juicer() {

      // squeezeTheJuice - метод класса, действие которое можно сделать с соковыжималкой
      //  помещяем яблоко в соковыжималку и получаем сок по формуле
      def squeezeTheJuice(apple: Apple): GlassOfJuice = GlassOfJuice(apple.size * 100)
    }

    println()
    println("Squeeze The GlassOfJuice")
    //Как из одного яблока получить 1 порцию сока?

    val oneApple: Apple = Apple("green", 2)
    val juicer: Juicer = Juicer()
    val onePortionJuice: GlassOfJuice = juicer.squeezeTheJuice(oneApple)

    println(oneApple)
    println(onePortionJuice)

    //Как из списка яблока получить список порций сока?

    val apples: List[Apple] = List(Apple("green", 2), Apple("red", 3), Apple("red", 2), Apple("green", 1))
    //    val juicer = Juicer()
    // У List есть метод map, которому нужно сказать как преобразовать 1 элемент списка (передать функцию)
    // Мы говорим, что хотим получить из одного яблока один сок, и это применяется для каждого элемента
    val cupsOfJuice: List[GlassOfJuice] = apples.map(apple => juicer.squeezeTheJuice(apple))

    println(apples)
    println(cupsOfJuice)

    println()
    println("Union GlassOfJuice")
    //Как объединить все порции сока?
    // У List есть метод reduce, которому нужно сказать как обьединить 2 элемента списка в 1 элемент (передать функцию)
    val allJuice: GlassOfJuice = cupsOfJuice.reduce((juiceL, juiceR) => GlassOfJuice(juiceL.volume + juiceR.volume))

    println(cupsOfJuice)
    println(allJuice)

    // у нас получился сок (мистер бин пьет сок!!)

    //Мы молодцы!!! получили сок
    //Но что делать, если среди яблок есть испорченное?
    //Давайте договоримся не использовать испорченное яблоко при производстве сока
    println()
    println("Rotten apple")
    // Apple("grey", 3)- испорченное яблоко
    val applesWithRotten: List[Apple] = List(Apple("green", 2), Apple("grey", 3), Apple("red", 2))

    // У List есть метод filter, которому нужно сказать  элемент списка подходит или нет (передать функцию)
    val applesGood: List[Apple] = applesWithRotten.filter(apple => apple.color != "grey")

    println(applesWithRotten)
    println(applesGood)
    // мы свели текущую задучу, к той, которую решали!!!

    //Что делать, если яблоки  хранится в ящике, который в свою очередь находится в ящике?
    // Шутка про пакет, в котором хранятся все пакеты
    println()
    println("Composite list")

    val applesNested1: List[List[Apple]] = List(List(Apple("green", 2), Apple("grey", 3), Apple("red", 2)))

    // Убирает 1 вложенность, в данном случае List
    val apples1: List[Apple] = applesNested1.flatten

    println(applesNested1)
    println(apples1)

    val applesNested2: List[List[Apple]] = List(List(Apple("green", 2)), List(Apple("grey", 3), Apple("red", 2)))

    val apples2: List[Apple] = applesNested2.flatten

    println(applesNested2)
    println(apples2)

    println()
    println("Flatten + Map = FlatMap")

    println()
    println("Suprise")
    println("Функциональный мир - неизменяемый мир")



    //Функциональный мир - неизменяемый мир

    // и яблоки и сок и т.д все показать на картинке
    //surprise motherfucker !!! у нас старые обьекты не менялись !!! поэтому все яблоки у нас остались + появился сок
    //Да, детка , это мир функционального программирования!!!
    // Добавить мистера Бинау
    // Добавить Дикаприо и Мак Конахи


    // очень похоже на карты карно, составление курса, только 1 изменение на каждой стадии


    // Что если нам нужно из списка стаканов сока получить список с упаковками сока обьемом  1 литр
    // Есть как минимум 2 варианта решения:
    // 1) обьединить весь сок в 1 емкость и разллить его по стаканам + показать
    // 2) из списка стаканов напрямую получать список соков(мы это сделаем)


    // хвостовая рекурсия
    // мемас https://www.google.com/search?q=%D0%BC%D0%B5%D0%BC+%D0%B4%D0%B5%D0%B2%D0%BE%D1%87%D0%BA%D0%B0+%D0%B2+%D0%B6%D0%B5%D0%BB%D1%82%D0%BE%D0%BC&client=ubuntu&hs=ITz&channel=fs&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiHzILaps_hAhXwpIsKHftDAzYQ_AUIDigB&biw=1600&bih=798#imgrc=dfFD2DnVX_1RAM:


    // 2 примера - 1 простой 1 сложный
    println()
    println("Tail Recursion simple example")

    // рекурсия - когда функция вызывает сама себя напрямую или косвенно

    def sumNotTailRecursion(list: List[Int]): Int = {
      if (list.isEmpty) 0
      else if (list.size == 1) list.head
      else
      // последняя операция - сумма сначала нужно найти list.head и sumNotTailRecursion(list.tail), потом их сложить
        list.head + sumNotTailRecursion(list.tail)
    }

    @tailrec
    def sumTailRecursion(list: List[Int], sumAccumulator: Int): Int = {
      if (list.isEmpty) {
        sumAccumulator
      } else {
        //Список = голова списка (один элемент) + хвост списка
        val sumNow: Int = list.head + sumAccumulator //голова списка + общая сумма
        // последняя операция - вызов функции sumRecursion(tail, sumNow), из за этого это хвостовая рекурсия
        // хвостовая рекурсия под "капотом" разворачивается в цикл
        sumTailRecursion(list.tail, sumNow) //list.tail - хвост списка
      }
    }

    val listNumber: List[Int] = List(1, 2, 4, 5)

    val sum1: Int = listNumber.reduce((x, y) => x + y)

    val sum2: Int = sumNotTailRecursion(listNumber)

    val sum3: Int = sumTailRecursion(listNumber, 0)

    println(listNumber)
    println(sum1)
    println(sum2)
    println(sum3)

    println()
    println("Tail Recursion not simple example")


    case class JuicePack(volume: Int)

    val volumeJuicePacking: Int = 500

    @tailrec
    def getJuicePacks(cupsOfJuice: List[GlassOfJuice], restVolumeJuice: Int, juicePackAccumulator: List[JuicePack]): List[JuicePack] = {
      // алгоритм максимально упрощенный, поэтому не на всех примерах он будет правильно работать
      if (cupsOfJuice.isEmpty) {
        juicePackAccumulator
      } else {
        val sumRestVolumeJuice: Int = restVolumeJuice + cupsOfJuice.head.volume
        if (sumRestVolumeJuice >= volumeJuicePacking) {
          val restVolumeJuiceNow: Int = sumRestVolumeJuice - volumeJuicePacking
          val juicePackAccumulatorNow: List[JuicePack] = JuicePack(volumeJuicePacking) :: juicePackAccumulator
          getJuicePacks(cupsOfJuice.tail, restVolumeJuiceNow, juicePackAccumulatorNow)
        } else {
          getJuicePacks(cupsOfJuice.tail, sumRestVolumeJuice, juicePackAccumulator)
        }
      }
      // алгоритм максимально упрощенный, поэтому не на всех примерах он будет правильно работать

    }

    val glassesOfJuice: List[GlassOfJuice] = List(100, 300, 200, 400, 450, 50).map(volume => GlassOfJuice(volume))

    val juicePacks: List[JuicePack] = getJuicePacks(glassesOfJuice, 0, List.empty)

    println(glassesOfJuice)
    println(juicePacks)

  }

}
