package aura.hadoop.mapreduce.mapreduce_writablecomparator01;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroup extends WritableComparator{
	/**
	 * compare方法中传入的两个参数：
	 * 	map端输出的key   这里指的Student
	 * 在这个方法中定义你的分组规则：
	 * 		按照课程进行分组
	 *能.出来什么看类型
	 */
	public MyGroup() {
		super(Student.class,true);
	}
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		//强转类型   转换为Student
		Student s1=(Student)a;
		Student s2=(Student)b;
		return  -s1.getCourse().compareTo(s2.getCourse());
	}

}
