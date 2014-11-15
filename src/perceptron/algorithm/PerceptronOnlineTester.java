package perceptron.algorithm;

import java.util.ArrayList;
import java.util.List;

import perceptron.io.TicTacToeFeatureExtraction2;
import perceptron.model.OnlinePerceptronData;
import utils.interfaces.FeatureTrainingData;
import utils.io.FileUtil;
import utils.io.FileUtil.FileLineProcess;

public class PerceptronOnlineTester {
	List<FeatureTrainingData> allData;
	PerceptronAlgorithmOnlineBuff algorithm;

	public PerceptronOnlineTester() {
		algorithm = new PerceptronAlgorithmOnlineBuff();
	}

	public void loadData() {
		allData = new ArrayList<FeatureTrainingData>();
		FileUtil.iterateResourceFileByLine("perceptron/tic-tac-toe.data.txt", new FileLineProcess() {
			@Override
			public boolean process(String line) {
				if (null == line)
					return false;
				String[] split = line.split(",");
				char[] originalFeature = new char[9];
				for (int i = 0; i < 9; i++) {
					originalFeature[i] = split[i].charAt(0);
				}
				String label = split[9];

				OnlinePerceptronData data = new OnlinePerceptronData(//
						TicTacToeFeatureExtraction2.extractFeature(originalFeature),//
						label, allData.size());
				allData.add(data);

				return true;
			}
		});
	}

	public void train() {
		for (FeatureTrainingData data : allData) {
			algorithm.train(data);
			algorithm.errorRate();
			algorithm.displayStatus();
		}
	}

	public static void main(String[] args) {
		PerceptronOnlineTester tester = new PerceptronOnlineTester();
		tester.loadData();
		tester.train();
	}
}
