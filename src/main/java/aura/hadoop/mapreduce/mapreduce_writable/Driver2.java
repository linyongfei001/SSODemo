package aura.hadoop.mapreduce.mapreduce_writable;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 将一个对象实现了Writable接口作为参数传入
 */

public class Driver2 {
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {

		System.setProperty("HADOOP_USER_NAME", "hadoop");
		//加载配置文件
		Configuration conf=new Configuration();
		//启动一个job  一个map  reduce程序  这里叫做一个job
		Job job=Job.getInstance(conf);

		//指定job运行的主类
		job.setJarByClass(Driver2.class);

		//指定这个job的mapper类和reduce类
		job.setMapperClass(FlowMapper2.class);
		job.setReducerClass(FlowReducer2.class);

		//指定map的输出的key  和  value的类型
		//这里为什么还要指定     泛型的只在编译的时候有作用  运行会自动擦除   所以在这里需要指定一下
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);

		//指定reduce输出的key和value类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		FileInputFormat.addInputPath(job,  new Path("hdfs://hadoop01:9000/flowin"));
		//添加输出路径    输出路径一定不能存在     怕如果存在会进行覆盖
		FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop01:9000/flowout02"));
		//提交job
		job.waitForCompletion(true);
	}
}
