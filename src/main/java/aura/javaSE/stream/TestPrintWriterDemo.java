package aura.javaSE.stream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestPrintWriterDemo {

	public static void main(String[] args)  {
		// BufferedReader 读一行
		//PrintWriter 写一行

		//1
		//包装一个字符流
		try(BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter("d:/data/name.txt");){

			//2
			String sr;//读到的内容存储
			while(true) {
				sr = bfr.readLine();
				if(sr.equals("q")) {
					break;
				}
				pw.println(sr);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	/*	//3.
		bfr.close();
		pw.close();*/
	}

}
