package aura.hadoop.mapreduce.mapreduce_writablecomparable01;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * key:sb
 * value:Text
 * @author aura-bd
 *
 */
public class ScoreReducer extends Reducer<ScoreBean, Text, ScoreBean, Text>{
	//相同的科目  相同的分数会到一起
	@Override
	protected void reduce(ScoreBean key, Iterable<Text> values,
						  Reducer<ScoreBean, Text, ScoreBean, Text>.Context context)
			throws IOException, InterruptedException {
		//求人数的变量
		int count=0;
		StringBuffer str=new StringBuffer();
		for(Text v:values){
			count++;
			str.append(v.toString()).append(",");
		}
		//str.substring(0, str.length()-1))含头不含尾
		context.write(key, new Text(count+"\t"+str.substring(0, str.length()-1)));
	}

}
