package KNN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KNN.utils.IrisFullCSVParser;
import utils.GroupingUtil;
import utils.ILabeler;

public class KNNTester {
	List<IMappable> trainingData;
	List<IMappable> testData;

	public void init(String trainingPath, String testingPath) {
		trainingData = IrisFullCSVParser.parse(trainingPath);
		testData = IrisFullCSVParser.parse(testingPath);

	}

	public List<String> test(int K) {
		KNNAlgorithm algorithm = new KNNAlgorithm(trainingData);

		List<String> result = new ArrayList<String>();
		for (IMappable point : testData) {
			String cluster = algorithm.cluster(point, K);
			result.add(cluster);
		}
		return result;
	}

	public void run(String trainingPath, String testingPath, int K) {
		init(trainingPath, testingPath);
		List<List<String>> allResult = new ArrayList<List<String>>();
		for (int i = 1; i <= K; i++) {
			List<String> test = test(i);
			allResult.add(test);
		}
		System.out.print("\t");
		for (int i = 1; i <= allResult.size(); i++)
			System.out.print(i + "\t");
		System.out.println();
		for (int i = 0; i < allResult.get(0).size(); i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < allResult.size(); j++) {
				System.out.print(allResult.get(j).get(i) + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		KNNTester test = new KNNTester();
		test.run("KNN/IrisTrain.csv", "KNN/IrisTest.csv", 7);
		System.out.println("-----[time cost: " + (System.currentTimeMillis() - t0) + " ms]--------------");

	}
}
