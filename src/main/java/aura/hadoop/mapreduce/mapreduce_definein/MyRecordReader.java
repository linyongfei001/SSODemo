package aura.hadoop.mapreduce.mapreduce_definein;

import java.io.IOException;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MyRecordReader extends RecordReader<Text, NullWritable>{

	//初始化方法  类似于setup方法  所有方法的起始方法   文件流创建对象放在这里  hdfs   fs.open(path)
	//conf   context.getConfiguration
	/*
	 * InputSplit split：文件切片对象      InputSplit--抽象类   目的获取路径
	 * TaskAttemptContext context：上下文对象的实例
	 */
	FSDataInputStream in=null;
	boolean isReader;//代表是否读取完毕   没有读取完毕false   读取完毕  true
	Text mapin_key=new Text();
	int len=0;
	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		//创建fs对象
		FileSystem fs=FileSystem.get(context.getConfiguration());
		//创建path
		FileSplit fss=(FileSplit)split;
		//获取当前切片的实际字节大小
		len=(int)fss.getLength();
		Path path = fss.getPath();
		//创建输入流
		in= fs.open(path);
	}

	/*
	 * 文件读取：
	 * FileInputFormat.addinputPath()
	 * 	创建一个流   输入流   hdfs流
	 * @see org.apache.hadoop.mapreduce.RecordReader#nextKeyValue()
	 * 返回值  boolean   代表是否读取完毕   完毕false   没有读取完毕返回true
	 */
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		//进行文件读取     整片文本一起读   读取完成  放在key中--map输入的key
		if(!isReader){
			//读取  指定的字节数组     整片
			byte[] b=new byte[len];
			in.readFully(b,0,len);
			//读取完成的内容  最终要给key赋值
			mapin_key.set(b);
			isReader=true;
			return true;//代表的是当前有没有可读的
		}else{
			return false;
		}
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return this.mapin_key;
	}

	@Override
	public NullWritable getCurrentValue() throws IOException, InterruptedException {
		return NullWritable.get();
	}

	//获取读取进度的方法
	@Override
	public float getProgress() throws IOException, InterruptedException {
		return isReader?1.0f:0.0f;
	}

	//关闭流的方法
	@Override
	public void close() throws IOException {
		in.close();

	}

}
