package aura.javaSE.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**接收方*/
public class TestServerUDP {

	public static void main(String[] args) throws IOException {
		//
		byte [] b = new byte[100];
		//打包：准备

		DatagramPacket dp = new DatagramPacket(b, b.length);
		//接收
		DatagramSocket ds = new DatagramSocket(5678);
		ds.receive(dp);
		//展示shuju
		byte [] b1 = dp.getData();
		System.out.println(new String(b1));
	}

}
