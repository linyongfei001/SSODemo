package aura.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

/**
 * 测试hdfs的链接是否成功
 * @author aura-bd
 *
 */
public class testHdfs2 {
	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		//hdfs连接有两个重要的对象    配置文件的
		//对象01    配置文件对象  作用？   配置文件从哪里来的？
		//System.setProperty("HADOOP_USER_NAME", "hadoop");
		/**
		 * 文件上传成功了   集群中的副本2个设定的   通过这种方式上传的文件的副本是3个  为什么？
		 * 	这里加载的配置文件  不是集群   是哪里的？
		 * 		代码中的   jar包中
		 * 这里的conf对象默认加载的是hadoop-hdfs-2.7.6.jar包下的hdfs-default.xml文件
		 * conf对象就是加载配置文件
		 * 		hdfs-default.xml
		 * 		core-default.xml
		 * 		yarn-default.xml
		 * 		mapred-default.xml
		 *
		 * 		hdfs-site.xml
		 * 		core-site.xml
		 * 		yarn-site.xml
		 * 		mapred-site.xml
		 * conf对象加载配置文件的时候：
		 * jar包下加载》》》》src下加载    hdfs-default.xml   hdfs-site.xml>>>代码中的
		 * 生效的顺序：
		 * 	代码》》》》src下》》》》jar包中
		 *
		 */
		Configuration conf=new Configuration();
		//conf.set("dfs.replication", "5");
		//强制加载配置文件  加载指定的配置文件
		//conf.addResource("config/hdfs-site.xml");
		//conf.set("HADOOP_USER_NAME", "hadoop");
		//对象02    文件系统对象     FileSystem  分布式文件系统的对象   文件系统的句柄
		//fs就是hdfs文件系统的抽象
		/**
		 * 两个参数是k-v形式的
		 * 这个方法就对应于
		 *  <property>
		 <name>fs.defaultFS</name>
		 <value>hdfs://hadoop01:9000</value>
		 </property>
		 key：<name>fs.defaultFS</name>
		 value：<value>hdfs://hadoop01:9000</value>
		 设置分布式文件系统的入口
		 */
		//conf.set("fs.defaultFS", "hdfs://hadoop01:9000");
		//FileSystem fs=FileSystem.get(conf);
		/**
		 * 参数1：hdfs集群连接的主节点入口
		 */
		FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"), conf, "hadoop");
		System.out.println(fs instanceof DistributedFileSystem);
		//当前的src是否可以是hdfs路径     汇报权限问题
		/**
		 * 修改权限的集中方式：
		 * 	1）代码提交的时候进行设置参数
		 * 		右键》》》》run as>>>>  run configurations
		 * 		arguments>>>>>>>
		 * 		program arguments:代码中需要的参数   String[] args
		 * 		VM arguments: 代码运行中  jvm的参数
		 * 			jvm提交代码的时候的用户
		 * 			将用户名已添加上
		 * 		-DHADOOP_USER_NAME=hadoop
		 * 2）在代码中添加：
		 * 	conf.set("HADOOP_USER_NAME", "hadoop");
		 *
		 *	System.setProperty("HADOOP_USER_NAME", "hadoop");
		 *	FileSystem fs=FileSystem.get(new URI("hdfs://hadoop01:9000"), conf, "hadoop");
		 *3)在windows的环境变量中
		 *	HADOOP_USER_NAME=hadoop
		 *
		 *如果代码中和运行的时候都指定了用户名：
		 *先加载运行指定的参数  在加载代码中，代码中的参数会覆盖掉运行指定的参数
		 *最后生效的是代码中的
		 */
		//Path src=new Path("C:\\soft\\hadoop-2.7.6\\LICENSE.txt");
		Path src=new Path("/myjob1");
		Path dst=new Path("D:\\test");
		/**
		 * 文件上传成功之后  会生成两个文件    原始文件    CRC文件
		 * 		linux下查看一下：
		 * 			blk_1073741825    原始文件
		 blk_1073741825_1001.meta    元数据文件----crc文件   校验  存储的是原始文件的偏移量
		 校验数据的完整性    验证数据是否被修改了或损坏了   校验的时候与文件上传的时候的数据进行校验
		 * 			1)块的末尾追加不会影响校验
		 * 			2）块的中间追加   会影响校验    -----校验和错误
		 * 			结论：crc校验  从原始文件头---原始文件末尾  的校验，如果这部分内容不发生改变
		 * 				不会影响校验    这部分内容发生改变的修啊吧则会报校验和错误
		 *
		 * Exception in thread "main" org.apache.hadoop.fs.ChecksumException: Checksum error: /cc/testcrc at 0 exp: 1139544428 got: -1322830295
		 * 文件块损坏的异常
		 */
		//fs.copyFromLocalFile(src, dst);
		fs.copyToLocalFile(src, dst);
	}

}
