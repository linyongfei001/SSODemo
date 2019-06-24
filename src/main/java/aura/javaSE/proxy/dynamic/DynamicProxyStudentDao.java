package aura.javaSE.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * java中带的
 * @author aura-bd
 * 1)实现一个借口    InvocationHandler      invoke
 * 2)
 *
 */
public class DynamicProxyStudentDao implements InvocationHandler{
	//被代理对象作为属性
	Object o;
	public DynamicProxyStudentDao(Object o){
		this.o=o;
	}


	/**
	 * 这个方法最终调用的方法  类似于代理对象中的重写的相同的方法
	 * insert-----delete
	 * 在这个方法中核心业务   被代理对象做的
	 * 其他业务才是代理对象做的
	 * Object proxy：代理对象    基本上不用
	 * Method method：被代理的方法
	 * Object[] args：方法的参数
	 * 返回值代表什么？核心方法的返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if("insert".equals(name)){
			System.out.println("开始插入数据。。。。。。");
			//核心代码   这个方法谁调用的  被代理对象
			Object res=method.invoke(o, args);
			System.out.println("插入数据成功");
			return res;
		}else{
			System.out.println("开始执行了、、、、");
			Object res=method.invoke(o, args);
			System.out.println("执行完成了");
			return res;
		}
	}

}
