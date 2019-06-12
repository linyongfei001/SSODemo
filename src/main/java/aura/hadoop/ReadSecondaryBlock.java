package aura.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.IOUtils;

/*
 * 手动拷贝某个特定的数据块（比如某个文件的第二个数据块）
 * 从hdfs-----hdfs
 * 流
 */
public class ReadSecondaryBlock {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        //conf
        Configuration conf=new Configuration();
        //fs
        FileSystem fs=FileSystem.get(new URI("hdfs://myjob/"),conf,"hadoop");
        //输入
        FSDataInputStream in = fs.open(new Path("/hadoop-2.7.6.tar.gz"));
        //获取第二个快的偏移量
        //获取第二个快
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/hadoop-2.7.6.tar.gz"), false);
        //System.out.println(listFiles.next().getLen());
        //System.out.println(listFiles.next().getLen());
        //数据块   给定的路径下有几个块就有几个长度
        BlockLocation[] blockLocations = listFiles.next().getBlockLocations();
        BlockLocation secondary=blockLocations[1];
        //获取第二个快 的偏移量
        long offset = secondary.getOffset();
        in.seek(offset);
        //获取第二个快的长度
        long length = secondary.getLength();
        //输出
        FSDataOutputStream out = fs.create(new Path("/hadoop_secondary_block"));
        IOUtils.copyBytes(in, out, length, true);
    }

}
