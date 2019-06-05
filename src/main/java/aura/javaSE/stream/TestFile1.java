package aura.javaSE.stream;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**File:文件的*/
public class TestFile1 {

	public static void main(String[] args) throws IOException {
		// /  \\
//		File f = new File("d:/data/a.txt");
//		File f = new File("a.txt");
		// 父路径 ,子路径
//		File f = new File("d:/data","a.txt");
		//父路径（File）
		File f1 = new File("d:/data");
		File f = new File(f1,"a.txt");
		//文件 或 目录 是否存在，存在 true
		System.out.println(f.exists());//false
		//新建文件
		f.createNewFile();
		System.out.println(f.exists());//true
		//获得 文件名
		System.out.println(f.getName());
		//获得路径
		System.out.println(f.getPath());
		//获得 绝对路径
		System.out.println(f.getAbsolutePath());
		//获得 父路径
		System.out.println(f.getParent());
		//文件是否 可读 ，可读 true
		System.out.println(f.canRead());
		//文件 是否 可写，可写 true
		System.out.println(f.canWrite());
		//是否 是文件，是文件 true
		System.out.println(f.isFile());
		//最后一次修改的时间
		System.out.println(f.lastModified());//1527644890851
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(f.lastModified());
		System.out.println(format.format(date));
		//文件 的内容长度（字节数）
		long len = f.length();//"hello"
		System.out.println(len);//5字节
		//删除文件
		f.delete();


	}

}
