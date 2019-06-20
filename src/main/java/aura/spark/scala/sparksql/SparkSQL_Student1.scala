package aura.spark.scala.sparksql

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

//case class一定要放到外面
case class Student11(id: Int, name: String, sex: String, age: Int, department: String)

object SparkSQL_Student1 {

  def main(args: Array[String]) {

    //创建SparkConf()并设置App名称
    val conf = new SparkConf().setAppName("FirstSparkSQLAPP--Student")
    //SQLContext要依赖SparkContext
    val sc = new SparkContext(conf)
    //创建SQLContext
    val sqlContext = new SQLContext(sc)

    //从指定的地址创建RDD
    val lineRDD = sc.textFile(args(0)).map(_.split(","))

    //创建case class
    //将RDD和case class关联
    val studentRDD = lineRDD.map(x => Student11(x(0).toInt, x(1), x(2), x(3).toInt, x(4)))

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
    df.write.json(args(1))
    //停止Spark Context
    sc.stop()
  }
}


/**

// 提交spark sql程序

hadoop fs -rm -r hdfs://myha01/student/output_sparksql

$SPARK_HOME/bin/spark-submit \
--class com.mazh.spark.sql.StudentSparkSQL \
--master spark://hadoop02:7077,hadoop04:7077 \
/home/hadoop/Spark_WordCount-1.0-SNAPSHOT.jar \
hdfs://myha01/student/student.txt \
hdfs://myha01/student/output_sparksql

hadoop fs -cat hdfs://myha01/student/output_sparksql/p*

  */

