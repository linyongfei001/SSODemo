package aura.javaSE.lock;

import java.util.concurrent.locks.ReentrantLock;

class Bank implements Runnable{
	int money = 0;
	//锁 公平
	ReentrantLock lock = new ReentrantLock(true);
	public  void setMoney() {
		try {
			lock.lock();//加锁
			money += 100;//存100
			System.out.println(Thread.currentThread().getName()+"存了100，余额："+money);
			return;
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();//释放锁
		}
	}
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			//同步锁
//			synchronized(this) {
			setMoney();
//			}
		}
	}
}

public class TestBank {
	public static void main(String[] args) {
		Bank bank = new Bank();
		Thread zhangsan = new Thread(bank,"张三");
		Thread lisi = new Thread(bank,"李四");
		zhangsan.start();
		lisi.start();
	/*	MyBank zhangsan = new MyBank("张三");
		MyBank lisi = new MyBank("李四");
		zhangsan.start();
		lisi.start();*/
	}

}
class MyBank extends Thread{
	MyBank(String name){
		super(name);
	}
	int money = 0;
	public  void setMoney() {
		money += 100;//存100
		System.out.println(Thread.currentThread().getName()+"存了100，余额："+money);
	}
	public void run() {
		for(int i = 1; i <= 3; i++) {
			setMoney();
		}
	}
}