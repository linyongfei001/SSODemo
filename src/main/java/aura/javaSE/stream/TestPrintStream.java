package aura.javaSE.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**PrintStream*/
public class TestPrintStream {

	public static void main(String[] args) throws IOException {
		// 把 一个 文件的内容读出来 ，在控制台上显示.
		//读
		//1
		FileInputStream fin = new FileInputStream("d:/data/a.txt");
		//2
		byte [] b = new byte[fin.available()];//获得 流中读到的字节数
		fin.read(b);
		String s = new String(b,"gbk");
		//3.
		fin.close();
		//写 PrintStream
		//System.in  键盘输入   System.out 控制台 输出
		PrintStream p = new PrintStream(System.out);
		p.println("内容："+s);
		p.close();


	}

}
