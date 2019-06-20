package aura.spark.scala.sparkcore

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCount {

  def main(args: Array[String]): Unit = {


    /**
      * 第一步：获取编程入口对象
      */
    val sparkConf: SparkConf = new SparkConf()
    sparkConf.setMaster(args(0))
    sparkConf.setAppName("SparkWordCount")
    val sparkContext: SparkContext = new SparkContext(sparkConf)
    // 如果没有传入sparkCOnf作为构造参数，那么就可以直接使用下面这句代码获取SprakContext对象
//    val sparkContext1: SparkContext = new SparkContext()


    /**
      * 第二步： 加载某个文件系统中的某个目录，得到一个数据抽象
      */
      // textFile就好像是跟mapreuce差不多，把整个文件中的一行，当做是一个元素。
      // RDD就是这种元素的一个集合
    val linesRDD: RDD[String] = sparkContext.textFile("hdfs://myha01/wc/input/")


    /**
      * 第三步： 对获取到的数据抽象进行各种业务处理
      * 单词词频统计
      * RDD[String] :  由linesRDD中的每一行切割出来的所有单词组成的一个新的集合
      */
    val wordRDD: RDD[String] = linesRDD.flatMap((line:String) => line.split(" "))
    val wordAndOneRDD: RDD[(String, Int)] = wordRDD.map((word:String) => (word, 1))

    // wordAndOneRDD
//    val unit: RDD[(String, Iterable[Int])] = wordAndOneRDD.groupByKey().reduce()
    val wordsCountRDD: RDD[(String, Int)] = wordAndOneRDD.reduceByKey((a:Int, b:Int) => a + b)



    /**
      * 第四步： 处理结果数据
      */
//    wordsCountRDD.foreach( (x:(String, Int)) => println(x._1, x._2))
      wordsCountRDD.saveAsTextFile(args(1))

    /**
      * 第五步： 关闭程序入口，回收资源
      */
    sparkContext.stop()
  }
}
