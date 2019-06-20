package aura.spark.scala.sparkstream

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_Transform {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 获取编程入口
      */
    val sparkConf = new SparkConf().setAppName("SparkStreaming_Transform")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("WARN")
    val sc: StreamingContext = new StreamingContext(sparkContext, Seconds(4))




    /**
      * 第二部： 构建DStream
      */
    val dstream: ReceiverInputDStream[String] = sc.socketTextStream("hadoop05", 9999)


    /**
      * 第三步： 堆特殊符号进行过滤
      */
    val list = List(",", ".", "?", "!", "#", "@", "$", "_")
    // DStream 其实是由一系列连续的RDD组成的一个逻辑抽象
    val wordsDStream:DStream[String] = dstream.flatMap(_.split(" "))


    val transformFunc = (rdd1: RDD[String]) => {

      /**
        * 如果直接使用在driver端中的sparkContext对象， 以前是不能直接被序列化到Executore当中的。
        * not serialziable
        */
      //      val newSparkContext = rdd1.sparkContext
      // 这个打括号中的所有代码都是在那里执行的？
      val diffRDD = rdd1.subtract(sparkContext.makeRDD(list))

      diffRDD
    }


    val wordFilterDStream = wordsDStream.transform(transformFunc)


    val wordAndOneDStream: DStream[(String, Int)] = wordFilterDStream.map(x => (x, 1))
    val resultDStream = wordAndOneDStream.reduceByKey( _ + _ )


    /**
      * 第四步： 处理结果数据, 调用 Ooutput Operation
      */
    resultDStream.print()


    /**
      * 第五步：  启动SparkStreaming应用程序， 然后等待终结
      */
    sc.start()
    sc.awaitTermination()

  }
}
