public class Synchronization {

	private long totalSize;
	private int amount;
	private double average;

	private synchronized void add(int integer) {
		System.out.println(Thread.currentThread().getName());
		totalSize += integer;
		amount++;
		average = totalSize / amount;
		System.out.println(amount + " integers, " + totalSize + " total. Average: " + average
							+ " (Last file: " + integer + ")");
	}

	public static void main(String[] args) {
		Synchronization s = new Synchronization();
		for (int i=0; i<1000; i++) new Thread(() -> s.add((int) (Math.random() * 1000))).start();
	}
}
