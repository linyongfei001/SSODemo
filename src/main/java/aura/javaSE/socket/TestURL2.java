package aura.javaSE.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class TestURL2 {

	public static void main(String[] args) throws IOException {
		//http://img15.3lian.com/2016/h1/233/d/157.jpg

		//下载此图片
		//定位器
		URL url = new URL("http://img15.3lian.com/2016/h1/233/d/157.jpg");
		//下载
		//读
		InputStream in = url.openStream();
		//写
		OutputStream out = new FileOutputStream("d:/data/datou.jpg");

		int temp;
		while((temp = in.read()) != -1) {
			out.write(temp);

		}

		in.close();
		out.close();






















	}

}
