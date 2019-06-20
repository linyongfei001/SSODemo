package aura.spark.scala.sparksql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 描述： 
  */
object SparkSQL_Read_CSV {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQL_Parquet").master("local[2]").getOrCreate()


    val dataFrame: DataFrame = sparkSession.read.format("csv").option("sep", ";").option("inferSchema", "true").option("header", "true")
      .load("/spark233/csv/input/people.csv")

    dataFrame.show()


    sparkSession.stop()
  }
}
