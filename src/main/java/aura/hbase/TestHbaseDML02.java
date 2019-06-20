package aura.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;

public class TestHbaseDML02 {
	static Connection connect=null;
	static HBaseAdmin admin =null;
	static Configuration conf=null;
	static HTable table=null;
	public static void main(String[] args) throws IOException {
		init();
		//putOneData();
		//putTenData();
		//putDatas();//497
		//putCacheData();
		//.deleteData();
		//getData();
		//scanData();
		scanFilter03();
	}
	//初始化方法   用于获取连接  获取表操作对象
	public static void init() throws IOException{
		conf = HBaseConfiguration.create();
		//制定zookeeper
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
		//获取连接
		connect = ConnectionFactory.createConnection(conf);
		//htable  dml操作的句柄  获取table对象的时候指定表名
		table = (HTable) connect.getTable(TableName.valueOf("user_info"));
	}
	//插入数据
	//一条数据一条数据插入  put '表'，‘rk’,'fa:co','v'
	public static void putOneData() throws IOException{
		//参数代表的是行键
		Put p=new Put("rk001".getBytes());
		//参数1：列族  参数2：列  参数3：值
		p.addColumn("info".getBytes(), "name".getBytes(), "zs".getBytes());
		table.put(p);
	}
	//一次性插入10条数据   rk随机数100内的  694
	public static void putTenData() throws IOException{
		long start = System.currentTimeMillis();
		Random ran=new Random();
		for(int i=0;i<10;i++){
			byte[] rk=(ran.nextInt(100)+"").getBytes();
			Put p=new Put(rk);
			if(i%2==0){
				p.addColumn("info".getBytes(), "name".getBytes(), ("zs"+i).getBytes());

			}else{
				p.addColumn("info".getBytes(), "age".getBytes(), (i+"").getBytes());
			}
			table.put(p);
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

	//批量数据插入
	public static void putDatas() throws IOException{
		long start = System.currentTimeMillis();
		List<Put> list=new ArrayList<Put>();
		Random ran=new Random();
		for(int i=0;i<10;i++){
			byte[] rk=(ran.nextInt(100)+"").getBytes();
			Put p=new Put(rk);
			if(i%2==0){
				p.addColumn("info".getBytes(), "name".getBytes(), ("zs"+i).getBytes());

			}else{
				p.addColumn("info".getBytes(), "age".getBytes(), (i+"").getBytes());
			}
			list.add(p);
		}
		table.put(list);
		long end = System.currentTimeMillis();
		System.out.println(end-start);

	}

	//块缓存插入数据   大量数据的插入
	public static void putCacheData() throws IOException{
		//可以将数据先放在缓存中
		long start = System.currentTimeMillis();
		List<Put> list=new ArrayList<Put>();
		Random ran=new Random();
		//table设置参数   设置提交数据自动刷新  true  自动提交  false  不自动提交
		//把结果不提交  放在缓存中  缓存中达到一定的数据之后一次性提交    提升效率
		table.setAutoFlushTo(false);
		//设置缓存的大小
		table.setWriteBufferSize(100*1024*1024L);
		for(int i=0;i<10;i++){
			byte[] rk=(ran.nextInt(100)+"").getBytes();
			Put p=new Put(rk);
			if(i%2==0){
				p.addColumn("info".getBytes(), "name".getBytes(), ("zs"+i).getBytes());

			}else{
				p.addColumn("info".getBytes(), "age".getBytes(), (i+"").getBytes());
			}
			//list.add(p);
			table.put(p);
		}
		//table.put(list);
		//强制性刷新
		table.flushCommits();
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	//数据删除
	public static void deleteData() throws IOException{
		//单挑数据进行删除
		Delete d=new Delete("69".getBytes());
		d.addColumn("info".getBytes(), "age".getBytes());
		table.delete(d);
	}
	//批量删除  delete对象封装到list集合中


	//查询
	//get
	public static void getData() throws IOException{
		//单挑数据查询
		Get get = new Get("96".getBytes());
		get.addFamily("info".getBytes());
		Result result = table.get(get);
		//List<KeyValue> list = result.list();
		List<Cell> listCells = result.listCells();
		for(Cell c:listCells){
			System.out.println(new String(c.getRow()));//获取行键
			System.out.println(new String(c.getFamily()));
			System.out.println(new String(c.getQualifier()));
			System.out.println(new String(c.getValue()));
		}
		/*System.out.println(new String(result.getRow()));
		System.out.println(new String(result.getValue("info".getBytes(), "age".getBytes())));
		System.out.println(result.getColumn("info".getBytes(),
				"age".getBytes()));*/
	}

	//scan   全表扫描   指定行键范围  scan 't',{startrow endrow}
	public static void scanData() throws IOException{
		Scan scan=new Scan();
		//Scan scan=new Scan("2".getBytes());
		//Scan scan=new Scan("2".getBytes(), "4".getBytes());
		//全表扫描
		scan.setStartRow("2".getBytes());
		scan.setStopRow("3".getBytes());
		ResultScanner scanner = table.getScanner(scan);
		//ResultScanner  迭代器
		Iterator<Result> iterator = scanner.iterator();
		while(iterator.hasNext()){
			Result next = iterator.next();
			List<Cell> listCells = next.listCells();
			//每一个单元格打印一行
			for(Cell c:listCells){
				System.out.print(new String(c.getRow()));//获取行键
				System.out.print(new String(c.getFamily()));
				System.out.print(new String(c.getQualifier()));
				System.out.println(new String(c.getValue()));
			}
		}
	}

	//过滤器：  和表扫描
	public static void  scanFilter01() throws IOException{
		Scan scan=new Scan();
		//过滤器：提交查询之前
		//参数1  比较操作符   参数2  比较规则
		Filter f1=new RowFilter(CompareOp.GREATER, new BinaryComparator("baiyc_20150716_0008".getBytes()));
		Filter f2=new FamilyFilter(CompareOp.NOT_EQUAL, new BinaryComparator("base_info".getBytes()));
		Filter f3=new QualifierFilter(CompareOp.EQUAL, new BinaryComparator("Hobbies".getBytes()));
		Filter f4=new ValueFilter(CompareOp.EQUAL, new BinaryComparator("music".getBytes()));
		/*scan.setFilter(f2);
		scan.setFilter(f1);*/
		//上面的方式进行添加过滤器   添加多个最后一次添加的生效  scan.setFilter(f2)  添加过滤求只能添加一个

		//两个都起作用
		Filter flist=new FilterList(f1,f2,f3,f4);
		scan.setFilter(flist);

		ResultScanner scanner = table.getScanner(scan);
		//ResultScanner  迭代器
		Iterator<Result> iterator = scanner.iterator();
		while(iterator.hasNext()){
			Result next = iterator.next();
			List<Cell> listCells = next.listCells();
			//每一个单元格打印一行
			for(Cell c:listCells){
				System.out.print(new String(c.getRow()));//获取行键
				System.out.print(new String(c.getFamily()));
				System.out.print(new String(c.getQualifier()));
				System.out.println(new String(c.getValue()));
			}
		}
	}

	//过滤器：  和表扫描  专用过滤器
	public static void  scanFilter02() throws IOException{
		Scan scan=new Scan();
		//过滤器：提交查询之前     单列值过滤器：过滤列   对应的值的大小
		/*Filter sf=new SingleColumnValueFilter("base_info".getBytes(), "age".getBytes(), CompareOp.GREATER_OR_EQUAL,
				new BinaryComparator("22".getBytes()));*/
		//针对行键   前缀过滤器只能针对行键
		Filter pf=new PrefixFilter("zh".getBytes());
		scan.setFilter(pf);
		ResultScanner scanner = table.getScanner(scan);
		//ResultScanner  迭代器
		Iterator<Result> iterator = scanner.iterator();
		while(iterator.hasNext()){
			Result next = iterator.next();
			List<Cell> listCells = next.listCells();
			//每一个单元格打印一行
			for(Cell c:listCells){
				System.out.print(new String(c.getRow()));//获取行键
				System.out.print(new String(c.getFamily()));
				System.out.print(new String(c.getQualifier()));
				System.out.println(new String(c.getValue()));
			}
		}
	}

	//过滤器：  分页过滤器
	public static void  scanFilter03() throws IOException{
		Scan scan=new Scan();
		//过滤器：提交查询之前     单列值过滤器：过滤列   对应的值的大小
		//参数：每页显示的size大小  每页显示多少条数据
		scan.setStartRow("baiyc_20150716_0002".getBytes());
		Filter pf=new PageFilter(2);//默认从第一行开始的
		scan.setFilter(pf);
		ResultScanner scanner = table.getScanner(scan);
		//ResultScanner  迭代器
		Iterator<Result> iterator = scanner.iterator();
		while(iterator.hasNext()){
			Result next = iterator.next();
			List<Cell> listCells = next.listCells();
			//每一个单元格打印一行
			for(Cell c:listCells){
				System.out.print(new String(c.getRow())+"\t");//获取行键
				System.out.print(new String(c.getFamily())+"\t");
				System.out.print(new String(c.getQualifier())+"\t");
				System.out.println(new String(c.getValue())+"\t");
			}
		}
	}


}
