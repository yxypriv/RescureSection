package random;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RandomGenerateMain {
	// static final long mount = 1000000000;

	public static void main(String[] args) {
		String path = args[0];
		long mount = Long.parseLong(args[1]);

		File f = new File(path);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		RandomPool rand = new RandomPool();
		rand.start();
		long count = 0;
		long sum = 0;
		long t0 = System.currentTimeMillis();
		while (++count < mount - 1) {
			int next = rand.nextRandom();
			writer.print(String.format("%d,", next));
			sum += next;
			// if (count % (mount / 33) == 0) {
			if (count % 10000 == 0) {
				double processPercent = (((double) count) / mount);
				System.out.println(String.format("progress: %d / %d\t %f percents\t estimate timeLeft: %d ms", //
						count, mount, processPercent * 100, (int) ((System.currentTimeMillis() - t0) * (1 / processPercent - 1))));
				System.out.println(String.format("bias: %f", ((double) sum) / count));
				writer.flush();
			}
		}
		writer.print(String.format("%d", rand.nextRandom()));
		writer.flush();
		rand.stop();
		System.out.println("finished, totally cost : " + (System.currentTimeMillis() - t0));
		writer.close();

	}
}
