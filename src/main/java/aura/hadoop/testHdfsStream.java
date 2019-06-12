package aura.hadoop;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * 用流的方式进行文件上传和下载
 * @author aura-bd
 *
 */
public class testHdfsStream {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"), conf, "hadoop");
		//流的方式进行文件上传   本地（本地读取流   输入流）-----hdfs（hdfs的流   输出流）
		/*//本地流   输入流
		FileInputStream in=new FileInputStream("C:\\testcrc.txt");
		//hdfs的输出流   create()   创建输出流
		FSDataOutputStream out = fs.create(new Path("/testcrc01"));
		//参数4：是否关闭流  true  关闭    false  不关闭
		IOUtils.copyBytes(in, out, 4096,true);*/
		//文件下载     hdfs（hdfs的输入流）-----本地（本地的输出流）
		FSDataInputStream in01 = fs.open(new Path("/myjob/test.txt"));
		in01.seek(5);
		//本地的输出流
		FileOutputStream out01=new FileOutputStream("D:\\test\\test1");
		IOUtils.copyBytes(in01, out01, 4096, true);
		fs.close();


	}

}
