package aura.hadoop.mapreduce.mapreduce_writable;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer extends Reducer<Text, Text, Text, Text>{
	//每一组调用一次   相同手机号的会发过来一次
	@Override
	protected void reduce(Text key, Iterable<Text> values,
						  Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		//循环遍历values  求和
		int upflow=0;
		int downflow=0;
		int sumflow=0;
		//<上行流量,下行流量   上行流量,下行流量  >
		for(Text v:values){
			String[] split = v.toString().split(",");
			upflow+=Integer.parseInt(split[0]);
			downflow+=Integer.parseInt(split[1]);
		}
		sumflow=upflow+downflow;
		context.write(key, new Text(upflow+"\t"+downflow+"\t"+sumflow));

	}

}
