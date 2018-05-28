import java.util.concurrent.atomic.AtomicInteger;

/**
 * An easy way to increment a counter in multiple threads is to use {@link AtomicInteger}.
 * This class makes {@code int}-operations atomic, meaning serving one thread a a time.
 * Thus the counting will be guaranteed to deliver {@code 10'000}.
 */
public class Atomic {

	private AtomicInteger counter = new AtomicInteger();

	/** Starts 10 threads, each increasing {@code counter} 1000 times */
	private void run() {
		Thread[] threads = new Thread[10];

		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(
					() -> {
						for (int j=0; j<1000; j++) counter.incrementAndGet();
					});
			threads[i].start();
		}

		for (Thread t : threads) {
			while (t.isAlive()) {
				try { t.join(); }
				catch (InterruptedException ignored) {}
			}
		}
		/* Will certainly be 10000 */
		System.out.println(counter);
	}

	/** Starts the procedure */
	public static void main(String[] args) {
		new Atomic().run();
	}
}
