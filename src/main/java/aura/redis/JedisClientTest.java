package aura.redis;

import redis.clients.jedis.Jedis;

public class JedisClientTest {
    public static void main(String[] args) {
    //创建一个jedis客户端对象（redis的客户端连接）
        Jedis client = new Jedis("hadoop01", 6379);
        //测试服务器是否连通
        String resp = client.ping();
        System.out.println(resp);
    }
}
