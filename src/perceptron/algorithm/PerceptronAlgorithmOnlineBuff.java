package perceptron.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import utils.interfaces.FeatureTrainingData;

public class PerceptronAlgorithmOnlineBuff {
	public static Random rand = new Random();

	List<FeatureTrainingData> historyData = new LinkedList<FeatureTrainingData>();
	double[] theta;
	int featureLength = -1;
	String positiveLabel = null;

	int positiveCount = 0;
	int negativeCount = 0;
	List<FeatureTrainingData> positiveBuff = new ArrayList<FeatureTrainingData>();
	List<FeatureTrainingData> negativeBuff = new ArrayList<FeatureTrainingData>();
	int buffSize = 100;

	int pathHit = 0;

	public double alpha = 0.02 / buffSize;

	/**
	 * generate new model
	 */
	public void train(FeatureTrainingData data) {
		if (featureLength == -1)
			featureLength = data.getFeatures().length + 1;
		if (null == positiveLabel)
			positiveLabel = data.getLabel();
		if (null == theta) {
			theta = new double[featureLength];
			for (int i = 0; i < featureLength; i++) {
				theta[i] = rand.nextDouble();
			}
		}
		if (data.getLabel().equals(positiveLabel)) {
			positiveCount++;
		} else {
			negativeCount++;
		}
		if (positiveCount + negativeCount >= buffSize) {
			double ratio = ((double) positiveCount) / (positiveCount + negativeCount);
			// double randVote = rand.nextDouble();
			double randVote = ((double) positiveBuff.size()) / (positiveBuff.size() + negativeBuff.size());
			if ((randVote < ratio && negativeBuff.size() > 0) || positiveBuff.size() == 0) {
				negativeBuff.remove(rand.nextInt(negativeBuff.size()) % negativeBuff.size());
			} else {
				positiveBuff.remove(rand.nextInt(positiveBuff.size()) % positiveBuff.size());
			}
		}
		if (data.getLabel().equals(positiveLabel)) {
			positiveBuff.add(data);
		} else {
			negativeBuff.add(data);
		}
		double dataY = 0.0;
		if (data.getLabel().equals(positiveLabel))
			dataY = 1.0;
		else
			dataY = -1.0;

		double dataH = 0.0;
		for (int i = 0; i < featureLength; i++) {
			dataH += theta[i] * getFeature(data, i);
		}
		if (dataY * dataH > 0)
			pathHit++;

		List<FeatureTrainingData> buffList = new ArrayList<FeatureTrainingData>();
		buffList.addAll(positiveBuff);
		buffList.addAll(negativeBuff);
		int loop = 10;
		while (--loop >= 0) {
//			double[] deltaTheta = new double[theta.length];
			for (int k = 0; k < buffList.size(); k++) {
				FeatureTrainingData buffData = buffList.get(k);
				// FeatureTrainingData buffData = buffList.get(k / 2 + (k % 2) *
				// (buffList.size() / 2));
				Double y = null;
				if (buffData.getLabel().equals(positiveLabel))
					y = 1.0;
				else
					y = -1.0;

				double h = 0.0;
				for (int i = 0; i < featureLength; i++) {
					h += theta[i] * getFeature(buffData, i);
				}

				for (int i = 0; i < featureLength; i++) {
					theta[i] = theta[i] + //
							alpha * (y - h) * getFeature(buffData, i);
				}
			}
//			for (int i = 0; i < theta.length; i++) {
//				theta[i] += deltaTheta[i];
//			}
		}
		historyData.add(data);
	}

	/**
	 * 0.7317327766179541 0.9592901878914405 add loop 100
	 * 0.6670146137787056 0.8308977035490606 add loop 10
	 * 0.6461377870563675 0.6889352818371608 add buff. 
	 * 0.6367432150313153 0.6951983298538622 for original
	 */
	public void errorRate() {
		double J = 0.0;
		double J_sign = 0.0;
		double hit = 0.0;
		for (FeatureTrainingData data : historyData) {
			Double y = null;
			if (data.getLabel().equals(positiveLabel))
				y = 1.0;
			else
				y = -1.0;

			double h = 0.0;
			for (int i = 0; i < featureLength; i++) {
				h += theta[i] * getFeature(data, i);
			}
			J += Math.pow(h - y, 2);
			if (h * y < 0) {
				J_sign += Math.pow(h - y, 2);
			} else {
				hit++;
			}
		}
		System.out.println(J + "\t" + J_sign + "\t" + hit / historyData.size() + "\t" + ((double) pathHit) / historyData.size());
	}

	public void displayStatus() {
		System.out.println(positiveCount + "\t" + negativeCount + "\t" + positiveBuff.size() + "\t" + negativeBuff.size());
	}

	public double getFeature(FeatureTrainingData data, int index) {
		if (index == 0)
			return 1;
		else
			return data.getFeatures()[index - 1];
	}

	public int getHistorySize() {
		return historyData.size();
	}
}
