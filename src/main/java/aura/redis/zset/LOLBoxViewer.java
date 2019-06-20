package aura.redis.zset;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * 描述： 英雄出场率排行榜查看模块
 */
public class LOLBoxViewer {

	public static void main(String[] args) throws Exception {

		Jedis jedis = new Jedis("hadoop01", 6379);
		int i = 1;
		
		while (true) {
			// 每隔3秒查看一次榜单
			Thread.sleep(3000);
			System.out.println("第" + i + "次查看榜单-----------");

			// 从redis中查询榜单的前N名
			Set<Tuple> topHeros = jedis.zrevrangeWithScores("hero:ccl:phb", 0, 4);

			for (Tuple t : topHeros) {
				System.out.println(t.getElement() + "   " + t.getScore());
			}

			i++;
			System.out.println("");
			System.out.println("");
			System.out.println("");
		}
	}
}
