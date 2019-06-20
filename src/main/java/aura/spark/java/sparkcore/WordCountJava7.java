package aura.spark.java.sparkcore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 使用普通的java编程语言去编写spark的wordcount
 *
 * 还有第二种写法：会更优： lambda
 */
public class WordCountJava7 {
    public static void main(String[] args) {
/*
        // 1 ： 获取程序入口
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(WordCountJava7.class.getSimpleName());
        sparkConf.setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);


        // 2 ： 获取数据抽象
        JavaRDD<String> lineRDD = jsc.textFile("/wc/input/");


        // 3 :  对数据进行各种处理
        // val wordRDD: RDD[String] = linesRDD.flatMap((line:String) => line.split(" "))
        JavaRDD<String> wordRDD = lineRDD.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String line) throws Exception {
                String[] strings = line.split(" ");
                List<String> strings1 = Arrays.asList(strings);
                return strings1.iterator();
            }
        });

        JavaPairRDD<String, Integer> wordAndOneRDD = wordRDD.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<>(word, 1);
            }
        });


        JavaPairRDD<String, Integer> wordsCountRDD = wordAndOneRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });


        // 排序操作
        JavaPairRDD<Integer, String> resveredWCRDD = wordsCountRDD.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(Tuple2<String, Integer> t) throws Exception {
                return t.swap();
            }
        });

        JavaPairRDD<Integer, String> sortedRDD = resveredWCRDD.sortByKey(false);

        JavaPairRDD<String, Integer> lastResultRDD = sortedRDD.mapToPair(new PairFunction<Tuple2<Integer, String>, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Tuple2<Integer, String> t) throws Exception {
                return t.swap();
            }
        });


        // 4 ： 如何处理结果
        lastResultRDD.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {

                System.out.println(t._1 + "\t" + t._2);
            }
        });


        // 5 ： 关闭程序入口
        jsc.stop();
*/
    }
}
