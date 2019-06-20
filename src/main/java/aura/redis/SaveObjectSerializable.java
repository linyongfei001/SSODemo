package aura.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import aura.redis.pojo.Student;
import org.junit.Test;


import redis.clients.jedis.Jedis;

public class SaveObjectSerializable {
	
	/**
	 * 将对象缓存到redis的string结构数据中
	 */
	@Test
	public void testObjectCache() throws Exception {
		
		//创建一个jedis客户端对象（redis的客户端连接）
		Jedis jedis = new Jedis("hadoop01", 6379);
		
		Student student = new Student(9527, "唐伯虎", "男", 33, "苏州府");
		
		// 将对象序列化成字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		
		// 用对象序列化流来将student对象序列化，然后把序列化之后的二进制数据写到baos流中
		oos.writeObject(student);
		
		// 将baos流转成byte数组
		byte[] pBytes = baos.toByteArray();
		
		// 将对象序列化之后的byte数组存到redis的string结构数据中
		jedis.set("student_tang".getBytes(), pBytes);
		
		// 根据key从redis中取出对象的byte数据
		byte[] pBytesResponse = jedis.get("student_tang".getBytes());
		
		// 将byte数据反序列出对象
		ByteArrayInputStream bais = new ByteArrayInputStream(pBytesResponse);
		ObjectInputStream ois = new ObjectInputStream(bais);
		
		// 从对象读取流中读取出p对象
		Student pResp = (Student) ois.readObject();
		
		System.out.println(pResp);
	}
}
