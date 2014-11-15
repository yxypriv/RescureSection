package random.tests;

import org.lwjgl.Sys;

import random.RandomPool;

public class RandomTest {
	static final long mount = 1000000000;

	public static void main(String[] args) {
		RandomPool rand = new RandomPool();
		rand.start();
		long count = 0;
		long sum = 0;
		long t0 = System.currentTimeMillis();
		while (++count < mount) {
			sum += rand.nextRandom();
			// if (count % (mount / 33) == 0) {
			if (count % 10000 == 0) {
				double processPercent = (((double) count) / mount) * 100;
				System.out.println(String.format("progress: %d / %d\t %f percents\t estimate timeLeft: %d ms", //
						count, mount, processPercent, (int) ((System.currentTimeMillis() - t0)* (1 / processPercent - 1))));
				System.out.println(String.format("bias: %f", ((double) sum) / count));
			}
		}
		rand.stop();
		System.out.println("finished");
	}
}
