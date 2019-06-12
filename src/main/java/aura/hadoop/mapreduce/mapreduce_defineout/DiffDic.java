package aura.hadoop.mapreduce.mapreduce_defineout;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class DiffDic {
	//统计平均成绩  并且将成绩输出到不同的目录中   computer,huangxiaoming,85,86,41,75,93,42,85

	static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
		Text mk=new Text();
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			String[] split = value.toString().split(",");
			int sum=0;
			int count=0;
			for(int i=2;i<split.length;i++){
				count++;
				sum+=Integer.parseInt(split[i].trim());
			}
			double avg=(double)sum/count;
			String res=split[0]+"\t"+split[1]+"\t"+avg;
			mk.set(res);
			//key：科目  姓名   平均分
			context.write(mk, NullWritable.get());
		}

	}
	static class MyReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
		@Override
		protected void reduce(Text key, Iterable<NullWritable> values,
							  Context context) throws IOException, InterruptedException {
			context.write(key, NullWritable.get());
		}
	}
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://hadoop01:9000/");
		Job job=Job.getInstance(conf);

		//指定主类  jar
		job.setJarByClass(DiffDic.class);

		//指定map和reduce的类
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);

		//指定输出
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		//设置自定义的输出
		job.setOutputFormatClass(MyOutputFormat.class);
		//指定输入路径  加载多的
		FileInputFormat.addInputPath(job, new Path("/scorezqin"));
		//指定的是输出标志文件存储的位置
		FileOutputFormat.setOutputPath(job, new Path("/diffdic_out02"));
		job.waitForCompletion(true);
	}

}
