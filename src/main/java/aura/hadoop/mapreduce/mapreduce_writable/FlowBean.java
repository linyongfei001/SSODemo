package aura.hadoop.mapreduce.mapreduce_writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

/**
 * Writable序列化组件
 */
public class FlowBean implements Writable{
	private int upflow;
	private int downflow;
	private int sumflow;


	@Override
	public String toString() {
		return  upflow + "," + downflow + ", " + sumflow;
	}

	public FlowBean() {
		super();
	}

	public FlowBean(int upflow, int downflow) {
		this.upflow = upflow;
		this.downflow = downflow;
		this.sumflow = this.upflow+this.downflow;
	}

	public int getUpflow() {
		return upflow;
	}

	public void setUpflow(int upflow) {
		this.upflow = upflow;
	}

	public int getDownflow() {
		return downflow;
	}

	public void setDownflow(int downflow) {
		this.downflow = downflow;
	}

	public int getSumflow() {
		return sumflow;
	}

	public void setSumflow(int sumflow) {
		this.sumflow = sumflow;
	}

	//序列化的方法     对象----二进制
	@Override
	public void write(DataOutput out) throws IOException {
		//将每一个需要的属性进行序列化
		out.writeInt(upflow);
		out.writeInt(downflow);
		out.writeInt(sumflow);

	}

	//反序列化     二进制-----对象  一定和序列化的顺序一致
	@Override
	public void readFields(DataInput in) throws IOException {
		this.upflow=in.readInt();
		this.downflow=in.readInt();
		this.sumflow=in.readInt();

	}

}
