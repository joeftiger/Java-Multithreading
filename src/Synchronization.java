/**
 * To keep multiple objects synchronized one may use the special keyword {@code synchronized},
 * which "locks" the current operation for other threads, thus having the only authority to perform
 * the operation. Even with multiple {@code synchronized} methods, there exists only one lock.
 * Therefore, other threads are forced to wait until the lock is released.
 *
 * To keep consistency in bigger programs, the user must be thoroughly synchronize every value <b>as needed</b> !
 * Too much synchronization however may slow the program or even create <i>Deadlocks</i>.
 */
public class Synchronization {

	private long totalSize;
	private int amount;
	private double average;

	/** a synchronized method to add Integers */
	private synchronized void add(int integer) {
		System.out.println(Thread.currentThread().getName());
		totalSize += integer;
		amount++;
		average = totalSize / amount;
		System.out.println(amount + " integers, " + totalSize + " total. Average: " + average
							+ " (Last Number: " + integer + ")");
	}

	/** Starts adding random numbers */
	public static void main(String[] args) {
		Synchronization s = new Synchronization();
		for (int i=0; i<100; i++) new Thread(() -> s.add((int) (Math.random() * 1000))).start();
	}
}
