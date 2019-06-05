package aura.javaSE.thread;
/**创建子线程*/

class MyThread extends Thread{
	MyThread(String name){
		super(name);
	}
	//子线程的任务
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}

}
class ThreadDemo implements Runnable{
	@Override
	public void run() {
		for(int i = 1; i <= 3; i++) {
			System.out.println(Thread.currentThread().getName()+"吃饭");
		}
	}

}
public class TestThrad1 {
	public static void main(String[] args) {
		//是不是线程对象：不是
		ThreadDemo demo = new ThreadDemo();
		Thread zhangsan = new Thread(demo,"张三");
		Thread lisi = new Thread(demo,"李四");
//		zhangsan.setPriority(10);
//		lisi.setPriority(1);

		//最低，普通，最高
		// 1   5    10
//		zhangsan.setPriority(Thread.MAX_PRIORITY);
//		zhangsan.setPriority(Thread.MIN_PRIORITY);
		zhangsan.setPriority(Thread.NORM_PRIORITY);

		lisi.start();
		zhangsan.start();
	/*	try {
			Thread.sleep(1000);//等 1000毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//判断子线程 是否仍然运行
	/*	if(zhangsan.isAlive() || lisi.isAlive()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	/*	try {
			zhangsan.join();
			lisi.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("主线程结束");

	/*	//创建一个子线程
		MyThread t1 = new MyThread("t1");//新建
		//启动子线程,只能启动一次
		t1.start();//就绪
*/


	}

}






