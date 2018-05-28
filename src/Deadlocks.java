/**
 * Small error, big problem.
 * Both threads synchronize to two different objects, but while thread 1 waits for Object 2,
 * thread 2 waits for object 1, both objects being locked by the other thread.
 *
 * Java does not cover for such an accident.
 */
public class Deadlocks {

	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();

		Thread t1 = new Thread(() -> {
			synchronized(o1) {      // Object 1
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignored) { }

				synchronized(o2) {  // Object 2
					System.out.println(1);
				}
			}
		});

		Thread t2 = new Thread(() -> {
			synchronized(o2) {      // Object 2
				try {
					Thread.sleep(100);
				} catch (InterruptedException ignored) { }

				synchronized(o1) {  // Object 1
					System.out.println(2);
				}
			}
		});

		t1.start();
		t2.start();
	}
}
