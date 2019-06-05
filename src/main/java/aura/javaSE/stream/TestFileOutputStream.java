package aura.javaSE.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileOutputStream {

	public static void main(String[] args) throws IOException {
		//输出：String s  = "hello" 存到 d:/data/b.txt中、
		//1.
//		FileOutputStream fout = new FileOutputStream("d:/data/b.txt");
		File f = new File("d:/data/b.txt");
		//                                   true 追加 ，false覆盖,默认覆盖
		FileOutputStream fout = new FileOutputStream(f,true);
		//2.写
		String s = "abc";

		fout.write(97);
		fout.write(s.getBytes());
		//                    从什么位置开始写，写几个字节
//		fout.write(s.getBytes(), 2, 2);//"ll"
		//3.
		fout.close();

	}

}
