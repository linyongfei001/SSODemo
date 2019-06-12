package aura.hadoop.mapreduce.mapreduce_reducejoin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Order_Product_Join {
	//key   pid   value：Text
	static class MyMapper extends Mapper<LongWritable, Text, Text, Text>{
		//如果在map执行之前获取到文件名  就可以办
		/**
		 * 参数  上下文对象  读取你的文件信息    读取的是切片信息
		 */
		String filename="";
		@Override
		protected void setup(Context context)
				throws IOException, InterruptedException {
			//获取的是当前maptask的输入切片
			InputSplit inputSplit = context.getInputSplit();
			//强转文件切片
			FileSplit fs=(FileSplit)inputSplit;
			//获取文件名
			filename=fs.getPath().getName();
		}
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			//获取每一行内容
			String[] split = value.toString().split("\t");
			//进行发送
			//文件是订单信息   1001	20150710	P0001	2
			if(filename.equals("ord")){
				context.write(new Text(split[2]), new Text("o"+split[0]+"\t"+split[1]+"\t"+split[3]));
			}else{
				//商品信息  P0001	灏忕背5	C01	2000
				context.write(new Text(split[0]), new Text("p"+split[1]+"\t"+split[2]+"\t"+split[3]));

			}

		}

	}
	static class MyReducer extends Reducer<Text, Text, Text, Text>{
		//相同关联条件的数据   两部分数据    订单    商品
		// P0001	        1001	20150710	2  多
		// P0001			灏忕背5	C01	2000   一
		@Override
		protected void reduce(Text key, Iterable<Text> values,
							  Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			//创建一个集合  存储商品信息 和  订单信息
			List<String> ordList=new ArrayList<String>();//多
			List<String> proList=new ArrayList<String>();  //1
			for(Text v:values){
				String info=v.toString();
				if(info.startsWith("o")){
					ordList.add(info.substring(1));
				}else{
					proList.add(info.substring(1));
				}
			}
			//进行拼接
			for(String s:ordList){//订单
				context.write(key, new Text(s+"\t"+proList.get(0)));
			}
		}


	}
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		System.setProperty("HADOOP_USER_NAME", "hadoop");
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);

		job.setJarByClass(Order_Product_Join.class);

		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);


		FileInputFormat.addInputPath(job, new Path("hdfs://hadoop01:9000/joinin"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://hadoop01:9000/joinout01"));

		job.waitForCompletion(true);
	}

}
