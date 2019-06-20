package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

object SparkSQL_NEW3 {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 使用SparkSession作为编程入口
      */
    val sparkSession: SparkSession = SparkSession.builder().appName("SparkSQL_NEW1").master("local").getOrCreate()


    val lineRDD: RDD[String] = sparkSession.sparkContext.textFile("file:///D:\\bigdata\\student\\input")


    val studentRDD: RDD[Row] = lineRDD.map(_.split(","))
      .map(x => Row(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))


    val schema = StructType(Array(
      StructField("id", IntegerType, false),
      StructField("name", StringType, true),
      StructField("sex", StringType, true),
      StructField("age", IntegerType, false),
      StructField("department", StringType, true)
    ))


    val studentDF1: DataFrame = sparkSession.createDataFrame(studentRDD, schema)


    studentDF1.createTempView("student")

    sparkSession.sql("select count(*) as total from student").show()

    /**
      * 第四步：  关闭SparkSESSOIN
      */
    sparkSession.stop()

  }
}

