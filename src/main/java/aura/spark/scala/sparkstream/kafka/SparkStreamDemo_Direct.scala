package aura.spark.scala.sparkstream.kafka

import kafka.serializer.StringDecoder
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 描述： 使用Direct方式整合Kafka
  */
object SparkStreamDemo_Direct {

  def main(args: Array[String]): Unit = {

    /**
      * 第一步： 获取程序入口， 设置一些必要的参数
      */
    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val conf = new SparkConf().setMaster("local[2]").setAppName("SparkStreamDemo_Direct")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val ssc = new StreamingContext(sc,Seconds(2))
    ssc.checkpoint("hdfs://myha01/flume-kafka-direct")

    /**
      * 第二步： 连接和读取kafka的数据
      *
      * def createDirectStream[K: ClassTag,V: ClassTag,KD <: Decoder[K]: ClassTag,VD <: Decoder[V]: ClassTag] (
            ssc: StreamingContext,
            kafkaParams: Map[String, String],
            topics: Set[String]
        )
      */
    val kafkaParams = Map("metadata.broker.list" -> "hadoop2:9092,hadoop03:9092,hadoop04:9092")
    val topics = Set("flume-kafka")
    val kafkaDStream: DStream[String] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topics).map(_._2)



    /**
      * 第三步： 对数据进行处理
      */
    kafkaDStream.flatMap(_.split(" "))
      .map((_,1))
//      .reduceByKey(_+_)
      .print()


    /**
      * 第四步： 启动sparkStreaming应用程序
      */
    ssc.start()
    ssc.awaitTermination()
    ssc.stop()

  }
}