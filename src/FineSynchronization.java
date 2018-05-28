/**
 * Often times it is unneeded to synchronize whole methods and make other Threads wait.
 * Therefore, only certain code blocks may be surrounded by the {@code synchronized} key word.
 */
public class FineSynchronization {

	private long totalSize;
	private int amount;
	private double average;

	/** a method to add Integers */
	private void add(int integer) {
		System.out.println(Thread.currentThread().getName());

		/* This part needs to be synchronized */
		synchronized (this) {
			totalSize += integer;
			amount++;
			average = totalSize / amount;
			System.out.println(amount + " integers, " + totalSize + " total. Average: " + average
								+ " (Last file: " + integer + ")");
		}
	}

	/** Adds random integers 100 times */
	public static void main(String[] args) {
		FineSynchronization s = new FineSynchronization();
		for (int i=0; i<100; i++) new Thread(() -> s.add((int) (Math.random() * 1000))).start();
	}
}
