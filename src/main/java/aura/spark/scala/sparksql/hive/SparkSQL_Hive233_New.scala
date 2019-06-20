package aura.spark.scala.sparksql.hive

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 该测试程序是为了和hive整合。 所以要启动hive的编程入口
  */
object SparkSQL_Hive233_New {

  def main(args: Array[String]): Unit = {


    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val sparkSession:SparkSession = SparkSession.builder()
      .appName("SparkSQL_Hive233_New")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()


    val studentDF: DataFrame = sparkSession.sql("select lcase(department), count(*) as total from myhive.student group by department")



    studentDF.show()


    sparkSession.stop()

  }
}
