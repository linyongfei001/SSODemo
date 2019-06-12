package aura.hadoop.mapreduce.mapreduce_counter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Counter_139_Lines {
	//
	static class MyMapper extends Mapper<LongWritable, Text,
			NullWritable, NullWritable>{
		@Override
		protected void map(LongWritable key, Text value,
						   Context context)
				throws IOException, InterruptedException {
			//先获取每一条数据
			String line = value.toString();
			String[] infos = line.split("\t");
			String phone=infos[1];
			//判断是否是139开始的   如果是  取出计数器+1
			if(phone.startsWith("139")){
				//通过context取全局计数器
				//获取计数器   没有给初始值  默认从0开始
				Counter counter = context.getCounter(MyCounter.COUNT_139_START);
				//将计数器 进行自增  1
				counter.increment(1L);
			}
		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);

		job.setJarByClass(Counter_139_Lines.class);
		job.setMapperClass(MyMapper.class);

		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(NullWritable.class);

		//设置reducetask的个数   不设置默认1
		job.setNumReduceTasks(0);

		FileInputFormat.addInputPath(job, new Path("hdfs://hadoop01:9000/flowin"));
		//输出路径  不需要  ????   目的：存储成功的标志文件
		FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop01:9000/flowcountout02"));

		job.waitForCompletion(true);

	}

}
