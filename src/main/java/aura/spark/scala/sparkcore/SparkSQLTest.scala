package aura.spark.scala.sparkcore

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkSQLTest {

  def main(args: Array[String]): Unit = {

    /**
      * 第一步：获取编程入口：sPARKsESSION
      */
    val sparkSession: SparkSession = SparkSession.builder()
      .appName("SparkSQLTest")
      .master("local")
      .getOrCreate()


    /**
      * 第二部： 构建一个DataFrame
      */
    val df: DataFrame = sparkSession.read.json("D:\\bigdata\\sparksql\\people\\input")


    /**
      * 第三步： 把这个DataFrame注册为一张表
      */
    df.registerTempTable("people")


    /**
      * 第四步： 使用sql语句的方式去处理people这张表中的数据
      */
    val df2: DataFrame = sparkSession.sql("select count(*) from people")


    /**
      * 第五步： 处理结果数据
      */
    df2.show()


    sparkSession.stop()
  }
}
