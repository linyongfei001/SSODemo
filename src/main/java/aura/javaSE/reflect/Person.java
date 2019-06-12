package aura.javaSE.reflect;

public class Person {
	public String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Person() {
		super();
	}
	public void eat(int num,double money){
		System.out.println("人们爱吃包子。。。。。。"+num+"馒头,花了"+money);
	}


}
