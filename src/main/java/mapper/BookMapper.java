package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pojo.Book;

public interface BookMapper {

	@Select("select * from book")
	List<Book> selBooks();
	
	//删除
	@Delete("delete from book where bid=#{param1}")
	int delete(int sid);
	
	//修改
	@Update("update book set bname=#{param1},author=#{param2},price=#{param3} where bid=#{param4}")
	int update(Book book);
	
	//增加
	@Insert("insert into book(bname,author,price) values(#{param1},#{param2},#{param3})")
	int insert(Book book);
	
	@Select("select * from book where bid = #{param1}")
	Book queryOne(int bid);

}
