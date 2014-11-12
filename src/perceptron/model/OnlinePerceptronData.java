package perceptron.model;

public class OnlinePerceptronData extends PerceptronData {

	int timeStamp;

	public OnlinePerceptronData(int[] features, String label, int timeStamp) {
		super(features, label);
		this.timeStamp = timeStamp;
	}

	public OnlinePerceptronData(double[] features, String label, int timeStamp) {
		super(features, label);
		this.timeStamp = timeStamp;
	}

	public int getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

}
