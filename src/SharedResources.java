/**
 * Threads can share the same resources.
 * However, synchronization is not guaranteed without special actions.
 *
 * In this example, we use {@code counter ++} which is understood as following:
 *      - {@code int newValue = counter + 1;}
 *      - {@code counter = newValue;}
 *
 * One can see, that without synchronization, multiple threads may overlap in this exact procedure,
 * thus causing a different output than anticipated.
 */
public class SharedResources {

	private int counter = 0;

	/** Starts 10 threads, each increasing {@code counter} 1000 times */
	private void run() {
		Thread[] threads = new Thread[10];

		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(
					() -> {
						for (int j=0; j<1000; j++) counter++;
					});
			threads[i].start();
		}

		/* Wait for each thread to finish */
		for (Thread t : threads) {
			while (t.isAlive()) {
				try { t.join(); }
				catch (InterruptedException ignored) {}
			}
		}
		/* Should theoretically be 10000 */
		System.out.println(counter);
	}

	/** Starts the procedure */
	public static void main(String[] args) {
		new SharedResources().run();
	}
}
