package aura.hadoop.mapreduce.mapreduce_writablecomparable02;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
	private String phonenum;
	private int upflow;
	private int downflow;
	private int sumflow;
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
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
	public FlowBean() {
		super();
	}
	public FlowBean(String phonenum, int upflow, int downflow, int sumflow) {
		super();
		this.phonenum = phonenum;
		this.upflow = upflow;
		this.downflow = downflow;
		this.sumflow = sumflow;
	}
	@Override
	public String toString() {
		return phonenum + "\t" + upflow + "\t" + downflow + "\t"
				+ sumflow;
	}
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(phonenum);
		out.writeInt(upflow);
		out.writeInt(downflow);
		out.writeInt(sumflow);

	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.phonenum=in.readUTF();
		this.upflow=in.readInt();
		this.downflow=in.readInt();
		this.sumflow=in.readInt();

	}
	//排序的  倒叙   上行   下行  总
	@Override
	public int compareTo(FlowBean o) {
		int tmp=o.getUpflow()-this.getUpflow();
		if(tmp==0){
			tmp=o.getDownflow()-this.getDownflow();
			if(tmp==0){
				tmp=o.getSumflow()-this.getSumflow();
			}
		}
		return tmp;
	}


}
