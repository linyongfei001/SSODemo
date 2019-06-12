package aura.hadoop.mapreduce.mapreduce_writable;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer2 extends Reducer<Text, FlowBean, Text, FlowBean>{
	//每一组调用一次   相同手机号的会发过来一次
	@Override
	protected void reduce(Text key, Iterable<FlowBean> values,
						  Context context)
			throws IOException, InterruptedException {
		//循环遍历values  求和
		int upflow=0;
		int downflow=0;
		//<上行流量,下行流量   上行流量,下行流量  >
		for(FlowBean fb:values){
			upflow+=fb.getUpflow();
			downflow+=fb.getDownflow();
		}
		FlowBean fb=new FlowBean(upflow, downflow);
		context.write(key, fb);
	}

}
