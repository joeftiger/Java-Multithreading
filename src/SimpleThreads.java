import java.util.stream.IntStream;

/**
 * Creating a thread in java is very simple.
 * Each thread has the methods {@link Thread#run()}, which needs to get overriden to perform certain operations.
 * Such a thread may be started calling {@link Thread#start()}.
 * After a thread finished its task, it dies and cannot be restarted.
 *
 * Notice, that threads don't get executed in order.
 */
public class SimpleThreads {

	/** Creates 10 new threads, each counting to 10 */
	public static void main(String[] args) {
		Thread thread;
		for (int i=0; i<10; i++) {
			thread = new Thread(
					() -> IntStream.range(0, 10).forEach(
							j -> System.out.println(
									Thread.currentThread().getName()
									+ ", run: " + j)
					));
			thread.start();
		}
	}

}
