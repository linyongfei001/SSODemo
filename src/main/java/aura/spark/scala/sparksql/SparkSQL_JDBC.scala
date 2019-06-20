package aura.spark.scala.sparksql

import java.util.Properties
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkSQL_JDBC {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkSQL_JDBC")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //通过并行化创建RDD
//    val studentRDD = sc.parallelize(Array("1 huangbo 33", "2 xuzheng 44", "3 wangbaoqiang 55")).map(_.split(" "))
    val studentRDD = sc.textFile(args(0)).map(_.split(","))
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
    val rowRDD = studentRDD.map(p => Row(p(0).toInt, p(1).trim, p(2).trim, p(3).toInt, p(4).trim))
    //将schema信息应用到rowRDD上
    val studentDataFrame = sqlContext.createDataFrame(rowRDD, schema)
    //创建Properties存储数据库相关属性
    val prop = new Properties()
    prop.put("user", "root")
    prop.put("password", "root")
    //将数据追加到数据库
    studentDataFrame.write.mode("append").jdbc("jdbc:mysql://hadoop02:3306/spider", "student", prop)
    //停止SparkContext
    sc.stop()
  }
}


/**

提交Spark任务：

$SPARK_HOME/bin/spark-submit \
--class com.mazh.spark.sql.SparkSQL_JDBC \
--master spark://hadoop02:7077,hadoop04:7077 \
--jars $SPARK_HOME/mysql-connector-java-5.1.40-bin.jar \
--driver-class-path $SPARK_HOME/mysql-connector-java-5.1.40-bin.jar \
/home/hadoop/Spark_WordCount-1.0-SNAPSHOT.jar \
hdfs://myha01/student/student.txt


  */

