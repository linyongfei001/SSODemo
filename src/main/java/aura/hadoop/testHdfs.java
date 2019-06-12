package aura.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class testHdfs {
    public static void main(String[] args) {
        //hdfs连接有两个重要的对象    配置文件的
        //对象01    配置文件对象  作用？   配置文件从哪里来的？
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        Configuration conf = new Configuration();
        //对象02    文件系统对象     FileSystem  分布式文件系统的对象   文件系统的句柄
        //fs就是hdfs文件系统的抽象
        FileSystem fs= null;
        try {
            fs = FileSystem.get(conf);
            System.out.println(fs);
            //文件上传
            /**
             * 参数1：本地的文件路径
             * 参数2：hdfs的路径？？？？？
             */
            /*
             * path对象是一个路径对象hdfs的内置对象  类似于windows下的file
             */
            Path src=new Path("D:\\test\\test.txt");
            //文件上传   这种方式传到哪里了=====本地了   默认情况下上传到工程所在的盘符下
            //上传成功之后生成两个文件    test1804_tmp上传的原始文件    test1804_tmp.crc文件，是什么文件？校验文件
            Path dst=new Path("/myjob/test");
            fs.copyFromLocalFile(src, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
