package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_Test {

  def main(args: Array[String]): Unit = {

    /**
      * 第一步： 获取sparkContext对象
      */
    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD_Test.class")
    val sc: SparkContext = new SparkContext(sparkConf)


    val list = List(1,2,3,4,5,6,7,8,9,10)
    val rdd1: RDD[Int] = sc.makeRDD(list)
    val rdd11: RDD[Int] = sc.makeRDD(list, 4)

    val list2 = List("hello a", "hello b", "hello c")
    val rdd2: RDD[String] = sc.makeRDD(list2)

    val result = 1 to 100
    val rdd3:RDD[Int] = sc.makeRDD(result)

    val result1 = 5 to 15
    val rdd4:RDD[Int] = sc.makeRDD(result1)


    val result5 = List(("a", 1),("c", 4), ("a", 3), ("b", 2), ("b", 3),
      ("c", 3), ("d", 4))
    val rdd5:RDD[(String, Int)] = sc.makeRDD(result5)


    val result6 = List(("a", "A"), ("a", "A"), ("a", "B"), ("b", "A"), ("b", "B"),
      ("c", "B"), ("c", "A"), ("d", "C"), ("d", "C"), ("d", "C"), ("d", "C"), ("d", "B"))
    val rdd6:RDD[(String, String)] = sc.makeRDD(result6)



    val result7 = List(
      ("a",34,3),
      ("d",5,33),
      ("d",44,33),
      ("b",22,77),
      ("b",1,6),
      ("d",7,9),
      ("a",34,33)
    )
    val rdd7:RDD[(String, Int, Int)] = sc.makeRDD(result7)


    val result8 = List(("a", 1), ("a", 3), ("b",2))
    val result9 = List(("a", 11), ("a", 44), ("b",22))
    val rdd8:RDD[(String, Int)] = sc.makeRDD(result8)
    val rdd9:RDD[(String, Int)] = sc.makeRDD(result9)





    /**
      * map
      */
    //    val f1 = (x:Int) => x * x
//    val rdd2: RDD[Int] = rdd1.map(f1)
//    rdd2.foreach(x => println(x))


    /**
      * filter
      */
    //    rdd1.filter( x => if(x % 2 == 0) true else false).foreach(println)


    /**
      * flatMap
      */
    //    rdd2.flatMap((line:String) => line.split(" ")).foreach(x => println(x))


    /**
      * mapPartitions  其实这个算子，每次交给f的数组就是一个分区
      */
    //    val lastValue: RDD[Int] = rdd11.mapPartitions((x: Iterator[Int]) => {
//      val partition_sum: Int = x.sum
//      val resultList = List(partition_sum)
//      resultList.iterator
//    })
//    lastValue.foreach(println)


    /**
      * mapPartitionsWithIndex  每次遍历一个分区，还给一个参数就是分区编号
      *                分区编号，分区的总和
      */
    //    val resultRDD: RDD[(Int, Int)] = rdd11.mapPartitionsWithIndex((index, x) => {
//      val partition_sum: Int = x.sum
//      val resultList = List((index, partition_sum))
//      resultList.iterator
//    })
//    resultRDD.foreach((x: (Int, Int)) =>  println(x._1, x._2))


    /**
      * sample(withReplacement, fraction, seed)  抽样
      *  三个参数的意义：表示有无放回，  抽取比率，  种子
      *  第二个参数，抽取比率， 每个元素被抽取到的概率， 从所有元素中抽取的元素的比率
      */
    //    val sample_result: RDD[Int] = rdd3.sample(true, 0.2, 0)
//    sample_result.foreach(println)


    /**
      * union 两个结果组成一个结果
      */
    //    rdd1.union(rdd4).foreach(x => println(x))


    /**
      * intersection 求交集
      */
    //  rdd1.intersection(rdd4).foreach(x => println(x))


    /**
      * distinct
      */
    //    val resultRDD: RDD[Int] = rdd1.union(rdd4)
//    // distict中药传入一个分区数， 一般来说，distict的结果集肯定要比原始数据集要小
//    // numPartitions参数一般来说要比元数据集中的分区数要小一些。
//    resultRDD.distinct(2).foreach(x => println(x))


    /**
      * groupByKey
      */
    //    val resultRDD: RDD[(String, Iterable[Int])] = rdd5.groupByKey()
//    resultRDD.foreach(x => {
//      println(x._1, x._2.mkString("-"))
//    })


    /**
      * reduceByKey  ===  groupByKey + reduce/map
      */
    //    rdd5.reduceByKey( (a, b) => a + b).foreach(x => println(x._1, x._2))


    /**
      * aggregate 做聚合的。 rdd1.aggregate(zeroVlaue)(seqOp, combOp)
      */
//    val result_avg: (Int, Int) = rdd1.aggregate((0, 0))(
//      //  U        Int           U
//      //  (6, 3),  4     ==>  (10 , 4)
//      (sum_count, number) => (sum_count._1 + number, sum_count._2 + 1),
//      (a1, a2) => (a1._1 + a2._1, a1._2 + a2._2)
//    )
//    println(result_avg._1 * 1D / result_avg._2)


    /**
      * aggregateByKey,  按照key进行了分组之后，对每一组的元素进行聚合
      */
//    val result_key_value: RDD[(String, Int)] = rdd5.aggregateByKey(0)(
//      (sum, number) => sum + number,
//      (sum1, sum2) => sum1 + sum2
//    )
//    result_key_value.foreach(x => println(x._1, x._2))

//    val result_key_value: RDD[(String, Int)] = rdd5.aggregateByKey(0)(
//      (number1, number) => if(number1 > number) number1 else number,
//      (number1, number) => if(number1 > number) number1 else number
//    )
//    result_key_value.foreach(x => println(x._1, x._2))

    // sum, count, avg, max, min , distinct


    /**
      * combineByKey
      *
      * 需求：求出 RDD6中的每个key中的每个value的出现次数
      *
      * //  String => C
      * //  C, String  ===>  C
      * //  C， C  ===>  C
      *
      * (String, (String, Int))
      *   key     value    count
      *
      * createCombiner: combineByKey() 会遍历分区中的所有元素，因此每个元素的键要么还没有遇到过，要么就
      * 和之前的某个元素的键相同。如果这是一个新的元素， combineByKey() 会使用一个叫作 createCombiner() 的函数来创建
      * 那个键对应的累加器的初始值
      * mergeValue: 如果这是一个在处理当前分区之前已经遇到的键， 它会使用 mergeValue() 方法将该键的累加器对应的当前值与这个新的值进行合并
      * mergeCombiners: 由于每个分区都是独立处理的， 因此对于同一个键可以有多个累加器。如果有两个或者更
      * 多的分区都有对应同一个键的累加器， 就需要使用用户提供的 mergeCombiners() 方法将各
      * 个分区的结果进行合并。
      */
//    val result_key_value_count: RDD[(String, (String, Int))] = rdd6.combineByKey(
//      (value: String) => (value, 1),
//      (c: (String, Int), value: String) => (c._1, c._2 + 1),
//      (c: (String, Int), d: (String, Int)) => (c._1, c._2 + d._2)
//    )
//    result_key_value_count.foreach(x => {
//      println(x._1, x._2._1, x._2._2)
//    })


    /**
      * sortByKey
      * sortBy
      */
    //    rdd5.sortByKey(false).foreach(x => println(x))
//    rdd5.sortBy(x => x._2, false).foreach(x => println(x))
//    rdd7.sortBy(x => (x._1, x._3)).foreach(x => println(x))


    /**
      * join
      * cogroup
      * cartesian
      */
//    val lastResultJoin: RDD[(String, (Int, Int))] = rdd8.join(rdd9)
//    lastResultJoin.foreach(println)
//    val lastResultCOGroup: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd8.cogroup(rdd9)
//    lastResultCOGroup.foreach(x => {
//      println(x._1, x._2._1.mkString("-"), x._2._2.mkString("-"))
//      println(x._1, x._2._1.mkString("-"), x._2._2.mkString("-"))
//    })

//    val value: RDD[((String, Int), (String, Int))] = rdd8.cartesian(rdd9)
//    value.foreach(x => println(x))


    rdd1.cache()

    sc.stop()
  }
}
