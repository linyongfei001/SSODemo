package aura.spark.scala.sparkstream

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object StreamingContextTest {


  def main(args: Array[String]): Unit = {


    /**
      * 第一种方式：使用sparkConf来创建StreamingContext对象
      *
      * 注意点：
      *
      *     1、其实传入的SparkConf中就包含了SparkContext， 如果没有传入，那么请直接传入一个SparkContext
      *
      *     2、在sparkStreaming的程序中，master的执行，不管使用哪种模式执行，都必须要指定的数量是大于等于2
      *         local ----->  local[2]
      */
//    val sparkConf = new SparkConf().setAppName("StreamingContextTest").setMaster("local[2]")
//    val streamingContext = new StreamingContext(sparkConf,  Seconds(4))
    val sparkConf1 = new SparkConf().setAppName("StreamingContextTest").setMaster("")
    val sparkContext = new SparkContext(sparkConf1)
    val streamingContext1 = new StreamingContext(sparkContext, Seconds(2))




    streamingContext1.stop(false)

  }
}

