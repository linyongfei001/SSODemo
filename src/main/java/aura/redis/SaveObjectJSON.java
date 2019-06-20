package aura.redis;

import aura.redis.pojo.Student;
import org.junit.Test;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class SaveObjectJSON {
	
	/**
	 * 将对象转成json字符串缓存到redis的string结构数据中
	 */
	@Test
	public void testObjectToJsonCache() {
		
		//创建一个jedis客户端对象（redis的客户端连接）
		Jedis jedis = new Jedis("hadoop01", 6379);
		
		Student student = new Student(9527, "唐伯虎", "男", 33, "苏州府");
		
		// 利用gson将对象转成json串
		Gson gson = new Gson();
		String pJson = gson.toJson(student);
		
		// 将json串存入redis
		jedis.set("student_tang", pJson);
		
		// 从redis中取出对象的json串
		String pJsonResp = jedis.get("student_tang");
		
		// 将返回的json解析成对象
		Student student_tang = gson.fromJson(pJsonResp, Student.class);
		
		// 显示对象的属性
		System.out.println(student_tang);
	}
	
}
