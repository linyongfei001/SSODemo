package aura.spark.scala.sparksql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 描述： 
  */
object SparkSQL_People_JSON {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQL_People_JSON").master("local").getOrCreate()
    val value: RDD[String] = sparkSession.sparkContext.textFile("")

    val dataFrame = sparkSession.read.json("/sparksql/json/input/")

    dataFrame.printSchema()

    val resultDF: DataFrame = dataFrame.select("name", "age")

    resultDF.foreach(x => println(x(0), x(1)))

    println("----------------------------------------------------")

    resultDF.show()

    sparkSession.stop()

  }
}
