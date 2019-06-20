package aura.spark.scala.sparksql.hive

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 测试：  测试使用SPARKsql去访问和处理存储在HIIVE数据仓库中的数据
  *
  * spark1.x版本中的老套路
  */
object SparkSQL_Hive233 {

  def main(args: Array[String]): Unit = {

    System.setProperty("HADOOP_USER_NAME", "hadoop")


    val sparkConf: SparkConf = new SparkConf()
    sparkConf.setAppName("SparkSQL_Hive233")
    sparkConf.setMaster("local")
    val sparkContext: SparkContext = new SparkContext(sparkConf)

    val hiveContext: HiveContext = new HiveContext(sparkContext)


    hiveContext.sql("select * from myhive.student").show(30)


    sparkContext.stop()
  }
}
