package aura.javaSE.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**练习：FileOutputStream*/
public class TestFileOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		String name;
		//1对象
		FileOutputStream fout = new FileOutputStream("d:/data/name.txt");
		while(true) {
			System.out.println("输入名字：");
			name = input.next();
			if(name.equals("q")) {
				break;
			}
			//2写
			fout.write(name.getBytes());
			fout.write("\r\n".getBytes());
		}
		//3关
		fout.close();
		input.close();
	}

}
