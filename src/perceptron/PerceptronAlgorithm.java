package perceptron;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import utils.interfaces.FeatureTrainingData;
import utils.interfaces.HasFeature;

public class PerceptronAlgorithm {
	public static Random rand = new Random();

	double[] theta;
	String[] labels;

	public static double alpha = 0.1;

	/**
	 * generate new model
	 * 
	 * @param training
	 */
	public void train(List<FeatureTrainingData> training) {
		Set<String> labelSet = new HashSet<String>();
		int featureLength = -1;
		for (FeatureTrainingData f : training) {
			labelSet.add(f.getLabel());
			if (featureLength == -1)
				featureLength = f.getFeatures().length;
		}
		if (labelSet.size() > 2) {
			System.err.println("Too many labels: ");
			for (String label : labelSet)
				System.err.print(label + "\t");
			System.err.println();
			System.err.println("Only under determine whether its the 1st one.");
		}

		labels = new String[labelSet.size()];
		theta = new double[featureLength];
		int labelIndex = 0;
		for (String label : labelSet) {
			labels[labelIndex++] = label;
		}
		for (int i = 0; i < featureLength; i++) {
			theta[i] = rand.nextDouble();
		}

		int count = 0;
		for (FeatureTrainingData data : training) {
			Double y = null;
			if (data.getLabel().equals(labels[0]))
				y = 1.0;
			else
				y = -1.0;

			double h = 0.0;
			for (int i = 0; i < featureLength; i++) {
				h += theta[i] * data.getFeatures()[i];
			}

			for (int i = 0; i < featureLength; i++) {
				theta[i] = theta[i] + //
						alpha * (y - h) * data.getFeatures()[i];
			}
		}
		errorRate(training, featureLength);
	}

	/**
	 * training from existing model
	 * 
	 * @param training
	 */
	public void continueTraining(List<FeatureTrainingData> training) {

	}

	public List<String> test(List<HasFeature> testing) {

		return null;
	}

	private void errorRate(List<FeatureTrainingData> training, int featureLength) {
		double J = 0.0;
		double J_sign = 0.0;
		for (FeatureTrainingData data : training) {
			Double y = null;
			if (data.getLabel().equals(labels[0]))
				y = 1.0;
			else
				y = -1.0;

			double h = 0.0;
			for (int i = 0; i < featureLength; i++) {
				h += theta[i] * data.getFeatures()[i];
			}
			J += Math.pow(h - y, 2);
			if (h * y < 0) {
				J_sign += Math.pow(h - y, 2);
			}
		}
		System.out.println(J + "\t" + J_sign);
	}
}
