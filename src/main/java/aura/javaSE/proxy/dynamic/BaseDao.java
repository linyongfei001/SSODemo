package aura.javaSE.proxy.dynamic;

import java.util.List;

/*
 * 数据库的核心操作
 */
public interface BaseDao {
	int insert(Person p);
	int delete(int id);
	int update(Person p);
	List<Person> select();
}
