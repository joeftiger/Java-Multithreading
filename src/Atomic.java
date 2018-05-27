import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

	private AtomicInteger counter = new AtomicInteger();

	private void run() {
		Thread[] threads = new Thread[10];

		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(
					() -> {
						for (int j=0; j<100; j++) counter.incrementAndGet();
					}, "Thread " + i);
			threads[i].start();
		}

		for (Thread t : threads) {
			while (t.isAlive()) {
				try { t.join(); }
				catch (InterruptedException ignored) {}
			}
		}
		System.out.println(counter);
	}

	public static void main(String[] args) {
		new Atomic().run();
	}
}
