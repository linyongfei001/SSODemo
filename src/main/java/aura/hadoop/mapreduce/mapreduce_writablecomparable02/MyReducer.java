package aura.hadoop.mapreduce.mapreduce_writablecomparable02;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<FlowBean, NullWritable, FlowBean, NullWritable>{

	//相同上行流量  下行流量  总流量  会分成一组
	@Override
	protected void reduce(FlowBean key, Iterable<NullWritable> values,
						  Context context)
			throws IOException, InterruptedException {
		//循环遍历values输出结果
		for(NullWritable v:values){
			context.write(key, NullWritable.get());
		}
	}
}
