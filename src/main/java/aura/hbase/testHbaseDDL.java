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

public class testHbaseDDL {
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
		HTableDescriptor htd=new HTableDescriptor(TableName.valueOf("test_java01"));
		//制定列族信息 建表至少指定一个列簇  否则报错
		//创建列簇描述器
		HColumnDescriptor hcd=new HColumnDescriptor("info".getBytes());
		htd.addFamily(hcd);
		admin.createTable(htd);
	}
	//查看表列表
	public static void listTables() throws IOException{
		TableName[] listTableNames = admin.listTableNames();
		for(TableName tn:listTableNames){
			System.out.println(tn.getNameAsString());
		}
	}

	//删除表  drop
	public static void deleteTable() throws IOException{
		//判断表是否存在   存在true   否则false
		String name="test_java01";
		boolean exists=admin.tableExists(TableName.valueOf(name));
		if(exists){
			//禁用表
			admin.disableTable(TableName.valueOf(name));
			//启用表
			//admin.enableTable(TableName.valueOf(name));
			//删除表
			admin.deleteTable(TableName.valueOf(name));
		}

	}


}
