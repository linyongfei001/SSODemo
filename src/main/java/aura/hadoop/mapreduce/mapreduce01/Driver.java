package aura.hadoop.mapreduce.mapreduce01;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 需要打成jar包，放到集群上运行
 * hadoop jar wc.jar aura.hadoop.mapreduce.mapreduce01.Driver /in /out021
 *
 * 					hadoop jar  x.jar  ×××.MainClassName inputPath outputPath
 * 					同时解释一下：
 * 						x.jar为包的名称，包括路径，直接写包名称，则为默认路径
 * 						×××.MainClassName为运行的类名称
 * 						inputPath为输入路径
 * 						outputPath为输出路径。
 */
public class Driver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		//加载配置文件
		Configuration conf=new Configuration();
		//启动一个job  一个map  reduce程序  这里叫做一个job
		Job job=Job.getInstance(conf);

		//指定job运行的主类
		job.setJarByClass(Driver.class);

		//指定这个job的mapper类和reduce类
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);

		//指定map的输出的key  和  value的类型
		//这里为什么还要指定     泛型的只在编译的时候有作用  运行会自动擦除   所以在这里需要指定一下
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//指定reduce输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);


		//指定输入路径   需要统计词频的路径
		FileInputFormat.addInputPath(job, new Path("/in"));
		//添加输出路径
		FileOutputFormat.setOutputPath(job, new Path("/out01"));
		//提交job    true判断是否打印日志
		job.waitForCompletion(true);
	}

}
