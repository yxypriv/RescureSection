package random;

import java.util.Random;

public class RandomFeedingThread extends Thread {
	static Random random = new Random();
	static final int interruptGap = 1;
	RandomPool location;

	public RandomFeedingThread(RandomPool location) {
		super();
		this.location = location;
	}

	@Override
	public void run() {
		int interruptCount = interruptGap;
		while (location.running) {
			int result;
			if (--interruptCount > 0) {
				double r = random.nextDouble();
				if (r <= location.currentMean)
					result = 0;
				else
					result = 1;
			} else {
				result = MicrophoneInterruption.nextMircophoneInterruption();
				interruptCount = interruptGap;
			}

			if (result == 1)
				location.addPositive();
			location.addMount();
			location.adjustMean();
			
			boolean added = false;
			while (!added) {
				try {
					added = location.outputQueue.add((byte) result);
				} catch (IllegalStateException e) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}
}
