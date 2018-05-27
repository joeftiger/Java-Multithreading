package producerConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private final BlockingQueue queue;

	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			Object data = produce();

			while (data != null) {
				try {
					queue.put(data);
					data = null;
				} catch (InterruptedException ignored) {}
			}
		}
	}

	private Object produce() {
		return queue.size();
	}
}
