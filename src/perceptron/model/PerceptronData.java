package perceptron.model;

import utils.interfaces.FeatureTrainingData;

public class PerceptronData implements FeatureTrainingData {

	double[] features;
	String label;

	public PerceptronData(int[] features, String label) {
		super();
		double[] dFeatures = new double[features.length];
		for(int i=0; i<features.length; i++) {
			dFeatures[i] = features[i];
		}
		this.features = dFeatures;
		this.label = label;
	}
	
	public PerceptronData(double[] features, String label) {
		super();
		this.features = features;
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public double[] getFeatures() {
		return features;
	}

	public void setFeatures(double[] features) {
		this.features = features;
	}
}
