package aura.javaSE.reflect;

import aura.javaSE.reflect.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testPersonReflect {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //获取class对象
        Class p= Person.class;
        //获取属性
        Field[] fields = p.getDeclaredFields();
        //获取属性  只能返回  共有的
        Field[] fields2 = p.getFields();
        for(Field f:fields){
            System.out.println(f);
        }
        System.out.println("--------------");
        for(Field f:fields2){
            System.out.println(f);
        }
        //参数表示的是属性的名字
        Field field = p.getField("name");
        System.out.println(field.getName());


        //方法
        /**
         * 参数1：方法名
         * 参数2：方法需要的参数
         * 获取的方法是共有的方法
         */
        Method method = p.getMethod("eat", int.class,double.class);
        System.out.println(method.getName());

        //构造方法
        Constructor[] constructors = p.getConstructors();
        for(Constructor c:constructors){
            System.out.println(c);
        }

        //参数指的是构造方法的参数类型
        Constructor constructor = p.getConstructor(String.class,int.class);
        System.out.println(constructor);

        //通过反射创建对象  获取构造方法  参数：创建对象的时候给构造方法穿的参数
        //new Person(initargs)
        Person person = (Person)constructor.newInstance("sanpang",45);
        System.out.println(person.getAge());
        System.out.println(person.getName());

        //设置属性的值
        //person.setAge(56);
        //System.out.println(person.getAge());
        //p.getField("name")  获取属性   set(obj, value);给属性复制
        //set的两个参数  参数1：对象  参数2：属性的值
        p.getField("name").set(person, "xiaohuahua");
        System.out.println(person.getName());

		/*p.getField("age").set(person, 109);
		System.out.println(person.getAge());*/
        Field declaredField = p.getDeclaredField("age");
        //将私有属性的赋值权限打开
        declaredField.setAccessible(true);
        declaredField.set(person, 109);
        System.out.println(person.getAge());

        //方法调用
        Method m01=p.getMethod("eat", int.class,double.class);
        //person.eat(4, 5.6);
        //参数1  对象  参数2：方法的参数
        m01.invoke(person, 18,0.0);

    }

}
