package aura.javaSE;

import java.util.Arrays;
import java.util.Comparator;

class Student implements Comparable<Student>{
	private int no;
	private String name;


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Student(int no, String name) {
		this.no = no;
		this.name = name;
	}
	@Override
	public String toString() {
		return no+","+name;
	}

	@Override
	public int compareTo(Student o) {
		/*
		 * 规则：
		 */
		// this , o
	/*	if(this.no > o.no) {
			//正数
			return 1;
		}else if(this.no < o.no) {
			//负数
			return -1;
		}else {
			return 0;
		}*/
		return this.no - o.no;
/*		if(this.name.compareTo(o.name) > 0) {
			return 1;
		}else if(this.name.compareTo(o.name) < 0) {
			return -1;
		}else {
			return 0;
		}*/
//		return this.name.compareTo(o.name);
	}


}

//方式一：自己定义一个比较器
/*class MyComparator implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		// o1,o2
		if(o1.getName().compareTo(o2.getName()) > 0) {
			return 1;
		}else if(o1.getName().compareTo(o2.getName()) < 0 ) {
			return -1;
		}else {
			return 0;
		}
	}
}*/
public class TestComparable {
	public static void main(String[] args) {

		Student [] stus = new Student[3];// null,null,null
		Student guojing = new Student(2,"guojing");
		Student yangkang = new Student(1,"yangkang");
		Student huangrong = new Student(3,"huangrong");
		stus[0] = guojing;
		stus[1] = yangkang;
		stus[2] = huangrong;
		//自然升序
//		Arrays.sort(stus);
//		                    ,比较器Comparator
//		Arrays.sort(stus,new MyComparator());
		//方式二：匿名内部类
		Arrays.sort(stus, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
		//方式三：Lambda
		Arrays.sort(stus, (stu1,stu2)->{return stu1.getName().compareTo(stu2.getName());});

		for(Student stu :stus) {
			System.out.println(stu);
		}
		/*		int [] arr1 = {55,11,66,22,33};
		 */
		//Arrays排序 :自然顺序 升序
//		Arrays.sort(arr1);
//		System.out.println(Arrays.toString(arr1));
		//自然顺序：升序
//		String [] arr2 = {"cc","yy","aa"};
//		Arrays.sort(arr2);
//		for(String s: arr2) {
//		
//			System.out.println(s);
//		}
		//
	}

}
