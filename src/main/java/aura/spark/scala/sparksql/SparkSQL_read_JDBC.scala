package aura.spark.scala.sparksql

import org.apache.spark.sql.SparkSession

/**
  *
  * 描述： 
  */
object SparkSQL_read_JDBC {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQL_read_JDBC").master("local[2]").getOrCreate()
    val jdbcDF = sparkSession.read.format("jdbc").options(Map(
      "url" -> "jdbc:mysql://hadoop02:3306/bigdata",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "student",
      "user" -> "root",
      "password" -> "root"))
      .load()

    jdbcDF.show()


    sparkSession.stop()
  }
}
