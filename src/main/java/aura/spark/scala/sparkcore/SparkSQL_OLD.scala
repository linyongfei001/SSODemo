package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 老版本的API进行sparksql编程
  *
  * 编程入口：  SQLContext
  */
object SparkSQL_OLD {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步： 获取编程入口
      */
    val sparkConf: SparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("SparkSQL_OLD")
    val sparkContext: SparkContext = new SparkContext(sparkConf)
    val sqlContext:SQLContext = new SQLContext(sparkContext)


    /**
      * 第二步： 加载数据得到一个RDD， 然后转换成DataFrame
      */
    val lineRDD: RDD[String] = sparkContext.textFile("file:///D:\\bigdata\\student\\input")


    val studentRDD: RDD[Student] = lineRDD.map(_.split(","))
      .map(x => Student(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))


    import sqlContext.implicits._
    val studentDF: DataFrame = studentRDD.toDF()


    // 第一种:  DSL风格
    studentDF.select(studentDF("age")).show(10)


    // 第二种： SQL风格
    studentDF.registerTempTable("student")
    sqlContext.sql("select count(*) from student").show()


    sparkContext.stop()
  }
}

case class Student(id:Int, name:String, sex:String, age:Int, department:String)

