package test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mapper.UserMapper;
import pojo.UserInfo;
import service.UserService;

public class Test {

	@org.junit.Test
	public void test() throws IOException{
	/*	Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		SqlSession ss = ssf.openSession(true);
		UserMapper mapper = ss.getMapper(UserMapper.class);
		UserInfo info = mapper.queryOne("zs", "123");
		System.out.println(info);
		ss.close();*/
		
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserService service = ac.getBean("userservice",UserService.class);
		System.out.println(service.login("ls", "123"));
		
				
		
	}

}
