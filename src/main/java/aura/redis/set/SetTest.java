package aura.redis.set;

import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * 描述： Test的测试
 */
public class SetTest {
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("hadoop01", 6379);
		
		jedis.sadd("bigdata:hadoop", "hdfs","maprduce","yarn","zookeeper");
		jedis.sadd("bigdata:spark", "flume","kafka","redis","zookeeper");
		
		// 判断一个成员是否属于指定的集合
		Boolean isornot = jedis.sismember("bigdata:hadoop", "zookeeper");
		System.out.println(isornot);
		System.out.println("----------------华丽的分割线--------------------");
		
		// 求两个集合的差集
		Set<String> diff = jedis.sdiff("bigdata:hadoop","bigdata:spark");
		for(String mb:diff){
			System.out.println(mb);
		}
		System.out.println("----------------华丽的分割线--------------------");
		
		// 求两个集合的并集
		Set<String> union = jedis.sunion("bigdata:hadoop","bigdata:spark");
		for(String mb:union){
			System.out.println(mb);
		}
		System.out.println("----------------华丽的分割线--------------------");
		
		// 求两个集合的交集
		Set<String> intersect = jedis.sinter("bigdata:hadoop","bigdata:spark");
		//打印结果
		for(String mb:intersect){
			System.out.println(mb);
		}
	}
}
