package aura.javaSE;

interface Run{
    void run();
}

class Dog implements Run{

    @Override
    public void run() {
        System.out.println("小狗 快速的跑");
    }
}

class Cat implements Run{

    @Override
    public void run() {
        System.out.println("小猫飞快的跑");
    }
}

class CheckRun{
    public void check(Run r) {//多态Run r = new Cat();  new Dog();
        r.run();
    }
/*	public void check(Dog dog) {
		dog.run();
	}
	public void check(Cat1 cat) {
		cat.run();
	}*/
}

public class TestLambda {
    public static void main(String[] args) {
        CheckRun c = new CheckRun();
        c.check(new Dog());
    }
}
