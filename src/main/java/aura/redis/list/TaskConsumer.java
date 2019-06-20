package aura.redis.list;

import java.util.Random;

import redis.clients.jedis.Jedis;

/**
 * 描述: 模拟实现消费者任务
 */
public class TaskConsumer {

	public static void main(String[] args) {

		Jedis jedis = new Jedis("hadoop01", 6379);
		Random random = new Random();

		while (true) {
			try {
				// 从task-queue中取一个任务，同时放入"temp-queue"
				String taskid = jedis.rpoplpush("task-queue", "temp-queue");

				// 模拟处理任务
				Thread.sleep(1000);

				// 模拟有成功又有失败的情况
				int nextInt = random.nextInt(10);
				// 模拟失败的情况， 当是8的时候
				if (nextInt == 8) { 
					// 失败的情况下，需要将任务从"temp-queue"弹回"task-queue"
					jedis.rpoplpush("temp-queue", "task-queue");
					System.out.println("-------任务处理失败： " + taskid);

				//模拟成功的情况
				} else {
					// 成功的情况下，将任务从"temp-queue"清除
					jedis.rpop("temp-queue");
					System.out.println("任务处理成功： " + taskid);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
