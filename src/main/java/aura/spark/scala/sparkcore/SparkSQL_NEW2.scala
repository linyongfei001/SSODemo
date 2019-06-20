package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQL_NEW2 {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 使用SparkSession作为编程入口
      */
    val sparkSession: SparkSession = SparkSession.builder().appName("SparkSQL_NEW1").master("local").getOrCreate()


    val lineRDD: RDD[String] = sparkSession.sparkContext.textFile("file:///D:\\bigdata\\student\\input")


    val studentRDD: RDD[Student1] = lineRDD.map(_.split(","))
      .map(x => Student1(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))


    val studentDF: DataFrame = sparkSession.createDataFrame(studentRDD)


    studentDF.select("id", "age").show()

    /**
      * 第四步：  关闭SparkSESSOIN
      */
    sparkSession.stop()

  }
}


case class Student1(id:Int, name:String, sex:String, age:Int, department:String)