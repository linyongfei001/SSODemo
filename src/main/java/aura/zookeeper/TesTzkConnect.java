package aura.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.ZooKeeper;

/**
 * 获取zookeeper的连接进行测试
 * @author aura-bd
 *
 */
public class TesTzkConnect {
	public static void main(String[] args) throws IOException {
		//创建对象
		//参数1：连接   主机名：2181  参数2：连接的超时时间 (ms) 参数3：监听对象  不需要监听传null
		ZooKeeper zk=new ZooKeeper("hadoop01:2181", 5000, null);
		System.out.println(zk);
	}
}
