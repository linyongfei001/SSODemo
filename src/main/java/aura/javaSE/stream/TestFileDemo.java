package aura.javaSE.stream;

import java.io.File;

/**练习：File*/
public class TestFileDemo {
	//显示目录下的 所有子目录和文件
	public void show(File f) {

		if(f.exists()) {//存在
			if(f.isDirectory()) {//是目录
				File[] fs = f.listFiles();
				if(fs == null) {
					return;
				}
				for(File ff: fs) {
					if(ff.isFile()) {
						System.out.println(ff.getName());
					}else {
						System.out.println("--  目录："+ff.getName());
						show(ff);//递归:是目录继续 show()
					}
				}
			}else {
				return;
			}
		}else {
			return;
		}
	}
	public static void main(String[] args) {
		File f = new File("d:/data");
		new TestFileDemo().show(f);

	}

}
