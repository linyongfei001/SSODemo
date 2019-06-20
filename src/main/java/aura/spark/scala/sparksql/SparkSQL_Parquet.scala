package aura.spark.scala.sparksql

import org.apache.spark.sql.SparkSession

object SparkSQL_Parquet {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQL_Parquet").master("local[2]").getOrCreate()

    val peopleDF = sparkSession.read.json("hdfs://myha01/spark/sql/input/people.json")

    // DataFrames can be saved as Parquet files, maintaining the schema information
    peopleDF.write.parquet("hdfs://myha01/spark/sql/input/people.parquet")

    /**
      * Read in the parquet file created above
      * Parquet files are self-describing so the schema is preserved
      * The result of loading a Parquet file is also a DataFrame
      */
    // hadoop fs -rm -r /spark/sql/input/people.parquet
    val parquetFileDF = sparkSession.read.parquet("hdfs://myha01/spark/sql/input/people.parquet")

    // Parquet files can also be used to create a temporary view and then used in SQL statements
    parquetFileDF.createOrReplaceTempView("parquetFile")

    val namesDF = sparkSession.sql("SELECT name FROM parquetFile WHERE age BETWEEN 13 AND 19")

    namesDF.foreach( x => println(x))

  }
}
