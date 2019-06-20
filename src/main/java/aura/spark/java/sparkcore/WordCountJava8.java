package aura.spark.java.sparkcore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;

public class WordCountJava8 {

    public static void main(String[] args) {
/*
        // 1 ： 获取程序入口
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(WordCountJava7.class.getSimpleName());
        sparkConf.setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);


        // 2 ： 获取数据抽象
        JavaRDD<String> lineRDD = jsc.textFile("/wc/input/");



        // 3 : 使用lambda表达式去处理
        JavaRDD<String> result1 = lineRDD.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        JavaPairRDD result2 = result1.mapToPair(word -> new Tuple2(word, 1));
//        <String, Integer>

        JavaPairRDD<String, Integer> result3 = result2.reduceByKey((a, b) -> (Integer)a + (Integer)b);


        result3.foreach( t -> System.out.println(t._1 + "\t" + t._2));

        jsc.stop();
*/
    }
}
