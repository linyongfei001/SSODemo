package aura.spark.scala.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

//case class一定要放到外面
case class Student(id: Int, name: String, sex: String, age: Int, department: String)

object SparkSQL_JSON {

  def main(args: Array[String]) {

    //创建SparkConf()并设置App名称
    val conf = new SparkConf().setAppName("FirstSparkSQLAPP--Student").setMaster("local")
    //SQLContext要依赖SparkContext
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    //创建SQLContext
    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc)

    //从指定的地址创建RDD
    val lineRDD = sc.textFile("file:///D:\\bigdata\\student\\input\\student.txt").map(_.split(","))

    //创建case class
    //将RDD和case class关联
    val studentRDD = lineRDD.map(x => Student(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))

    //导入隐式转换，如果不到人无法将RDD转换成DataFrame
    //将RDD转换成DataFrame
    import sqlContext.implicits._
    val studentDF = studentRDD.toDF

    //注册表
    studentDF.registerTempTable("t_student")
    //传入SQL
    val df = sqlContext.sql("select department, count(*) as total from t_student " +
      "group by department having total > 6 order by total desc")

    //将结果以JSON的方式存储到指定位置
    df.write.json("file:///D:\\bigdata\\student\\spark_stu_out11")
    //停止Spark Context
    sc.stop()

  }
}
