package aura.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class TestHbaseConnect {
	public static void main(String[] args) throws IOException {
		//加载配置文件
		//HBaseConfiguration conf=new HBaseConfiguration();
		// org.apache.hadoop.conf.Configuration
		Configuration conf=HBaseConfiguration.create();
		//需要制定zookeeper的通信地址
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
		//获取连接
		Connection connect = ConnectionFactory.createConnection(conf);
		//判断连接是否获取
		if(connect==null){
			System.out.println("连接不成功");
		}else{
			System.out.println("OK------"+connect);
		}
	}

}
