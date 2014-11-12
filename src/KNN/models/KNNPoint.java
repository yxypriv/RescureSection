package KNN.models;

import KNN.IMappable;

public class KNNPoint implements IMappable {
	double[] features;
	String label;

	public KNNPoint(double[] features, String label) {
		super();
		this.features = features;
		this.label = label;
	}

	@Override
	public double getDistance(IMappable obj2) {
		KNNPoint p2 = (KNNPoint) obj2;
		double squareSum = 0.0;
		for (int i = 0; i < features.length; i++) {
			squareSum += Math.pow(features[i] - p2.features[i], 2);
		}
		return Math.sqrt(squareSum);
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("label: ");
		sb.append(label).append(", ");
		sb.append("features: ");
		sb.append("[");
		for (double feature : features)
			sb.append(feature).append(", ");
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]}");
		return sb.toString();
	}

}
