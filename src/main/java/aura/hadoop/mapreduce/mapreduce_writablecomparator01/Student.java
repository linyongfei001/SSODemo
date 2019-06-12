package aura.hadoop.mapreduce.mapreduce_writablecomparator01;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Student implements WritableComparable<Student>{
	private String course;
	private String name;
	private double avg;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public Student(String course, String name, double avg) {
		super();
		this.course = course;
		this.name = name;
		this.avg = avg;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return  course + "\t" + name + "\t" + avg;
	}
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(course);
		out.writeUTF(name);
		out.writeDouble(avg);

	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.course=in.readUTF();
		this.name=in.readUTF();
		this.avg=in.readDouble();
	}
	//排序的方法  按照分数进行排序
	//先按照课程  再按照平均分
	@Override
	public int compareTo(Student o) {
		/*double tmp=o.getAvg()-this.getAvg();
		if(tmp==0){
			return 0;
		}else if(tmp>0){
			return 1;
		}else{
			return -1;
		}*/
		int tmp=o.getCourse().compareTo(this.getCourse());
		if(tmp==0){
			double tmp1=o.getAvg()-this.getAvg();
			return tmp1>=0?1:-1;
		}
		return tmp;

	}


}
