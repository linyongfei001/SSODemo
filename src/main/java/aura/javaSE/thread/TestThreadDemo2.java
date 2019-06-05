package aura.javaSE.thread;
/**练习：同步*/
class Ticket implements Runnable{
	int ticketCount = 20;
	synchronized public void getTicket() {
		this.ticketCount -= 1;
		System.out.println(Thread.currentThread().getName()+"卖了1张，还剩下："+this.ticketCount);
	}
	@Override
	public void run() {
		for(int i = 1; i <= 10 ; i++) {
			getTicket();
		}

	}

}
public class TestThreadDemo2 {
	public static void main(String[] args) {
		Ticket tic = new Ticket();
		Thread win1 = new Thread(tic,"窗口一");
		Thread win2 = new Thread(tic,"窗口二");
		win1.start();
		win2.start();

	}

}
