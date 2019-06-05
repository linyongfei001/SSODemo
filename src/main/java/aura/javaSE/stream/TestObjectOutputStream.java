package aura.javaSE.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Student implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;//版本号
	private int age;
	private int no ;
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
}
public class TestObjectOutputStream {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
	/*	Student zhangsan = new Student();
		zhangsan.setNo(11);
		zhangsan.setName("张三");
		//想  存储 对象 zhangsan
		//序列化:把对象 以 二进制流的形式 存到 文件中.
		//1.
		File f = new File("d:/data/student.txt");
		FileOutputStream fout = new FileOutputStream(f);
		ObjectOutputStream objOut = new ObjectOutputStream(fout);
		//2.写，存
		objOut.writeObject(zhangsan);
		//3.
		objOut.close();*/

		//----反序列化：从文件中 把 对象 还原出来。---读出来：还原---------------------------------------------------------
		//1.
		File f = new File("d:/data/student.txt");
		FileInputStream fin = new FileInputStream(f);
		ObjectInputStream objIn = new ObjectInputStream(fin);
		//2.读，还原
		Student stu = (Student)objIn.readObject();
		System.out.println(stu.getNo()+","+stu.getName());
		//3.
		objIn.close();




	}

}
