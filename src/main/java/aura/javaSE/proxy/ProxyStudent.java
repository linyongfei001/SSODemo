package aura.javaSE.proxy;

public class ProxyStudent implements Person{
	Student stu;
	//需要对被代理对象进行初始化
	public ProxyStudent(){
		this.stu=new Student();
	}

	@Override
	public void huxi() {
		//写代码
		System.out.println("开始写代码，好心累");
		//呼吸
		stu.huxi();

		//写代码
		System.out.println("还要写到代码，心好累");
	}

}
