package aura.javaSE.proxy;
/**
 * 找代理-----找本人
 * 		核心的部分仍然是本人做的
 * @author aura-bd
 *
 */
public class TestProxy {
	public static void main(String[] args) {
		/*Student stu=new Student();
		stu.huxi();*/
		ProxyStudent ps=new ProxyStudent();
		ps.huxi();

		ProxyTeacher pt=new ProxyTeacher();
		pt.huxi();
	}

}
