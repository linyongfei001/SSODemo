package aura.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/*
 * 从随机地方开始读，读任意长度
 * 将hdfs的文件从随机的地方开始读   读取任意长度
 * 读取完成的内容方法hdfs的一个指定目录下
 * 流的方式：
 * 	hdfs(in)------hdfs(out)
 *
 */
public class RandomRead {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Random random =new Random();
        //conf
        Configuration conf=new Configuration();
        //fs
        FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"),conf,"hadoop");
        //hdfs的输入流
        FSDataInputStream in = fs.open(new Path("/myjob1"));
        in.seek(10L);
        //hdfs的输出流
        FSDataOutputStream out = fs.create(new Path("/testRandom"));
        //参数3：需要复制的字节长度
        IOUtils.copyBytes(in, out, 15L, true);
    }

}
