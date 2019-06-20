package aura.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;

/**
 * 模拟 Hadoop 构建一个RPC服务端
 */
public class MyNamenode {

    public static void main(String[] args) {

        try {
            RPC.Server server = new RPC.Builder(new Configuration())
                    .setProtocol(BusinessProtocol.class)
                    .setInstance(new BusinessIMPL())
                    .setBindAddress("localhost")
                    .setPort(6789)
                    .build();

            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
