package aura.hadoop.mapreduce.mapreduce_writablecomparator01;

import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<Student, NullWritable>{

	//分区放在map集合中   key：科目    value：分区号
	static Map<String, Integer> map=new HashMap<String, Integer>();
	static{
		map.put("algorithm", 0);
		map.put("computer", 1);
		map.put("english", 2);
		map.put("math", 3);
	}

	@Override
	public int getPartition(Student key, NullWritable value, int numPartitions) {
		//获取科目：
		String course = key.getCourse();
		return map.get(course);

	}

}
