package aura.hadoop.mapreduce.mapreduce_writable;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/*
 * KEYOUT,   手机号   text
 * VALUEOUT   上行流量  下行流量   text
 */
public class FlowMapper2 extends Mapper<LongWritable, Text, Text, FlowBean>{

	@Override
	protected void map(LongWritable key, Text value,
					   Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] infos = line.split("\t");//这个数组中是一行切分出来的所有字段    一条完整的数据
		//取有效字段
		String num=infos[1];
		FlowBean fb=new FlowBean(Integer.parseInt(infos[infos.length-3]),
				Integer.parseInt(infos[infos.length-2]));
		context.write(new Text(num), fb);
	}
}
