package aura.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZKAPI {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk=new ZooKeeper("hadoop01:2181,hadoop02:2181,hadoop03:2181", 8000, null);
		//创建节点
		/**
		 * 参数1：节点路径
		 * 参数2：节点存储的数据  byte[]
		 * 参数3：权限
		 * 参数4：节点的属性   节点的模式   永久与编号  永久无编号  临时有编号  临时无编号
		 * OPEN_ACL_UNSAFE  最大权限
		 */
		//zk.create("/java_test01", "hello nihao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//程序运行结束  客户端就关闭了  临时节点被销毁了
		//zk.create("/java_test02", "hello nihao".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		//zk.close();
		//删除节点
		//zk.delete("/java_test01", -1);
		//不能删除非空节点
		//zk.delete("/bd1804_test02", -1);
		//修改节点内容
		//zk.setData("/test05", "hehehe".getBytes(), -1);
		String ss=new String(zk.getData("/test", null, null));
		System.out.println(ss);
		//获取子节点
		/*List<String> children = zk.getChildren("/bd1804_test02", null, null);
		System.out.println(children);*/
		//判断节点是否存在  如果节点不存在  null  存在则返回状态信息
		/*Stat exists = zk.exists("/test05", null);
		System.out.println(exists.getCtime());*/
	}

}
