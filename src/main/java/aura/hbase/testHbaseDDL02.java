package aura.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.google.protobuf.ServiceException;

public class testHbaseDDL02 {
	static Connection connect=null;
	static HBaseAdmin admin =null;
	static Configuration conf=null;
	public static void main(String[] args) throws IOException, ServiceException {
		init();
		createTable();
		//listTables();
		//admin.checkHBaseAvailable(conf);
		//deleteTable();
	}

	//初始化方法   用于获取连接  获取表操作对象
	public static void init() throws IOException{
		conf = HBaseConfiguration.create();
		//制定zookeeper
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
		//获取连接
		connect = ConnectionFactory.createConnection(conf);
		//获取hbaseadmin对象  admin对象是hbase   ddl的句柄
		admin= (HBaseAdmin)connect.getAdmin();
	}

	//创建表
	public static void createTable() throws IOException{
		//创建表描述器对象
		//参数传入的是表名
		//HTableDescriptor htd=new HTableDescriptor("test_java01");
		//HTableDescriptor htd=new HTableDescriptor("test_java01".getBytes());
		//TableName对象    封装表名信息的
		HTableDescriptor htd=new HTableDescriptor(TableName.valueOf("test_java03"));
		//制定列族信息 建表至少指定一个列簇  否则报错
		//创建列簇描述器
		HColumnDescriptor hcd=new HColumnDescriptor("info".getBytes());
		htd.addFamily(hcd);
		//admin.createTable(htd);
		//建表的时候指定预分区
		/*region---rowkey范围
		 * 1:表描述器
		 * 2：startkey  起始的rowkey   第一个分区的endrow
		 * 3.终止rowkey   最后一个分区的起始row  中间的部分平分的
		 * 4.预分区的个数
		 *
		 */
		//admin.createTable(htd, "0000".getBytes(), "9999".getBytes(), 5);
		//字节数组中放的是每一个分区的rowkey范围  hbase的建表优化
		byte[][] splitKeys={"0000".getBytes(),"2255".getBytes(),"4444".getBytes()};
		admin.createTable(htd, splitKeys);
	}


}
