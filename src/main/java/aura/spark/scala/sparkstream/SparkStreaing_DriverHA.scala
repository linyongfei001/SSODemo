package aura.spark.scala.sparkstream


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object SparkStreaing_DriverHA {

  def main(args: Array[String]): Unit = {

    val chkDir = "hdfs://myha01/sparkStreaming_chk2"

    def functionToCreateContext(): StreamingContext = {

      /**
        * 第一步： 获取编程入口
        */
      val sparkConf = new SparkConf().setAppName("SparkStreaming_NET").setMaster("local[2]")
      val sparkContext = new SparkContext(sparkConf)
      sparkContext.setLogLevel("WARN")
      val sc: StreamingContext = new StreamingContext(sparkContext, Seconds(4))
      sc.checkpoint(chkDir)

      /**
        * 第二部： 构建DStream
        */
      val dstream: ReceiverInputDStream[String] = sc.socketTextStream("hadoop05", 9999)


      /**
        * 第三步： 针对第一个DStream做词频统计操作
        */
      val wordsDStream = dstream.flatMap(_.split(" "))
      val wordAndOneDStream: DStream[(String, Int)] = wordsDStream.map(x => (x, 1))

      def updateFunc(value:Seq[Int], state:Option[Int]):Option[Int] = {
        val newSumResult = value.sum
        val lastValue = state.getOrElse(0)
        Some(newSumResult + lastValue)
      }

      //  参数： updateFunc :   Seq[Int], Option[T] => Option[T]
      //     list.add(a)              a ,   list    =>  list
      val resultDStream = wordAndOneDStream.updateStateByKey(updateFunc)


      /**
        * 第四步： 处理结果数据, 调用 Ooutput Operation
        */
      resultDStream.print()

      /**
        * 第五步：  启动SparkStreaming应用程序， 然后等待终结
        */
      sc.start()
      sc.awaitTermination()
      sc
    }


    val context: StreamingContext = StreamingContext.getOrCreate(chkDir, functionToCreateContext _ )


    context.start()
    context.awaitTermination()

  }
}
