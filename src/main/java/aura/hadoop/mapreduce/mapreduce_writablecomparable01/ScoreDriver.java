package aura.hadoop.mapreduce.mapreduce_writablecomparable01;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ScoreDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);

		job.setJarByClass(ScoreDriver.class);

		job.setMapperClass(ScoreMapper.class);
		job.setReducerClass(ScoreReducer.class);

		//设置map和reduce的输出   如果map和reduce的输出的key  value类型一样  只需要设置一个
		//设置最终的输出类型就可以
		job.setOutputKeyClass(ScoreBean.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path("hdfs://hadoop01:9000/scorein"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop01:9000/scoreout06"));

		job.waitForCompletion(true);
	}

}
