package aura.javaSE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//自定义安全管理器
class MySecurity extends SecurityManager{
	@Override
	public void checkRead(String file) {
		if(file.endsWith("txt")) {
			throw new SecurityException("不允许 读取 txt文件");
		}
	}

}
public class TestFileInputStream {

	public static void main(String[] args) throws IOException {
		//设置安全管理器
		System.setSecurityManager(new MySecurity());

		File f = new File("d:/data/a.txt");
		FileInputStream fin = new FileInputStream(f);
		char c = (char)fin.read();
		System.out.println(c);
		fin.close();

	}

}
