package aura.spark.scala.sparkcore

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{Accumulator, SparkConf, SparkContext}

object BroadCastTest {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 获取sparkContext对象
      */
    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD_Test.class")
    val sc: SparkContext = new SparkContext(sparkConf)


    val list = List(1,2,3,4,5,6,7,8,9,10)
    val name:String = "huangbo"
    val numberRDD: RDD[Int] = sc.makeRDD(list, 2)


    /**
      * 当要广播的变量的值比较大的时候， 能够减少每个JVM进程（executor）中的使用内存
      * 如果这个变量比较小，那么不用做广播也无伤大雅
      * 但是当这个变量name很大的时候，就一定要做变量的广播
      */
    val bc: Broadcast[String] = sc.broadcast(name)


    numberRDD.map( (x:Int) => {
      bc.value + x
    }).foreach(x => println(x))






    /**
      *
      */
    var sum = 0
    // 累加器的定义
//    val myacc: Accumulator[Int] = sc.accumulator(sum)
    val myacc1: LongAccumulator = sc.longAccumulator("myacc")



    val value: Int = numberRDD.reduce((a:Int, b:Int) => a + b)

    /**
      * 分析： 你在使用这个变量的时候，初始值0， 然后每task在执行计算的时候
      * 就需要拿到这个变量的值。
      *
      * 每个task都拿到sum = 0 做累加
      * 做完累加之后。 这个sum的值有没有被改变? 没有。
      *
      * map   foreach
      * 如果最终计算过程中，RDD中的每个元素都不需要被转换成另外一个元素作为返回
      * 那么就不用去使用map, 使用foreach即可
      *
      * map(f)  ====  f： Int => U
      * foreach(f)  ====  f:Int => Unit
      */
    numberRDD.foreach( (x:Int) => {
      // 累加器的 累加
      myacc1.add(x)
//      myacc1 += x
    })


    // 累加的变量访问
    println(myacc1.value)




    sc.stop()
  }
}
