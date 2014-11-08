package perceptron;

import java.util.ArrayList;
import java.util.List;

import perceptron.io.DataLoader;
import perceptron.model.PerceptronData;
import utils.interfaces.FeatureTrainingData;

public class PerceptronTester {
	List<FeatureTrainingData> allData;

	public void loadData() {
		allData = new ArrayList<FeatureTrainingData>();
		
		List<double[]> loadData0 = DataLoader.loadData("C:/Users/Xingyu/Downloads/data/label0.dat");
		List<double[]> loadData1 = DataLoader.loadData("C:/Users/Xingyu/Downloads/data/label1.dat");
		for (double[] data : loadData0) {
			PerceptronData pd = new PerceptronData(data, "label0");
			allData.add(pd);
		}

		for (double[] data : loadData1) {
			PerceptronData pd = new PerceptronData(data, "label1");
			allData.add(pd);
		}
		// System.out.println(loadData.size());
	}
	
	public void train() {
		PerceptronAlgorithm algorthm = new PerceptronAlgorithm();
		algorthm.train(allData);
	}
	
	public static void main(String[] args) {
		PerceptronTester tester = new PerceptronTester();
		tester.loadData();
		tester.train();
	}
}
