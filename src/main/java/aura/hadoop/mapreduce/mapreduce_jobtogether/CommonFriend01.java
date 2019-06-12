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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 				第一步得到：
 一个完整的mr
 将右侧的好友拆分   循环遍历和用户拼接
 A-O
 H-O
 I-O
 J-O
 K-O


 A-T
 A-E
 好友：用户
 A：B   Y  R
 原始数据：
 A:B,C,D,F,E,O
 * @author aura-bd
 *
 */
public class CommonFriend01 {
	/**
	 * 将好友进行查分    循环遍历和用户拼接
	 * key：好友
	 * value：用户
	 * A:B,C,D,F,E,O
	 * @author aura-bd
	 *
	 */
	static class MyMapper01 extends Mapper<LongWritable, Text, Text, Text>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			String[] user_friends = line.split(":");
			//获取的是所有的好友
			String[] friends = user_friends[1].split(",");
			//循环遍历好友  和用户拼接发送
			for(String f:friends){
				context.write(new Text(f), new Text(user_friends[0]));
			}
		}

	}
	//key：好友    value：用户拼接   用户1  用户2
	static class MyReducer01 extends Reducer<Text, Text, Text, Text>{
		//同一个好友的所有用户
		@Override
		protected void reduce(Text key, Iterable<Text> values,
							  Context context)
				throws IOException, InterruptedException {
			//将values进行循环遍历拼接
			StringBuffer sb=new StringBuffer();
			for(Text v:values){
				sb.append(v.toString()).append(",");
			}
			context.write(key, new Text(sb.substring(0,sb.length()-1)));
		}
	}

}
