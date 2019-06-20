package aura.hbase;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;


public class SplitPageHbase {
	static Connection connect=null;
	static HBaseAdmin admin =null;
	static Configuration conf=null;
	static Table table =null;
	//初始化方法   用于获取连接  获取表操作对象
	public static void init() throws IOException{
		conf = HBaseConfiguration.create();
		//制定zookeeper
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
		//获取连接
		connect = ConnectionFactory.createConnection(conf);
		//获取hbaseadmin对象  admin对象是hbase   ddl的句柄
		admin= (HBaseAdmin)connect.getAdmin();
		table= connect.getTable(TableName.valueOf("stu_info"));
	}

	public static ResultScanner getPageData(String startrow,int pageSize) throws IOException{
		Scan scan=new Scan();
		if(!("".equals(startrow)||startrow==null)){
			//这个方法只是设置开始扫描的startrow   不一定非要存在   如果不存在  从大于这个startrow的最小的行键开始返回
			scan.setStartRow(startrow.getBytes());
		}

		Filter pf=new PageFilter(pageSize);
		scan.setFilter(pf);
		ResultScanner scanner = table.getScanner(scan);
		return scanner;
	}
	public static ResultScanner getPageData01(int pageIndex,int pageSize) throws IOException{
		//第一步需将pageIndex===startrow
		String startrow=getCurrentRow(pageIndex,pageSize);
		ResultScanner pageData = getPageData(startrow, pageSize);
		return pageData;
	}

	private static String getCurrentRow(int pageIndex, int pageSize) throws IOException {
		//pageIndex---startrow
		//pageIndex<=1  返回第一页
		if(pageIndex<=1){
			return "";
		}else{
			//不是第一页   需要获取的就是当前页的startrow
			//获取上一页的最后一个行键
			String startrow="";
			for(int i=0;i<pageIndex-1;i++){
				ResultScanner pageData = getPageData(startrow, pageSize);
				String lastrow="";
				Iterator<Result> it = pageData.iterator();
				//当前的迭代器的最后一个就是你的前一页的最后一个行键
				while(it.hasNext()){
					lastrow=new String(it.next().getRow());
				}
				//add方法
				String newrow=new String(Bytes.add(lastrow.getBytes(), "00x0".getBytes()));
				startrow=newrow;
			}
			return startrow;
		}
	}

	public static void main(String[] args) throws IOException {
		//用户传入的参数  pagesize   pageindex
		init();
		//''===默认从第一个开始返回  null  '' null=======默认返回第一页
		ResultScanner pageData = getPageData01(5, 2);
		Iterator<Result> it = pageData.iterator();
		while(it.hasNext()){
			System.out.println(new String(it.next().getRow()));
		}
	}

}
