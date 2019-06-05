package aura.javaSE.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**发送方*/
public class TestClinetUDP {

	public static void main(String[] args) throws IOException {
		String s = "hello";
		byte [] b = s.getBytes();
		//打包
		InetAddress address = InetAddress.getLocalHost();
		//                                                       接收方：接收方的端口
		DatagramPacket dp = new DatagramPacket(b, b.length,address,5678);
		//发送方
		DatagramSocket ds = new DatagramSocket(8888);
		ds.send(dp);

	}

}
