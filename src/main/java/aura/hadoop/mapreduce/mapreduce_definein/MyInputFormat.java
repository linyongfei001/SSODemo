package aura.hadoop.mapreduce.mapreduce_definein;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
/**
 * 泛型1：map的输入的key的类型
 * 泛型2：map输入的value的类型
 * @author aura-bd
 *小文件合并
 *map的输入   我们每次读取一个文件的内容给mapper
 */

public class MyInputFormat extends FileInputFormat<Text, NullWritable>{

	/*
	 * InputSplit split,   文件切片信息
	 * TaskAttemptContext context   上下文
	 */
	@Override
	public RecordReader<Text, NullWritable> createRecordReader(InputSplit split, TaskAttemptContext context)
			throws IOException, InterruptedException {
		MyRecordReader mr=new MyRecordReader();
		//传递参数
		mr.initialize(split, context);
		return mr;
	}

}
