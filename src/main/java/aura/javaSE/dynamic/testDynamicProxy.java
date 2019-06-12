package aura.javaSE.dynamic;

import java.lang.reflect.Proxy;

/**
 * 测试一下动态代理
 * @author aura-bd
 *能调用到什么看类型
 *方法的执行结果看对象
 */
public class testDynamicProxy {
	public static void main(String[] args) {
		//生成代理对象
		/**参数1：类加载器    被代理对象的类加载器
		 * 参数2：被代理对象的所实现的所有借口
		 * 参数3：代理对象  InvocationHandler类型  借口    传入实现类对象   代理对象
		 */
		//代理BaseStudentDao对象
		BaseDao newProxyInstance = (BaseDao)Proxy.newProxyInstance(BaseStudentDao.class.getClassLoader(),
				BaseStudentDao.class.getInterfaces(),
				new DynamicProxyStudentDao(new BaseStudentDao()));
		newProxyInstance.insert(new Student());

		System.out.println("0000000000000000000000000000");
		//代理String
		CharSequence newProxyInstance2 = (CharSequence)Proxy.newProxyInstance(String.class.getClassLoader(),
				String.class.getInterfaces(), new DynamicProxyStudentDao(new String("test")));
		int len=newProxyInstance2.length();
		System.out.println(len);
		/*BaseDao s=(BaseDao)new DynamicProxyStudentDao(new BaseStudentDao());
		s.in*/
	}

}
