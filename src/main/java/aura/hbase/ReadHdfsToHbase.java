package aura.hbase;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.html.dom.HTMLTableRowElementImpl;
/**
 * 添加依赖包的时候  必须先添加hadoop的  再添加hbase的  否则有可能汇报jar包冲突的问题
 * @author aura-bd
 *
 */
public class ReadHdfsToHbase {
	//
	static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			context.write(value, NullWritable.get());
		}

	}
	//这个reduce作用是写入hbase中  表操作   put
	//继承TableReducer  表操作reducer
	//泛型1：map的输出key   泛型2：map的输出的value  泛型3：reduce的输出的key的类型
	//不需要指定输出的value类型   TableReducer内部帮你封装好reduce的输出的value类型
	/*
	 * public abstract class TableReducer<KEYIN, VALUEIN, KEYOUT>
extends Reducer<KEYIN, VALUEIN, KEYOUT, Mutation  写死的>
reduce输出的value的类型默认Mutation（抽象类）（hbase的put有关）   子类put   delete  append   写出的时候reduce的输出的
value就是habse的put(封装的就是需要插入的数据)、delete、append
	 */
	static class MyReducer extends TableReducer<Text, NullWritable, NullWritable>{
		@Override
		protected void reduce(Text key, Iterable<NullWritable> values,
							  Reducer<Text, NullWritable, NullWritable, Mutation>.Context context)
				throws IOException, InterruptedException {
			//业务逻辑  解析map发送过来的数据   封装成put对象
			for(NullWritable nvl:values){
				String[] split = key.toString().split(",");
				//封装行键    stu_info  列族：info
				Put p=new Put(split[0].getBytes());
				if(split.length==5){
					p.addColumn("info".getBytes(),"name".getBytes(), split[1].getBytes());
					p.addColumn("info".getBytes(), "sex".getBytes(), split[2].getBytes());
					p.addColumn("info".getBytes(), "age".getBytes(), split[3].getBytes());
					p.addColumn("info".getBytes(), "department".getBytes(), split[4].getBytes());
				}else if(split.length==3){
					/*
					 * 96001,45,MA
						97001,ZS,MA
						98000,MS
					 */
					if(Pattern.matches("[0-9]*", split[1])){
						p.addColumn("info".getBytes(), "age".getBytes(), split[1].getBytes());
					}else{
						p.addColumn("info".getBytes(), "name".getBytes(), split[1].getBytes());
					}
					p.addColumn("info".getBytes(), "department".getBytes(), split[2].getBytes());

				}else{
					p.addColumn("info".getBytes(), "department".getBytes(), split[1].getBytes());
				}
				context.write(NullWritable.get(), p);
			}
		}

	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		//加载配置文件
		Configuration conf=new Configuration();
		//指定zookeeper的路径
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");

		//启动一个job
		Job job=Job.getInstance(conf);
		//设置jar包主类
		job.setJarByClass(ReadHdfsToHbase.class);

		//设置map 的类
		job.setMapperClass(MyMapper.class);

		//建表
		String tablename="stu_info";
		Connection connect = ConnectionFactory.createConnection(conf);
		Admin admin = connect.getAdmin();
		if(!admin.tableExists(TableName.valueOf(tablename))){
			HTableDescriptor htd=new HTableDescriptor(TableName.valueOf(tablename));
			HColumnDescriptor hcd=new HColumnDescriptor("info".getBytes());
			htd.addFamily(hcd);
			admin.createTable(htd);
		}
		//设置reduce的类   TableMapReduceUtil工具类  操作表  初始化map或reduce类（表操作的）
		//参数1：表名  参数2：reduce类   参数3：job
		//这个方法会报错  报jar报依赖冲突
		//TableMapReduceUtil.initTableReducerJob("stu_info", MyReducer.class, job);
		TableMapReduceUtil.initTableReducerJob("stu_info", MyReducer.class, job, null, null, null, null, false);
		//指定map的输出
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);

		//指定reduce的输出
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Put.class);

		//指定输入  hdfs://hadoop01:9000
		FileInputFormat.addInputPath(job, new Path("hdfs://bd1804/stuin"));
		//输出路径需要
		FileOutputFormat.setOutputPath(job, new Path("hdfs://bd1804/out"));
		job.waitForCompletion(true);
	}

}
