package aura.spark.scala.sparksql

import org.apache.spark.sql.SparkSession


object SparkSessionTest {

  def main(args: Array[String]): Unit = {

    // 构建SparkSQL程序的编程入口对象SparkSession
    val sparkSession:SparkSession = SparkSession.builder()
      .appName("MyFirstSparkSQL")
      .config("someKey", "someValue")
      .master("local")
      .getOrCreate()

    // 导入隐式转换，好让RDD可以被隐式转换到DataFrame
//    implicit  sparkSession.implicits._

  }
}
