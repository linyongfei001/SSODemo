package aura.hadoop.mapreduce.mapreduce_partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
/**
 * 泛型1：map输出的key的类型
 * 泛型2：map输出的value的类型
 * @author aura-bd
 *
 */
public class MyPartitioner extends Partitioner<Text, IntWritable>{
	/**
	 * Text key,    map的key
	 * IntWritable value,   map的value
	 * int numPartitions     分区的个数    job.setNumReducetask()
	 *
	 * 1------   a---f
	 2------   g---n
	 3------   o----t
	 4------   剩下的
	 */

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		String k = key.toString();
		if(k.charAt(0)>='a'&&k.charAt(0)<='f'){
			return 0;
		}else if(k.charAt(0)>='g'&&k.charAt(0)<='n'){
			return 1;
		}else if(k.charAt(0)>='o'&&k.charAt(0)<='t'){
			return 2;
		}else{
			return 4;
		}
	}

}
