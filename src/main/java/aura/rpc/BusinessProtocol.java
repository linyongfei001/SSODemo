package aura.rpc;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * RPC协议： 用来定义服务
 * 要实现VersionedProtocol这个接口： 不同版本的Server和Client之前是不能进行通信的
 */
public interface BusinessProtocol {
    void mkdir(String path);
    void hello(String name);

    String getName(String name);

    long versionID = 345043000L;
}
