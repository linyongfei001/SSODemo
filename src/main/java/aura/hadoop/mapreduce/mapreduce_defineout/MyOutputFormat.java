package aura.hadoop.mapreduce.mapreduce_defineout;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * 泛型1：reduce端输出的key的类型  泛型2：reduce端输出的value的类型
 * key：输出
 * @author aura-bd
 *
 */
public class MyOutputFormat extends FileOutputFormat<Text, NullWritable>{

	@Override
	public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job)
			throws IOException, InterruptedException {
		FileSystem fs=FileSystem.get(job.getConfiguration());
		return new MyRecordwriter(fs);
	}

}
