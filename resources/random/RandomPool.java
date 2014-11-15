package random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RandomPool implements IRandom {
	public static final int threadPoolSize = 4;
	ArrayBlockingQueue<Byte> outputQueue = new ArrayBlockingQueue<Byte>(1000000);
	List<RandomFeedingThread> threads = new ArrayList<RandomFeedingThread>();
	boolean running = false;

	double currentMean = 0.5;
	long mountCount = 10000;
	long positiveCount = 5000;

	public ArrayBlockingQueue<Byte> getOutputQueue() {
		return outputQueue;
	}

	public void setOutputQueue(ArrayBlockingQueue<Byte> outputQueue) {
		this.outputQueue = outputQueue;
	}

	public List<RandomFeedingThread> getThreads() {
		return threads;
	}

	public void setThreads(List<RandomFeedingThread> threads) {
		this.threads = threads;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void start() {
		running = true;
		for (int i = 0; i < threadPoolSize; i++) {
			threads.add(new RandomFeedingThread(this));
		}
		for (RandomFeedingThread t : threads) {
			t.start();
		}
	}

	@Override
	public int nextRandom() {
		while (outputQueue.isEmpty()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return outputQueue.poll().intValue();
	}

	public void stop() {
		running = false;
	}

	public synchronized void addPositive() {
		positiveCount++;
	}

	public synchronized void addMount() {
		mountCount++;
	}

	public synchronized void adjustMean() {
		currentMean = (double) positiveCount / mountCount;
	}

	public double getCurrentMean() {
		return currentMean;
	}
}
