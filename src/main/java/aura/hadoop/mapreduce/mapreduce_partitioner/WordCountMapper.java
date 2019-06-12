package aura.hadoop.mapreduce.mapreduce_partitioner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * 分
 */
/**
 * 统计单词词频：
 * 	map这里：
 * 		统计每一个小文件的单词词频
 * 		拆分出来没有一个单词   标记1   输出就可以   统计工作reduce上了
 * 输入：  k-v  这里的输入是框架帮你输入的   写死的  固定的内容
 * KEYIN, 输入的键的类型  在这里指的是偏移量------指的是每一行起始的偏移量    long
 * VALUEIN:输入的值的类型  在这里指的是一行的内容     string
 * 第一行：
 * 	KEYIN：0
 * VALUEIN：hadoop	hadoop	spark	mllib	sqoop
 * 输出：  k-v  取决于用户的业务
 * KEYOUT, 输出的键的类型     这里指的是单词  可以允许重复的    string
 * VALUEOUT  输出的值的类型  这里指的是1     int
 * @author aura-bd
 *我的数据map-----reduce中    你的程序执行的时候  可能map  reduce不在一台机器上
 *序列化和反序列化
 *	数据需要持久化或网络传输的时候    Serializable
所以所有map短   reduce  传输的数据必须是经过序列化和反序列化的
 *但是hadoop中自定义了一套序列化和反序列化的接口：
 *	没有使用java中？因为java中的序列化和反序列化接口Serializable（将类结构一并可进行序列化和反序列化）  过于臃肿
 *Writable
 *long-----longWritable
 *int-----intwritable
 *double---doublewritable
 *float-----floatwritable
 *
 *null-----nullwritable
 *string-----text
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	//重写map方法
	/**
	 * key:输入的键  这里指的每一行的起始偏移量
	 * value：指的输入的值   这里指的是没一行的内容
	 * context：上下文对象   用于传输使用的      map----reduce中
	 * 这个方法的调用频率：
	 * 	每行调用一次  文本中有几行就调用几次
	 * key：当前行的起始偏移量
	 * value：当前行的内容    和key是一一对应的
	 */
	@Override
	protected void map(LongWritable key,
					   Text value,
					   Context context)
			throws IOException, InterruptedException {
		//拿到每一行的内容  进行分割
		//将text---String
		String line = value.toString();
		//拆分单词
		String[] words = line.split("\t");
		//循环遍历每一个单词  进行打标机  1  发送给reduce进行统一统计
		for(String w:words){
			//参数1：key   参数2：value
			//String--text
			Text k=new Text(w);
			IntWritable v=new IntWritable(1);
			context.write(k, v);
		}

	}

}
