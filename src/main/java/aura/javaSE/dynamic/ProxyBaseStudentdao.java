package aura.javaSE.dynamic;

import java.util.List;

public class ProxyBaseStudentdao implements BaseDao{
	BaseStudentDao bsd;
	public ProxyBaseStudentdao(){
		this.bsd=new BaseStudentDao();
	}

	@Override
	public int insert(Person p) {
		System.out.println("开始插入啦");
		int res=bsd.insert(p);
		System.out.println("成功啦");
		return res;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Person p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Person> select() {
		// TODO Auto-generated method stub
		return null;
	}

}
