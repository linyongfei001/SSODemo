package aura.hadoop.mapreduce.mapreduce_partitioner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * reduce输出结果：   单词----词频
 * 进行统计分析
 * @author aura-bd
 * 输入：     map输出的
 * KEYIN, map输出的key  指的就是单词          text
 * VALUEIN, map输出的value  指的就是1    IntWritable
 * 输出：      最终写出到文件的
 * KEYOUT, 输出的key的类型  这里指的就是单词     这里的key不可以重复的  text
 * VALUEOUT：输出的value的类型  这里指的就是总的词频   intwritable
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	/**
	 * 这个方法的调用频率：每组调用一次     有几组会调用几次
	 * 分组规则：
	 * 	key相同的为一组
	 * hello,1   hello,1 hadoop,1  hello,1
	 * key:reduce输入的  这里指的是单词   每一组中的一个key
	 * values：每一组中的所有value   <1,1,1>
	 */
	@Override
	protected void reduce(Text key,
						  Iterable<IntWritable> values,
						  Context context) throws IOException, InterruptedException {
		//进行词频统计
		int sum=0;
		//循环变遍历values   求和
		for(IntWritable v:values){
			//v.get()  这个是将intwritable转换为int
			sum+=v.get();
		}
		context.write(key, new IntWritable(sum));
	}
}
