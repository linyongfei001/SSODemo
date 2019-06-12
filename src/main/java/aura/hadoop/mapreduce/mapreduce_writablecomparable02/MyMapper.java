package aura.hadoop.mapreduce.mapreduce_writablecomparable02;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/*
 * NullWritable  是一个单例对象
 * 获取对象  NullWritable.get()
 * 以上一次的统计结果为基础的
 * 13480253104	2494800	2494800	4989600
 */
public class MyMapper extends Mapper<LongWritable, Text, FlowBean, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] split = line.split("\t");
		FlowBean fb=new FlowBean(split[0].trim(),
				Integer.parseInt(split[1].trim()),
				Integer.parseInt(split[2].trim()),
				Integer.parseInt(split[3].trim()));
		context.write(fb, NullWritable.get());
	}
}
