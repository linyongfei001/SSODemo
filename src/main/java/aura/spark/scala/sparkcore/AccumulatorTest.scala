package aura.spark.scala.sparkcore

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.{AccumulatorV2, LongAccumulator}

import scala.collection.mutable

/**
  * 描述： Spark2.x 累加器测试
  */
object AccumulatorTest {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setAppName("Accumulator3Test")
    sparkConf.setMaster("local")
    val sparkContext = new SparkContext(sparkConf)


    val list  = List(1,2,3,4,5,6,7,8,9,10)
    val rdd:RDD[Int] = sparkContext.makeRDD(list)


    /**
      * 老版本的累加器： 直接从SparkContext中获取
      */
    val myacc = sparkContext.longAccumulator("myacc")
    rdd.foreach(x => {
      // 累加器的使用
      myacc.add(x)
    })
    println(myacc.value)


    /**
      * 新版本的累加器， 先初始化一个累加器，然后要进行注册
      */
    val accumulator = new LongAccumulator
    sparkContext.register(accumulator)
    rdd.foreach(x => {
      // 累加器的使用
      accumulator.add(x)
    })


    /**
      * 新版本的自定义累加器， 也需要进行注册
      * 定义灵活
      */
    val accumulator1 = new MyACC
    sparkContext.register(accumulator1)
    rdd.foreach(x => {
      // 累加器的使用
      accumulator1.add(x)
    })


    println(accumulator.value)
    println(accumulator1.value)


    sparkContext.stop()
  }
}


/**
  * isZero: 当AccumulatorV2中存在类似数据不存在这种问题时，是否结束程序。
  * copy: 拷贝一个新的AccumulatorV2
  * reset: 重置AccumulatorV2中的数据
  * add: 操作数据累加方法实现
  * merge: 合并数据
  * value: AccumulatorV2对外访问的数据结果
  */
class MyACC extends AccumulatorV2[Int, Int] {

  private var sum = 0

  override def isZero: Boolean = sum == 0

  override def copy(): AccumulatorV2[Int, Int] = {
    val myacc = new MyACC
    myacc.sum = sum
    myacc
  }

  override def reset(): Unit = {
    sum = 0
  }

  override def add(v: Int): Unit = {
    sum += v
  }

  override def merge(other: AccumulatorV2[Int, Int]): Unit = other match {
    case acc: AccumulatorV2[Int, Int] => sum += acc.value
  }

  override def value: Int = sum
}