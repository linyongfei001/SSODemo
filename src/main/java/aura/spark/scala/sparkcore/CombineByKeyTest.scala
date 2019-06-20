package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object CombineByKeyTest {

  def main(args: Array[String]): Unit = {

    /**
      * 第一步： 获取sparkContext对象
      */
    val sparkConf = new SparkConf()
    sparkConf.setMaster("local")
    sparkConf.setAppName("RDD_Test.class")
    val sc: SparkContext = new SparkContext(sparkConf)

    val scores = List(
      ScoreDetail("xiaoming", "Math", 98),
      ScoreDetail("xiaoming", "English", 88),
      ScoreDetail("wangwu", "Math", 75),
      ScoreDetail("wangwu", "English", 78),
      ScoreDetail("lihua", "Math", 90),
      ScoreDetail("lihua", "English", 80),
      ScoreDetail("zhangsan", "Math", 91),
      ScoreDetail("zhangsan", "English", 80))

    val rdd: RDD[ScoreDetail] = sc.makeRDD(scores)


    val rdd2: RDD[(String, (String, Float))] = rdd.map(x => {
      (x.studentName, (x.subject, x.score))
    })


    /**
      * 掌握这个算子的作用：
      */
    val result333: RDD[(String, (Float, Int))] = rdd2.combineByKey(
      (x: (String, Float)) => (x._2, 1),
      (a: (Float, Int), b: (String, Float)) => (a._1 + b._2, a._2 + 1),
      (a: (Float, Int), b: (Float, Int)) => (a._1 + b._1, a._2 + b._2)
    )

    result333.map(x => {
      (x._1, x._2._1 * 1D / x._2._2)
    }).foreach(x => println(x._1, x._2))


    sc.stop()
  }
}


case class ScoreDetail(studentName: String, subject: String, score: Float)