package aura.javaSE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class MyClassLoader extends ClassLoader{
	//自己要加载的类的路径  d:/data/
	String path;
	MyClassLoader(String path){
		this.path = path;
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = null;
		//用 流 把 Hello.class文件读出来  d:/data/Hello.class
		//路径  path  = d:/data/  + Hello.class;
		path = path + name.replace(".", "/").concat(".class");
		File f = new File(path);
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(f);
			byte [] b = new byte[fin.available()];
			int len = fin.read(b);
			//解析成方法区 数据结构
			c = this.defineClass(name,b, 0, len);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


		return c;
	}

}
public class TestMyLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		//创建一个 自定义类加载器  d:/daata/   Hello.class
		MyClassLoader mycloader = new MyClassLoader("d:/data/");
		Class<?> c = Class.forName("Hello", true, mycloader);
		//day19.MyClassLoader@4e25154f
		System.out.println(c.getClassLoader());//
	}

}
