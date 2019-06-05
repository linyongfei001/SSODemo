package aura.javaSE.socket.socket4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**客户端*/
public class Client4 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(),5678);
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		Scanner in  = new Scanner(is);
		PrintWriter out = new PrintWriter(os,true);
		System.out.println("\t------------客户端-----------------");
		//读。写
		String sr,sw;
		Scanner input = new Scanner(System.in);
		while(true) {
			//读
			sr = in.nextLine();
			System.out.println("服务器说:"+sr);
			System.out.print("客户端说:");
			sw = input.next();
			out.println(sw);
			if(sw.equals("end")) {
				break;
			}
		}

		socket.close();


	}

}
