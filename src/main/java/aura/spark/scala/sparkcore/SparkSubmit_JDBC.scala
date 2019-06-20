package aura.spark.scala.sparkcore

import java.util.Properties

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object SparkSubmit_JDBC {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 使用SparkSession作为编程入口
      */
    val sparkSession: SparkSession = SparkSession.builder()
      .appName("SparkSubmit_JDBC")
//      .master("local")
      .getOrCreate()


    val lineRDD: RDD[String] = sparkSession.sparkContext.textFile(args(0))


    val studentRDD: RDD[Row] = lineRDD.map(_.split(","))
      .map(x => Row(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))


    val schema = StructType(Array(
      StructField("id", IntegerType, false),
      StructField("name", StringType, true),
      StructField("sex", StringType, true),
      StructField("age", IntegerType, false),
      StructField("department", StringType, true)
    ))


    val studentDF: DataFrame = sparkSession.createDataFrame(studentRDD, schema)


    //创建 Properties 存储数据库相关属性
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "root")
    //将数据追加到数据库

    studentDF.write.mode(args(1)).jdbc("jdbc:mysql://hadoop02:3306/bigdata",
      "student", prop)


    sparkSession.stop()

  }
}
