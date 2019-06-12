package aura.hadoop.mapreduce.mapreduce_writablecomparator01;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AvgScore01 {
	//key：课程+姓名+平均分  自定义类  value:null
	static class MyMapper extends Mapper<LongWritable, Text, Student, NullWritable>{
		@Override
		protected void map(LongWritable key, Text value,
						   Mapper<LongWritable, Text, Student, NullWritable>.Context context)
				throws IOException, InterruptedException {
			//english,huangbo,85,42,96,38,55,47,22
			String[] stuinfos = value.toString().split(",");
			String course=stuinfos[0].trim();
			String name=stuinfos[1].trim();
			int sum=0;
			int count=0;
			for(int i=2;i<stuinfos.length;i++){
				count++;
				sum+=Integer.parseInt(stuinfos[i].trim());
			}
			double avg=(double)sum/count;
			Student stu=new Student(course, name, avg);
			context.write(stu, NullWritable.get());
		}

	}
	static class MyReducer extends Reducer<Student, NullWritable, Student, NullWritable>{
		//1）reduce中的values的问题  迭代器   迭代器只能迭代一次   指针的操作
		//第一次迭代的时候   迭代完成   指针指向最后
		//2）key到底指的是是谁的key   这个key并不是一成不变的  而是随value的变化而变化的

		@Override
		protected void reduce(Student key, Iterable<NullWritable> values,
							  Context context)
				throws IOException, InterruptedException {
			//求每门课程的最高分
			System.out.println(key);
			for(NullWritable v:values){
				System.out.println("************"+key);
			}
		}

	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);

		job.setJarByClass(AvgScore01.class);

		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);

		job.setOutputKeyClass(Student.class);
		job.setOutputValueClass(NullWritable.class);
		//设置分组规则
		job.setGroupingComparatorClass(MyGroup.class);
		//设置分区
		/*job.setNumReduceTasks(4);
		job.setPartitionerClass(MyPartition.class);*/

		FileInputFormat.addInputPath(job, new Path("hdfs://hadoop01:9000/scorezqin"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop01:9000/score_zq_out0011"));

		job.waitForCompletion(true);
	}

}
