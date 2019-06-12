package aura.javaSE.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**读：FileInputStream*/
public class TestFileInputStream1 {

	public static void main(String[] args) throws IOException {
		//d:/data/a.txt文件内容 读出来 ，在控制台上显示
		//输入 流 FileInputStream
		//1.创建流对象  new
		//FileNotFoundException
//		FileInputStream fin = new FileInputStream("d:/data/a.txt");
//		FileInputStream fin = new FileInputStream(new File("d:/data/a.txt"));
		File f = new File("d:/out/a.txt");
		FileInputStream fin = new FileInputStream(f);
		//转换流：  字符流      把 字节流 包装成 字符流
		InputStreamReader ir = new InputStreamReader(fin);
		//2.读数据  IOException
		int temp ;
		while((temp = ir.read()) != -1) {
			System.out.print((char)temp);
		}
		//3.关流
		fin.close();
		ir.close();//由 内 向 外关
//		ir.close();//关最外层的
	}

}









