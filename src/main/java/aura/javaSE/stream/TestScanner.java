package day17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestScanner {

	public static void main(String[] args) throws IOException {
		// Scanner
		//                          键盘输入流
		//                           (数据源)
//		Scanner input = new Scanner(System.in);

//		int n = input.nextInt();
//		System.out.println(n);
	/*	int n ;
		if(input.hasNextInt()) {//true
			n = input.nextInt();
			System.out.println(n);
		}else {
			System.out.println("不是整数");
		}*/
		
/*		FileInputStream fin = new FileInputStream("d:/data/a.txt");
		Scanner input = new Scanner(fin);
		
		String s = input.nextLine();
		System.out.println(s);
		
		fin.close();
		input.close();*/

		Scanner input = new Scanner("aa bb cc dd ee ff");
//		String s = input.next();
		String s = input.nextLine();
		System.out.println(s);

	}

}
