package aura.spark.scala.sparksql

import org.apache.spark.sql.SparkSession

/**
  * 描述： 读取JSON
  */
object SparkSQL_ReadParquet {

  def main(args: Array[String]): Unit = {

    // 构建SparkSQL程序的编程入口对象SparkSession
    val sparkSession:SparkSession = SparkSession.builder()
      .appName("MyFirstSparkSQL")
      .config("someKey", "someValue")
      .master("local")
      .getOrCreate()

    // 方式1
    val df1 = sparkSession.read.parquet("D:\\bigdata\\parquet\\people.parquet")

    // 方式2
    val df2 = sparkSession.read.format("parquet")
      .load("D:\\bigdata\\json\\people.json")

  }
}