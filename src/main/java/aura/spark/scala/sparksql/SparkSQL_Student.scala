package aura.spark.scala.sparksql

import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types._
import org.apache.spark.{SparkContext, SparkConf}

object SparkSQL_Student {

  def main(args: Array[String]) {
    //创建SparkConf()并设置App名称
    val conf = new SparkConf().setAppName("StructType SparkSQL")
//    conf.setMaster("local[2]")
    //SQLContext要依赖SparkContext
    val sc = new SparkContext(conf)
    //创建SQLContext
    val sqlContext = new SQLContext(sc)

    //从指定的地址创建RDD
//    val personRDD = sc.textFile("hdfs://myha01/student/").map(_.split(","))
    val personRDD = sc.textFile(args(0)).map(_.split(","))
    //通过StructType直接指定每个字段的schema
    val schema = StructType(
      List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("sex", StringType, true),
        StructField("age", IntegerType, true),
        StructField("department", StringType, true)
      )
    )
    //将RDD映射到rowRDD
    val rowRDD = personRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).trim, p(3).toInt, p(4).trim))
    //将schema信息应用到rowRDD上
    val personDataFrame = sqlContext.createDataFrame(rowRDD, schema)
    //注册表
    personDataFrame.registerTempTable("t_student")
    //执行SQL
    val df = sqlContext.sql("select department, count(*) as total from t_student " +
      "group by department having total > 6 order by total desc")
    //将结果以JSON的方式存储到指定位置
    df.write.json(args(1))
//    df.write.json("hdfs://myha01/student/output")
    //停止Spark Context
    sc.stop()
  }
}



/**

// 提交spark sql程序

hadoop fs -rm -r hdfs://myha01/student/output_sparksql2

$SPARK_HOME/bin/spark-submit \
--class com.mazh.spark.sql.StudentSparkSQL2 \
--master spark://hadoop02:7077,hadoop04:7077 \
/home/hadoop/Spark_WordCount-1.0-SNAPSHOT.jar \
hdfs://myha01/student/student.txt \
hdfs://myha01/student/output_sparksql2

hadoop fs -cat hdfs://myha01/student/output_sparksql2/p*

  */