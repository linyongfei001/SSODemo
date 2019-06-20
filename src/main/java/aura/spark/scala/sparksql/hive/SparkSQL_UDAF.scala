package aura.spark.scala.sparksql.hive

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._


/**
  * 自定义一个函数  abc  实现和 avg一样的逻辑
  *
  * avg  Aggregate聚集函数
  *
  * 自定义abc实现和 avg一样的效果的函数定义就是  自定义 聚集函数
  */
object SparkSQL_UDAF {

  def main(args: Array[String]): Unit = {

    System.setProperty("HADOOP_USER_NAME", "hadoop")
    val sparkSession:SparkSession = SparkSession.builder()
      .appName("SparkSQL_Hive233_New")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()



    sparkSession.udf.register("abc", MyUDAF_AVG)


    sparkSession.sql("select sex, avg(age) as avgage from myhive.student group by sex").show()
    sparkSession.sql("select sex, abc(age) as avgage from myhive.student group by sex").show()



    sparkSession.stop()
  }
}


/**
  * 定义这么一个类，就是实现一个  聚集函数的  逻辑
  *
  * avg(age)
  */
object MyUDAF_AVG extends UserDefinedAggregateFunction{


  /**
    * 输入数据的schema信息
    */
  override def inputSchema: StructType = StructType(
    StructField("age", DoubleType, false) :: Nil
  )

  /**
    * avg是如何计算的？  (2,3,4,5,6) =====> ( sum += value,  count += 1 )
    * 中间辅助数据的 schema 信息
    */
  override def bufferSchema: StructType = StructType(
    StructField("sum", DoubleType, false) ::
      StructField("count", IntegerType, false)
      :: Nil
  )


  /**
    * 初始化辅助变量的。
    */
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
//    buffer(0) = 0.0D
//    buffer(1) = 0

    buffer.update(0, 0.0D)
    buffer.update(1, 0)
  }

  /**
    *  就是把输入数据合并到辅助变量中去
    *  buffer   辅助变量容器
    *  input    输入数据
    */
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {

    var new_sum = buffer.getDouble(0)
    var new_count = buffer.getInt(1)

    val new_value: Double = input.getDouble(0)

    new_sum  += new_value
    new_count += 1

    buffer.update(0, new_sum)
    buffer.update(1, new_count)
  }

  /**
    * 合并两个中间辅助结果
    *
    * 把 buffer2 中的中间辅助结果 合并到  buffer1 中去
    */
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {

    val sum1 = buffer2.getDouble(0)
    val count1 = buffer2.getInt(1)


    buffer1.update(0, buffer1.getDouble(0) + sum1)
    buffer1.update(1, buffer1.getInt(1) + count1)

  }

  /**
    * 用来计算最后的聚合的结果
    */
  override def evaluate(buffer: Row): Any = {

    val sum = buffer.getDouble(0)
    val count  = buffer.getInt(1)

    sum / count
  }

  /**
    *  最终返回结果值的额类型
    */
  override def dataType: DataType = DoubleType


  /**
    * 如果输入数据和输出数据类型一致的话， 把这个返回值改成true,否则改成false
    */
  override def deterministic: Boolean = true
}
