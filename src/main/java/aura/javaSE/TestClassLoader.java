package aura.javaSE;
class Exam{
	static {
		System.out.println("static_Exam");
	}
}
public class TestClassLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		//查看一下 所有的类加载器
	/*	ClassLoader c = Exam.class.getClassLoader();
		//sun.misc.Launcher$AppClassLoader@2a139a55 系统类加载器 （应用类加载器）
		System.out.println(c);
		//sun.misc.Launcher$ExtClassLoader@7852e922扩展类加载器
		System.out.println(c.getParent());
		//根类加载器
		System.out.println(c.getParent().getParent());*/

		//怎么加载？
		//1.
		//获得 系统类加载器 ,
		//可以加载 类，不能引起类的初始化    javac Exam.java    java day19.Exam.class
		ClassLoader.getSystemClassLoader().loadClass("day19.Exam");
		//2.加载并且初始化
		//               类             ，true进行初始化，指定 加载器
		Class.forName("day19.Exam",true,ClassLoader.getSystemClassLoader());
//		Class.forName("day19.Exam");

	}

}
