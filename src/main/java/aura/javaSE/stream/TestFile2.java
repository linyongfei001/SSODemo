package aura.javaSE.stream;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**File:目录*/
public class TestFile2 {

	public static void main(String[] args) {
		File f = new File("d:/data");
		System.out.println(f.exists());
		//是否 是目录，是 true
		System.out.println(f.isDirectory());
		//创建目录
//		f.mkdir();//父路径存在时，可以创建不存在的目录
//		f.mkdirs();//可以创建不存在的父目录

		System.out.println("---------------------------");
		//目录下的 ：文件 和 目录
		//获得 指定 目录 下 的 文件 和 目录的名称。String[]
/*		String[] strs = f.list();
		for(String s : strs) {
			System.out.println(s);
		}*/
		//过滤文件名，过滤出 ".txt"文本文件
		//                            文件名过滤器
	/*	String [] strs = f.list(new FilenameFilter() {
			@Override //           父路径    ，  文件名
			public boolean accept(File dir, String name) {
				return name.endsWith("txt");
			}
		});*/
	/*	String [] strs = f.list((dir,name)->name.endsWith("txt"));
		for(String s : strs) {
			System.out.println(s);
		}*/
		//获得 指定目录下的 ，文件 和 目录本身的File形式
	/*	File[] fs = f.listFiles();
		for(File fi: fs) {
			if(fi.isFile()) {
				System.out.println(fi.getName());
			}else {
				System.out.println("目录："+fi.getPath());
			}
		}*/
		//过滤文件名，过滤出 ".txt"文本文件
		File [] fs = f.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith("txt");
			}
		});
		for(File fi: fs) {
			System.out.println(fi.getName());
		}
	}

}
