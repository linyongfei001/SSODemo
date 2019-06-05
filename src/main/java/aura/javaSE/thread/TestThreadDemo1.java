package aura.javaSE.thread;
/**练习：多线程*/
class ThreadDemo5 implements Runnable{

	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			try {
				Thread.sleep(1000);//一秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());//名
		}
	}

}
public class TestThreadDemo1 {

	public static void main(String[] args) {
		ThreadDemo5 demo5 = new ThreadDemo5();
		Thread t1 = new Thread(demo5,"t1");
		Thread t2 = new Thread(demo5,"t2");
		t1.start();
		t2.start();
	}

}
