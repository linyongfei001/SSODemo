package aura.spark.scala.sparksql.hive

import org.apache.spark.sql.SparkSession

object SparkSQL_UDF {

  def main(args: Array[String]): Unit = {


    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val sparkSession:SparkSession = SparkSession.builder()
      .appName("SparkSQL_Hive233_New")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()



    val xxx = (age:Int) => if(age % 2 == 0) (age - 1) else (age * 2)
    sparkSession.udf.register("xxx", xxx)



    sparkSession.sql("select id,name,age, xxx(age) as newage, department from myhive.student").show()



    sparkSession.stop()
  }
}
