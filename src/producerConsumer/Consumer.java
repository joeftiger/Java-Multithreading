package producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private final BlockingQueue queue;

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				consume(queue.take());
			} catch (InterruptedException ignored) {}
		}
	}

	private void consume(Object object) {
		System.out.println(object);
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
		Producer p = new Producer(queue);
		Consumer c = new Consumer(queue);
		new Thread(p).start();
		new Thread(c).start();
	}
}
