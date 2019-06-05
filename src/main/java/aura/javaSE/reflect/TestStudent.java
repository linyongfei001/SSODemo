package aura.javaSE.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

class Student{
	private int no;
	private String name;
	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
		System.out.println("带参数的构造器："+no+","+name);
	}
	public Student() {
		super();
		System.out.println("无参构造器");
	}
	public void show() {
		System.out.println("show");
	}
	public String f(String s,int n) {
		return "连接："+s + n;
	}
}
public class TestStudent {

	public static void main(String[] args) throws Exception {
		//获得 Student.class对应的 Class对象
		//1.
		Class<?> c = Class.forName("day19.Student");
		//2.
//		Class<Student>  c = Student.class;
		//3.
//		Class<? extends Student> c = new Student().getClass();
		//属性：

		System.out.println("------------属性：------------------");
		//所有属性 ，包括 私有的
		Field [] fs = c.getDeclaredFields();
		//获得所有的属性，不包含私有的
//		Field [] fs = c.getFields();
		for(Field f : fs) {
			System.out.println(f.getName());//属性名
			System.out.println(f.getType());
			System.out.println(Modifier.toString(f.getModifiers()));
		}
		//对属性赋值
		Field f = c.getDeclaredField("no");
		//安全管理器
//		System.setSecurityManager(new SecurityManager());
		f.setAccessible(true);//设置 访问权限
		Object obj = c.newInstance();//创建实例，对象
		f.set(obj, 22);//给属性赋值
		System.out.println(f.get(obj));//访问属性值
		//方法：------------------------------------------------------------
		System.out.println("---------------------方法-------------------------");
		Method [] ms = c.getDeclaredMethods();
		for(Method m : ms) {
			System.out.println(m.getName());
			System.out.println(m.getReturnType());//返回值类型
			System.out.println(Arrays.toString(m.getParameterTypes()));
		}
		//调用方法
		Method m1 = c.getDeclaredMethod("f",String.class,int.class);
		System.out.println(m1.invoke(obj, "abc",111));
		Method m2 = c.getDeclaredMethod("show");
		m2.invoke(obj);
		//构造器：
		System.out.println("------------------构造器----------------------------");
		Constructor [] crs = c.getDeclaredConstructors();//所有构造器
		for(Constructor cr : crs) {
			System.out.println(Arrays.toString(cr.getParameterTypes()));//参数类型
		}
		// 调用构造器
		Constructor cr1 = c.getDeclaredConstructor(int.class,String.class);
		cr1.newInstance(21,"张三");
		Constructor cr2 = c.getDeclaredConstructor();
		cr2.newInstance();














	}

}
