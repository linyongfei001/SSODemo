package aura.javaSE.proxy.dynamic;

import java.util.List;

public class BaseStudentDao implements BaseDao{

	@Override
	public int insert(Person p) {
		// TODO Auto-generated method stub
		return p==null?0:1;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return id>0?1:0;
	}

	@Override
	public int update(Person p) {
		// TODO Auto-generated method stub
		return p==null?0:1;
	}

	@Override
	public List<Person> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
