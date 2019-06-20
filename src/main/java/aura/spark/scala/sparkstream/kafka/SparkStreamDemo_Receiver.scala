package aura.spark.scala.sparkstream.kafka

import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 描述： 使用Receiver方式整合Kafka
  */
object SparkStreamDemo_Receiver {

  def main(args: Array[String]) {

    /**
      * 第一步： 获取程序入口， 设置一些必要的参数
      */
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val conf = new SparkConf()
    conf.setAppName("SparkStreamDemo_Receiver")
    conf.setMaster("local[2]")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("hdfs://myha01/flume-kafka-receiver")
    sc.setLogLevel("WARN")
    val ssc = new StreamingContext(sc, Seconds(5))


    /**
      * 第二步： 连接和读取kafka的数据
      */
    val topics = Map("flume-kafka" -> 3)
    val lines = KafkaUtils.createStream(ssc,
      "hadoop02:2181,hadoop03:2181,hadoop04:2181", "flume-kafka-group1", topics)
      .map(_._2)


    /**
      * 第三步： 对数据进行处理
      */
    val ds1 = lines.flatMap(_.split(" ")).map((_, 1))
    val ds2 = ds1.updateStateByKey[Int]((x:Seq[Int], y:Option[Int]) => {
      Some(x.sum + y.getOrElse(0))
    })
    ds2.print()


    /**
      * 第四步： 启动sparkStreaming应用程序
      */
    ssc.start()
    ssc.awaitTermination()
 
  }
}