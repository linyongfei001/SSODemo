package aura.javaSE.thread;

//包子铺
class Qingfeng{
	private int count;//包子数量
	private boolean tag = false;//true ->有包子false->没包子
	//生产
	synchronized public void put(int count) {
		if(tag == true) {//有包子了
			try {
				wait();//休息
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//没有包子 false
		this.count = count;//产包子
		System.out.println("生产了："+this.count);
		tag = true;
		notify();//唤醒 销售人员
	}
	//销售
	synchronized public void get() {
		if(tag == false) {//没包子
			try {
				wait();//休息
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 有包子true
		System.out.println("卖了："+this.count);
		tag = false;//没了
		notify();//唤醒厨师
	}
}
//生产
class Producer implements Runnable{
	Qingfeng qingfeng;
	Producer(Qingfeng qingfeng){
		this.qingfeng = qingfeng;
	}
	@Override
	public void run() {
		//生产put
		for(int i = 1; i <= 5; i++) {
			qingfeng.put(i);
		}
	}
}
//销售
class Consumer implements Runnable{
	Qingfeng qingfeng;
	Consumer(Qingfeng qingfeng){
		this.qingfeng = qingfeng;
	}
	public void run() {
		for(int i = 1; i <= 5; i++) {
			qingfeng.get();
		}
	}
}
public class TestWaitConsumer {
	public static void main(String[] args) {
		Qingfeng qingfeng = new Qingfeng();//包子铺
		Producer pro = new Producer(qingfeng);
		Consumer con = new Consumer(qingfeng);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		t1.start();
		t2.start();
	}

}
