package aura.hadoop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 删除HDFS集群中的所有空文件和空目录   /
 * 		判断给定的目录是否是空目录
 获取给定的目录下的所有的文件或目录
 如果是文件：
 判断文件的长度是否为0
 0   删除
 如果是目录：
 判断是否是空目录：
 是：删除
 不是
 递归 操作
 获取该目录下的所有文件和目录
 如果是文件。。。
 如果是目录。。。。。。
 父目录是否是空目录

 * @author aura-bd
 *
 */

public class DeleteEmptyFileAndDic {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"),conf,"hadoop");
		Path path=new Path("/");
		deleteEmpty(fs, path);
	}

	/**
	 * 参数：
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void deleteEmpty(FileSystem fs,Path path) throws FileNotFoundException, IOException{
		//获取给定路径下的文件或目录
		FileStatus[] listStatus = fs.listStatus(path);
		//判断是否是一个空目录
		if(listStatus.length==0){
			fs.delete(path,false);
		}else{
			//不是空目录  进行循环遍历
			for(FileStatus fs1:listStatus){
				Path childpath=fs1.getPath();
				//判断是否是文件
				if(fs1.isFile()){
					if(fs1.getLen()==0){
						fs.delete(childpath, false);
					}
				}else{
					deleteEmpty(fs,childpath);
				}
			}
			//到这里   需要判断父目录是否是空
			FileStatus[] listStatus02=fs.listStatus(path);
			if(listStatus02.length==0){
				deleteEmpty(fs,path);
			}

		}
	}

}
