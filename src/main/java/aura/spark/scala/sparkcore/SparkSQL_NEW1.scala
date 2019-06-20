package aura.spark.scala.sparkcore

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL_NEW1 {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 使用SparkSession作为编程入口
      */
    val sparkSession: SparkSession = SparkSession.builder().appName("SparkSQL_NEW1").master("local").getOrCreate()


    /**
      * 第二步：构建DataFrame
      */
    val dataFrame:DataFrame = sparkSession.read.json("file:///D:\\bigdata\\sparksql\\people\\input")


    /**
      * 第三步：进行操作
      */
    dataFrame.select("name", "age").show()


    /**
      * 第四步：  关闭SparkSESSOIN
      */
    sparkSession.stop()

  }
}
