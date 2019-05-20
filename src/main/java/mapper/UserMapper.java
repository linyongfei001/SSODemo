package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import pojo.UserInfo;

public interface UserMapper {
	
	@Select("select * from userinfo where sname = #{param1} and password = #{param2}")
	UserInfo login(String sname, String password);
	
	
	//注册
	@Insert("insert into userinfo(sname,password) values(#{param1},#{param2})")
	int insert(String sname, String password);
	
}
