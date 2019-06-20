package aura.zookeeper;

import java.io.IOException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Watcher01 {
	static final String CONNECT_STRING="hadoop01:2181,hadoop02:2181,hadoop03:2181";
	static final int SESSION_TIME=5000;

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk=new ZooKeeper(CONNECT_STRING, SESSION_TIME, new Watcher(){

			//回调方法   触发监听的时候会进行回调
			//WatchedEvent   监听事件对象
			@Override
			public void process(WatchedEvent event) {
				System.out.println("--------------");
				String path = event.getPath();
				EventType type = event.getType();
				KeeperState state = event.getState();
				System.out.println(path+"==="+type+"==="+state);
			}

		});
		//添加监听   ls----getchildren   getdata   exists
		//参数2的3种传参方式：
		/*
		 * 1.null    不添加监听
		 * 2.true    添加监听    监听触发的时候回调创建对象时候的监听的process()
		 * null===None===SyncConnected   和你的监听没有关系
				/test07===NodeCreated===SyncConnected  监听触发的
			false:===null   不添加监听
			3.传一个watcher对象   触发监听的时候调用的是这里的process
				监听每触发一次都会回调process函数
		 */
		//zk.exists("/test09", false);
		zk.exists("/test", new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				System.out.println("========================**************");
				String path = event.getPath();
				EventType type = event.getType();
				KeeperState state = event.getState();
				System.out.println(path+"==="+type+"==="+state);
			}

		});
		//触发监听  create  delete   setdata  监听只生效一次  思考：怎么循环生效监听
		zk.create("/test04", "aa".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.setData("/test", "tt".getBytes(), -1);

	}

}
