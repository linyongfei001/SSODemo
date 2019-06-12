package aura.hadoop.mapreduce.mapreduce_defineout;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class MyRecordwriter extends RecordWriter<Text, NullWritable>{
	private FileSystem fs;
	FSDataOutputStream out01;
	FSDataOutputStream out02;
	public MyRecordwriter(FileSystem fs) throws IllegalArgumentException, IOException{
		this.fs=fs;
		//创建输出流   输出到不同的目录中   2个流
		out01 = this.fs.create(new Path("/out/score_j01/jige"));
		out02 = this.fs.create(new Path("/out/score_bj01/bujige"));
	}


	//这个方法   是最后写入hdfs的方法       流的方式  hdfs的输出流
	//fs.create-------fs-----configuration  写之前创建
	@Override
	public void write(Text key, NullWritable value) throws IOException, InterruptedException {
		//判断分数是否及格  输出到不同的目录中
		String[] split = key.toString().split("\t");
		double score=Double.parseDouble(split[2]);
		if(score>=60){
			out01.write((key.toString()+"\n").getBytes());
		}else{
			out02.write((key.toString()+"\n").getBytes());
		}

	}

	//关闭流的方法
	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		out01.close();
		out02.close();
		fs.close();

	}

}
