package aura.hadoop.mapreduce.mapreduce_definein;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MergeSmallFile {
	//map输入key：Text    map输入的value：Nullwritable
	static class MyMapper extends Mapper<Text, NullWritable, Text, NullWritable>{
		//调用频率  一片文章调用一次
		@Override
		protected void map(Text key, NullWritable value, Context context)
				throws IOException, InterruptedException {
			context.write(key, value);
		}

	}
	//只需要一个reducetask就可以
	static class MyReducer extends Reducer<Text, NullWritable, Text, NullWritable>{

		@Override
		protected void reduce(Text key, Iterable<NullWritable> values,
							  Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
			//直接输出就可以
			for(NullWritable nvl:values){
				context.write(key, NullWritable.get());
			}

		}

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://hadoop01:9000/");
		Job job=Job.getInstance(conf);

		//指定主类  jar
		job.setJarByClass(MergeSmallFile.class);

		//指定map和reduce的类
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);

		//指定输出
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		//设置自定义的输入
		job.setInputFormatClass(MyInputFormat.class);
		//指定输入路径  加载多的
		MyInputFormat.addInputPath(job, new Path("/in"));
		FileOutputFormat.setOutputPath(job, new Path("/merge_in_out12"));
		job.waitForCompletion(true);
	}

}
