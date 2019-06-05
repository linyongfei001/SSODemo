package aura.javaSE.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;
/**获得 IP对象*/
public class TestIP {

	public static void main(String[] args) throws UnknownHostException {
		//admin-PC/192.168.3.76
		//本机 的 IP封装对象
		System.out.println(InetAddress.getLocalHost());
		//admin-PC/192.168.3.76
		//根据主机名称 获得 IP对象
		System.out.println(InetAddress.getByName("LinYF"));
		InetAddress address = InetAddress.getByName("LinYF");
		//admin-PC
		System.out.println(address.getHostName());
		//192.168.3.76
		System.out.println(address.getHostAddress());
	}

}
