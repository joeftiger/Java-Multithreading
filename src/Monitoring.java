public class Monitoring implements Runnable {

	private final String text;
	private final Object monitor;

	public Monitoring(String text, Object monitor) {
		this.text = text;
		this.monitor = monitor;
	}

	public void run() {
		synchronized(monitor) {
			while (true) {
				System.out.println(text);
				monitor.notify();
				try {
					monitor.wait();
				} catch (InterruptedException ignored) {
				}
			}
		}
	}

	public static void main(String[] args) {
		Object monitor = new Object();
		new Thread(new Monitoring("Ping", monitor)).start();
		new Thread(new Monitoring("Pong", monitor)).start();
	}
}
