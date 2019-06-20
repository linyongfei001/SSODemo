package aura.spark.scala.sparkstream

import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreaming_HDFS {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 获取编程入口
      */
    val sparkConf = new SparkConf().setAppName("SparkStreaming_NET").setMaster("local[2]")
    val sparkContext = new SparkContext(sparkConf)
    sparkContext.setLogLevel("WARN")
    val sc: StreamingContext = new StreamingContext(sparkContext, Seconds(4))


    /**
      * 第二步： 构建第一个DStream
      *
      *
      * 分类统计：
      *
      * 1、如果要接受的数据源的目录不存在，程序会抱错， 但是也会正常运行
      * 2、如果往这个目录中去添加一个文件，那么这个应用程序会自动识别到有新的文件加入
      *    就会自动接受到然后形成为一个RDD执行计算
      * 3、如果往这个文件中追加数据呢？ 追加无效
      * 4、新导入的文件是有效的。已经算过了的文件再追加内容的话是无效的。
      * 5、如果把一个已存在的数据文件删掉，再上传一个一样的文件，最终也会被识别到，参与计算
      */
    val dir = "hdfs://myha01/streaming/"
    val dstream = sc.textFileStream(directory = dir)


    /**
      * 第三步：各种操作
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
