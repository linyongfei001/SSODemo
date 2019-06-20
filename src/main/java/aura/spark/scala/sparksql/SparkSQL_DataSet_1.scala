package aura.spark.scala.sparksql

import org.apache.spark.sql.{Dataset, SparkSession}

/**
  * 描述： 
  */
object SparkSQL_DataSet_1 {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQL_Parquet").master("local[2]").getOrCreate()


//    val dataSet: Dataset[Array[String]] = sparkSession.read.textFile("/wc/input/").map(_.split(" "))
//
//    dataSet.printSchema()
//    dataSet.foreach(x => println(x(0) + "\t" + x(1)))


    sparkSession.stop()

  }
}
