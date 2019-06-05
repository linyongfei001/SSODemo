package aura.javaSE.socket.socket4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**服务器*/
public class Server4 {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(5678);
		Socket socket= server.accept();
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		Scanner in = new Scanner(is);
		PrintWriter out = new PrintWriter(os,true);

		System.out.println("\t---------------服务器----------------");
		//写，
		out.println("客户端连接服务器成功");
		//读，写
		String sr ,sw;
		Scanner input = new Scanner(System.in);
		while(true) {
			sr = in.nextLine();
			System.out.println("客户端说："+sr);
			System.out.print("服务器说:");
			sw = input.next();
			out.println(sw);
			if(sw.equals("end")) {
				break;
			}
		}
		socket.close();

	}

}
