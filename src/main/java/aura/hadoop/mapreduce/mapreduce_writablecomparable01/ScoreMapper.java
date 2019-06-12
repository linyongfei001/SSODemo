package aura.hadoop.mapreduce.mapreduce_writablecomparable01;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * key：科目+分数
 * value:人名
 * @author aura-bd
 *
 */
public class ScoreMapper extends Mapper<LongWritable, Text, ScoreBean, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, ScoreBean, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		//将一行内容进行切分
		String[] split = line.split(",");
		if(split.length==3){
			ScoreBean sb=new ScoreBean(split[0].trim(),
					Integer.parseInt(split[split.length-1].trim()));
			Text v=new Text(split[1].trim());
			context.write(sb, v);
		}

	}

}
