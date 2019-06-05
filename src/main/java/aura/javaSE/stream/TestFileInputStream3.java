package aura.javaSE.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestFileInputStream3 {

	public static void main(String[] args) {

		// 读
		//1
		File f = new File("d:/data/a.txt");
		FileInputStream fin  = null;

		try {
			fin = new FileInputStream(f);
			//2.
			//文件 内容字节大小
			byte [] b = new byte[(int)f.length()];
			fin.read(b);
			//    存储到 字节数组的 起始位置，字节数
			//		fin.read(b, 1, 2);
			//把 字节数组 转换成 字符串
			String s = new String(b,"gbk");
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fin != null) {
				try {
					//3.
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
