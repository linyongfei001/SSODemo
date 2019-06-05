package aura.javaSE.socket;

import java.net.MalformedURLException;
import java.net.URL;

public class TestURL1 {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:8900/data/a.txt?id=1&page=2#hello");
		//http协议
		System.out.println(url.getProtocol());
		//127.0.0.1 服务器主机
		System.out.println(url.getHost());
		//8900端口号
		System.out.println(url.getPort());
		//80 默认端口
		System.out.println(url.getDefaultPort());
		//id=1&page=2
		System.out.println(url.getQuery());
		//hello 片段
		System.out.println(url.getRef());

	}

}
