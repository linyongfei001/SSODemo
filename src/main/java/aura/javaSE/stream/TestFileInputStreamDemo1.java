package aura.javaSE.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**练习：FileInputStream*/
public class TestFileInputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//1.创建流对象
		File f1 = new File("d:/data/aoteman.jpg");
		FileInputStream fin = new FileInputStream(f1);
		BufferedInputStream bfin = new BufferedInputStream(fin);//板车
		File f2 = new File("d:/data/aotemannew.jpg");
		FileOutputStream fout = new FileOutputStream(f2);
		BufferedOutputStream bfout = new BufferedOutputStream(fout);//汽车
		//2.读。写
		int temp ;
		while((temp = bfin.read()) != -1) {
			bfout.write(temp);
		}
		bfout.flush();//刷新 缓冲区
		//3关闭
		fin.close();
		bfin.close();
		fout.close();
		bfout.close();
	}

}
