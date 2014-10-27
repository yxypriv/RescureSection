package KNN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KNN.utils.IrisFullCSVParser;
import utils.GroupingUtil;
import utils.ILabeler;

public class KNNPerformanceTester {
	int exampleDivide = 10;
	Map<String, Integer> label2IndexMap = new HashMap<String, Integer>();
	Map<Integer, String> Index2LabelMap = new HashMap<Integer, String>();

	List<List<IMappable>> groupedDataSet = new ArrayList<List<IMappable>>();

	public void init(String path) {
		List<IMappable> wholeTrainingSet = IrisFullCSVParser.parse(path);

		Map<String, ArrayList<IMappable>> labeledGroupingMap = GroupingUtil.getLabelGroupingList(wholeTrainingSet, new ILabeler<IMappable>() {
			@Override
			public String getLabel(IMappable e) {
				return e.getLabel();
			}
		});

		int labelIndex = 0;
		for (String label : labeledGroupingMap.keySet()) {
			label2IndexMap.put(label, labelIndex);
			Index2LabelMap.put(labelIndex, label);
			labelIndex++;
		}

		for (int i = 0; i < exampleDivide; i++) {
			List<IMappable> singleSet = new ArrayList<IMappable>();
			for (String label : labeledGroupingMap.keySet()) {
				ArrayList<IMappable> arrayWithLabel = labeledGroupingMap.get(label);
				int piece_size = arrayWithLabel.size() / exampleDivide;
				singleSet.addAll(arrayWithLabel.subList(piece_size * i, //
						Math.min(arrayWithLabel.size(), piece_size * (i + 1))));
			}
			groupedDataSet.add(singleSet);
		}
	}

	public int[][] crossTesting(int K) {
		int[][] confusionMatrix = new int[label2IndexMap.size()][label2IndexMap.size()];
		for (int i = 0; i < exampleDivide; i++) {
			// indicats i is the testing set
			List<IMappable> currentTrainingSet = new ArrayList<IMappable>();
			for (int j = 0; j < exampleDivide; j++) {
				if (j != i) {
					currentTrainingSet.addAll(groupedDataSet.get(j));
				}
			}

			KNNAlgorithm algorithm = new KNNAlgorithm(currentTrainingSet);

			List<IMappable> testingSet = groupedDataSet.get(i);
			for (IMappable testElement : testingSet) {
				String cluster = algorithm.cluster(testElement, K);
				int labelIndex = label2IndexMap.get(testElement.getLabel());
				int clusterIndex = label2IndexMap.get(cluster);
				confusionMatrix[labelIndex][clusterIndex]++;
			}
		}
		return confusionMatrix;
	}

	public void displayResult(int[][] result) {

		if (result.length > 0) {
			System.out.print("\t");
			for (int i = 0; i < result[0].length; i++)
				System.out.print(Index2LabelMap.get(i) + "\t");
			System.out.println();
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(Index2LabelMap.get(i) + "\t");
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void run(String path, int K) {
		init(path);
		int[][] result = crossTesting(K);
		displayResult(result);
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 7; i++) {
			long t0 = System.currentTimeMillis();
			System.out.println("-------------[k = " + i + "]-------------");
			KNNPerformanceTester test = new KNNPerformanceTester();
			test.run("KNN/IrisFull.csv", i);
			System.out.println("-----[time cost: " + (System.currentTimeMillis() - t0) + " ms]--------------");
		}

	}
}
