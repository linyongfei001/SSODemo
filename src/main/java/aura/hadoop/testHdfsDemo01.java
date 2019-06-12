package aura.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

public class testHdfsDemo01 {
	public static void main(String[] args) throws InterruptedException, URISyntaxException, IOException {
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"),conf,"hadoop");
		//创建文件夹    级联创建  hadoop fs  -mkdir -p
		/*boolean ifmk=fs.mkdirs(new Path("/bd1804_01/aa/bb/cc"));
		System.out.println(ifmk);*/
		//删除文件夹   文件   空目录  非空目录
		//fs.delete(new Path("/cc"));
		//参数1：删除的路径   参数2：是否递归删除  true  需要递归  false：不递归
		//fs.delete(new Path("/bd1804_01"), true);
		/*fs.copyFromLocalFile(src, dst);//上传
		fs.copyToLocalFile(src, dst);//下载
*/
		//hadoop fs -ls /
		//查看指定目录下的文件信息
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), false);
		//循环遍历输出
		while(listFiles.hasNext()){
			//System.out.println(listFiles.next());
			//LocatedFileStatus   描述hdfs上的文件或目录信息的对象
			LocatedFileStatus next = listFiles.next();
			System.out.println(next.getPath());
			System.out.println(next.getLen());//获取数据的真实长度
			System.out.println(next.getBlockSize());//获取块的大小
			/*//获取的是块存储信息
			 *//**
			 * BlockLocation   封装的是数据块的详细信息
			 * 数据块的偏移量+数据块的存储位置（副本）
			 * 0（数据块的其实偏移量）,119978（块的实际大小）,hadoop02,hadoop01（存储位置）
			 *
			 */
			BlockLocation[] blockLocations = next.getBlockLocations();
			System.out.println("999999999999999999999999999999");
			for(BlockLocation bl:blockLocations){
				System.out.println(bl.getOffset());//获取块的起始偏移量
				System.out.println(bl.getLength());//获取块的实际的大小
				System.out.println(Arrays.toString(bl.getHosts()));//获取存储的主机   数组
			}

		}

		//获取状态信息  返回的是状态信息对象   FileStatus  封装的是一个路径的信息
		//这种方式只能返回文件或目录的状态信息   不能返回块信息   获取文件或目录的信息都可以
	/*	FileStatus[] listStatus = fs.listStatus(new Path("/"));
		
		for(FileStatus ff:listStatus){
			System.out.println("----------------------");
			System.out.println(ff.getPath());
			System.out.println(ff.getLen());
			System.out.println(ff.getReplication());
			System.out.println(ff.getBlockSize());
		}*/
		fs.close();
	}

}
