package aura.javaSE.proxy;

public class ProxyTeacher implements Person{

	Teacher tea;
	public ProxyTeacher(){
		this.tea=new Teacher();
	}
	@Override
	public void huxi() {
		System.out.println("开始上课了，找个代理吧");
		tea.huxi();
	}


}
