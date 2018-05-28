import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Creating new threads is an expensive operation.
 * If a program needs to create many threads for single purposes, it is better to reuse certain threads instead
 * of discarding them. For this purpose, we have a so called <i>Thread-Pool</i>, which tried his best
 * to reuse old threads. However, if all cached threads are in use, new ones may be created.
 */
public class ThreadReuse {

	/** Creates a new cached Thread-Pool */
	private Executor executor = Executors.newCachedThreadPool();

	/** Counts to 5 with spaces of 500 milliseconds */
	private void request() {
		executor.execute(() -> {
			for (int i=0; i<5; i++) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) { }
			}});
	}


	/** Start process */
	public static void main(String[] args) {
		ThreadReuse reuse = new ThreadReuse();
		reuse.request();
		reuse.request();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ignored) { }
		System.out.println("\nReusing:");
		reuse.request();
		reuse.request();
	}

}
