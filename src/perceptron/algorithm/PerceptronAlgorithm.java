package perceptron.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import utils.interfaces.FeatureTrainingData;

public class PerceptronAlgorithm {
	public static Random rand = new Random();

	List<FeatureTrainingData> historyData = new LinkedList<FeatureTrainingData>();
	double[] theta;
	int featureLength = -1;
	String positiveLabel = null;
	
	public static double alpha = 0.01;

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
		Double y = null;
		if (data.getLabel().equals(positiveLabel))
			y = 1.0;
		else
			y = -1.0;

		double h = 0.0;
		for (int i = 0; i < featureLength; i++) {
			h += theta[i] * getFeature(data, i);
		}

		for (int i = 0; i < featureLength; i++) {
			theta[i] = theta[i] + //
					alpha * (y - h) * getFeature(data, i);
		}

		historyData.add(data);
	}
	/**
	 * 0.35386221294363257 for original
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
		System.out.println(J + "\t" + J_sign + "\t" + hit / historyData.size());
	}

	public double getFeature(FeatureTrainingData data, int index) {
		if (index == 0)
			return 1;
		else
			return data.getFeatures()[index - 1];
	}
}
