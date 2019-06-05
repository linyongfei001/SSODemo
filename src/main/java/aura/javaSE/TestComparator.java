package aura.javaSE;

import java.util.Arrays;
import java.util.Comparator;

class Employee implements Comparable<Employee>{
	private String name;
	private int age;
	private int score;//考核分数
	public Employee(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return name+","+age+","+score;
	}
	@Override
	public int compareTo(Employee o) {
		//降序:分数
/*		if(this.score < o.score ) {
			return 1;
		}else if(this.score > o.score) {
			return -1;
		}else {
			return 0;
		}*/
		return o.score - this.score;
	}
}

public class TestComparator {

	public static void main(String[] args) {
		Employee emp1 = new Employee("guojing", 22, 100);
		Employee emp2 = new Employee("guojing", 20, 80);
		Employee emp3 = new Employee("yangkang", 21, 90);
		Employee emp4 = new Employee("huangrong", 19, 70);
		Employee [] emps = {emp1,emp2,emp3,emp4};
//		Arrays.sort(emps);
		//名字和年龄排序
		Arrays.sort(emps,new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				//相同的
		/*		if(o1.getName().compareTo(o2.getName()) == 0) {
					//比较年龄
					if(o1.getAge() > o2.getAge()) {
						return 1;
					}else if(o1.getAge() < o2.getAge()) {
						return -1;
					}else {
						return 0;
					}
				}else {//不同的
					if(o1.getName().compareTo(o2.getName()) > 0) {
						return 1;
					}else  {
						return -1;
					}
				}*/
				//简化
				if(o1.getName().compareTo(o2.getName()) == 0) {
					//年龄
					return o1.getAge() - o2.getAge();
				}else {
					return o1.getName().compareTo(o2.getName());
				}

			}

		});
		for(Employee e :emps) {
			System.out.println(e);
		}
	}

}
