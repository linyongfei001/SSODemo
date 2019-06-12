package aura.hadoop.mapreduce.mapreduce_jobtogether;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * 好友：用户
 A：B   Y  R
 B：A   Y   R
 1)将右侧的数据拆分   两两组合  共同好友：左侧的数据
 key:B-Y  value:用户
 A：B   Y  R
 B-Y:A
 B-R:A
 Y-R:A
 B：A   Y   R
 A-Y:B
 A-R:B
 Y-R:B

 reduce：
 Y-R:A   B
 * @author aura-bd
 *
 */

public class CommonFriend02 {
	//map端需要做的事情  将用户两两拼接  作为key发送给reduce  value：好友
	//A	F,I,O,K,G,D,C,H,B
	static class MyMapper02 extends Mapper<LongWritable, Text, Text, Text>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] friend_users = line.split("\t");
			String[] users = friend_users[1].split(",");
			//循环遍历用户  进行两两拼接   拼接顺序问题   a-b    b-a
			for(String ul:users){//拼左侧的
				for(String ur:users){//拼右侧的
					if(ul.charAt(0)<ur.charAt(0)){
						String uu=ul+"-"+ur;
						context.write(new Text(uu), new Text(friend_users[0]));
					}
				}
			}

		}

	}
	//将map发哦少年宫过来的两两用户的  共同好友可进行拼接
	static class MyReducer02 extends Reducer<Text, Text, Text, Text>{
		//相同的两两用户为一组
		@Override
		protected void reduce(Text key, Iterable<Text> values,
							  Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			StringBuffer sb=new StringBuffer();
			for(Text v:values){
				sb.append(v.toString()).append(",");
			}
			context.write(key, new Text(sb.substring(0, sb.length()-1)));
		}

	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		//多job串联     需要一起执行几个job  需要在main函数中构建几个job
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		//这里的Configuration  加载的是配置文件 的   集群的
		Configuration conf=new Configuration();
		//启动第一个job
		Job job1=Job.getInstance(conf);
		//job1进行设置
		job1.setJarByClass(CommonFriend01.class);

		job1.setMapperClass(CommonFriend01.MyMapper01.class);
		job1.setReducerClass(CommonFriend01.MyReducer01.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job1, new Path("hdfs://hadoop01:9000/friendin"));
		FileOutputFormat.setOutputPath(job1, new Path("hdfs://hadoop01:9000/friend_step03"));
		//到这一步不提交

		Job job2=Job.getInstance(conf);

		job2.setJarByClass(CommonFriend02.class);

		job2.setMapperClass(MyMapper02.class);
		job2.setReducerClass(MyReducer02.class);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job2, new Path("hdfs://hadoop01:9000/friend_step03"));
		FileOutputFormat.setOutputPath(job2, new Path("hdfs://hadoop01:9000/friend_step04"));
		//多job串联的对象
		//参数指的是组名   组：将当前一起运行的job划分为一组  这里需要传入的组名  随意
		//job控制器   可以统一进行提交job  就是一个线程  相当于一个Runnable对象
		JobControl jc=new JobControl("common_friend");

		//将原来的job转为Controlledjob
		//job1.getConfiguration()  返回job1的配置参数   包含job的相关配置
		ControlledJob cjob01=new ControlledJob(job1.getConfiguration());
		ControlledJob cjob02=new ControlledJob(job2.getConfiguration());

		//添加依赖  添加job之间的相互依赖
		cjob02.addDependingJob(cjob01);

		//将需要运行的job添加到jc中
		jc.addJob(cjob01);
		jc.addJob(cjob02);

		//提交job  创建一个线程对象
		Thread t=new Thread(jc);
		t.start();

		//job运行完成  才可以停止jc.allFinished()  判断是否所有的job执行完成   执行完成true
		while(!jc.allFinished()){
			Thread.sleep(500);
		}

		jc.stop();
	}

}
