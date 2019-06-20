package aura.hbase;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ReadHbaseToHdfs {
	//从hbase的表中读取数据
	//泛型1：map输出的key的类型  泛型2：map输出的value的泛型
	/*
	 * public abstract class TableMapper<KEYOUT, VALUEOUT>
extends Mapper<ImmutableBytesWritable, Result, KEYOUT, VALUEOUT>
		ImmutableBytesWritable：封装的就是hbase表中的rowkey  一行一个rowkey  底层封装的是一个字节数组
		Result:封装的结果集  hbase中数据的结果集   cell
		map输出的key：text
		map输出的value：age  intWritable
	 */
	static class MyMapper extends TableMapper<Text, IntWritable>{
		Text mk=new Text();
		IntWritable mv=new IntWritable();
		//map函数的调用频率   hbase中的一行数据调用一次  一个行键调用一次
		@Override
		protected void map(ImmutableBytesWritable key, Result value,
						   Context context)
				throws IOException, InterruptedException {
			//取到hbase表中的数据  封装在Result value   只需要解析这个参数就可以了
			//这个结果集拿到的数据  get的数据   每一个result封装的是一个rowkey的数据   就是一行的数据
			/*
			 * 95021                column=info:age, timestamp=1533632431403, value=17
				 95021                column=info:department, timestamp=1533632431403, value=MA
				 95021                column=info:name, timestamp=1533632431403, value=\xD6\xDC\xB
				                      6\xFE
				 95021                column=info:sex, timestamp=1533632431403, value=\xC4\xD0
			 */
			List<Cell> listCells = value.listCells();
			String department="";
			int age=-1;
			//获取需要的字段  循环遍历结果集
			for(Cell c:listCells){
				//获取的一个是列的名字-----department的 age的   获取一个单元格的值
				//age    department   name   sex
				String qualifier=new String(c.getQualifier());
				String v=new String(c.getValue());
				if("department".equals(qualifier)){
					department=v;
				}else if("age".equals(qualifier)){
					age=Integer.parseInt(v);
				}
			}
			//如果没有部门或者没有年龄的咱们删除
			if(!"".equals(department)&&age!=-1){
				mk.set(department);
				mv.set(age);
				context.write(mk, mv);
			}
		}

	}

	static class MyReducer extends Reducer<Text, IntWritable, Text, DoubleWritable>{
		DoubleWritable rv=new DoubleWritable();
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
							  Context context)
				throws IOException, InterruptedException {
			int sum=0;
			int count=0;
			for(IntWritable v:values){
				count++;
				sum+=v.get();
			}
			double avg=(double)sum/count;
			rv.set(avg);
			context.write(key, rv);
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		conf.set("hbase.zookeeper.quorum", "hadoop01:2181,hadoop02:2181,hadoop03:2181");
		Job job=Job.getInstance(conf);
		job.setJarByClass(ReadHbaseToHdfs.class);

		//设置map的类
		/*result对象：get    scan  resultscanner---迭代器
		 * 1：表名
		 * 2：scan对象    hbase中的对象
		 * 3：mapper的类
		 * 4.map输出的key的类型
		 * 5.map输出的value的类型
		 */
		Scan scan=new Scan();//代表全表扫描

		TableMapReduceUtil.initTableMapperJob("stu_info", scan, MyMapper.class,
				Text.class, IntWritable.class, job);

		//设置reduce的类
		job.setReducerClass(MyReducer.class);

		//设定reduce的输出  对于在hbase表中读取的数据 无论map  reduce的输出是否一致  都需要设定reduce的
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		//输入
		//输出
		FileOutputFormat.setOutputPath(job, new Path("hdfs://bd1804/hbase_out001"));
		job.waitForCompletion(true);

	}

}
