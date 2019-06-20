package aura.spark.scala.sparkstream

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_NET {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 获取编程入口
      */
    val sparkConf = new SparkConf().setAppName("SparkStreaming_NET").setMaster("local[2]")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("WARN")
    val sc: StreamingContext = new StreamingContext(sparkContext, Seconds(4))


    /**
      * 第二部： 构建DStream
      */
    val dstream: ReceiverInputDStream[String] = sc.socketTextStream("hadoop05", 9999)


    /**
      * 第三步： 针对第一个DStream做词频统计操作
      */
    val wordsDStream = dstream.flatMap(_.split(" "))
    val wordAndOneDStream: DStream[(String, Int)] = wordsDStream.map(x => (x, 1))
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
