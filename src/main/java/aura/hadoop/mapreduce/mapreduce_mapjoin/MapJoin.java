package aura.hadoop.mapreduce.mapreduce_mapjoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BufferedFSInputStream;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/*
 * 就是做关联的   map端
 */
public class MapJoin {
	//join过程 map进行了   关联结果  key：关联键   value：其他的   或者：key：关联结果  value：null
	static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
		//进行缓存文件的读取    读取的数据放在一个容器  java的容器  list   set   map
		//map更好一些  key：关联的条件   value：其他的
		Map<String,String> map=new HashMap<String,String>();
		@Override
		protected void setup(Context context)
				throws IOException, InterruptedException {
			//进行缓存文件的一个读取   缓存文件：本地
			//获取本地缓存文件  返回的是一个本地缓存的路径的数组
			Path[] localCacheFiles = context.getLocalCacheFiles();
			//现在只有一个缓存文件  路径
			Path path=localCacheFiles[0];
			//进行读取   P0001	灏忕背5	C01	2000
			BufferedReader br=new BufferedReader(new FileReader(path.toString()));
			String line=null;
			while((line=br.readLine())!=null){
				String[] split = line.split("\t");
				map.put(split[0], split[1]+"\t"+split[2]+"\t"+split[3]);
			}

		}
		Text mk=new Text();
		//map方法中就开始进行关联了   缓存中的数据必须提前读
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			//对订单数据进行读取   进行拼接关联   1001	20150710	P0001	2
			String[] split = value.toString().split("\t");
			//取出关联字段
			String glkey=split[2];
			//进行关联  如果在  证明可以关联上   如果不在   关联不上   外关联   内关联：关联不上  就不要   公共的数据
			if(map.containsKey(glkey)){
				//进行拼接
				String res=glkey+"\t"+split[0]+"\t"+split[1]+"\t"+split[3]+"\t"+map.get(glkey);
				//new Text()  相当于每一行创建一个对象
				mk.set(res);
				context.write(mk, NullWritable.get());
			}
		}

	}
	//不需要reduce
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		//System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		//conf.set("fs.defaultFS", "hdfs://hadoop01:9000/");
		Job job=Job.getInstance(conf);

		//指定主类  jar
		job.setJarByClass(MapJoin.class);

		//指定map和reduce的类
		job.setMapperClass(MyMapper.class);

		//指定输出
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		//注意：如果不需要reducetask  请将reducetask的个数设置为0   要不然默认一个
		job.setNumReduceTasks(0);

		//添加缓存文件   添加到每个节点的缓存中
		job.addCacheFile(new URI("/joinin/pro"));
		//指定输入路径  加载多的
		FileInputFormat.addInputPath(job, new Path("/joinin/ord"));
		FileOutputFormat.setOutputPath(job, new Path("/mapjoin01"));
		job.waitForCompletion(true);

	}

}
