package random.tests;

import java.util.Random;

import random.IRandom;

public class Random1 implements IRandom {
	Random random = new Random();
	double currentMean = 0.5;

	long mountCount = 10000;
	long positiveCount = 5000;

	@Override
	public int nextRandom() {
		double r = random.nextDouble();
		int result;
		if (r <= currentMean)
			result = 0;
		else
			result = 1;
		adjustMean(result);
		return result;
	}

	public void adjustMean(int currentResult) {
		positiveCount += currentResult;
		mountCount++;
		currentMean = (double) positiveCount / mountCount;
	}

}
