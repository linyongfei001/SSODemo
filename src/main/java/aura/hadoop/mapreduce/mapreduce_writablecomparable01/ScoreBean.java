package aura.hadoop.mapreduce.mapreduce_writablecomparable01;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
/**
 * WritableComparable<T>  泛型指的是用于比较的类型
 * @author aura-bd
 *
 */
public class ScoreBean implements /*Writable*/WritableComparable<ScoreBean>{
	private String course;
	private int score;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ScoreBean(String course, int score) {
		super();
		this.course = course;
		this.score = score;
	}
	public ScoreBean() {
		super();
	}
	@Override
	public String toString() {
		return  course + ", " + score ;
	}
	//序列化方法
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(course);
		out.writeInt(score);

	}
	//反序列化方法
	@Override
	public void readFields(DataInput in) throws IOException {
		this.course=in.readUTF();
		this.score=in.readInt();

	}
	//指定排序的比较规则
	//相同科目  相同分数
	@Override
	public int compareTo(ScoreBean o) {
		//比较科目
		int tmp=this.getCourse().compareTo(o.getCourse());
		//科目相同
		if(tmp==0){
			//比较分数
			tmp=this.getScore()-o.getScore();
			//return tmp;
		}
		return tmp;
	}



}
