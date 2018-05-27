import java.util.stream.IntStream;

public class SimpleThreads {

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
