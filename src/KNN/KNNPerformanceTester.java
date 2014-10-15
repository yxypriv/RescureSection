package KNN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.GroupingUtil;
import utils.ILabeler;

public class KNNPerformanceTester {
	int exampleDivide = 10;
	Map<Integer, Integer> label2IndexMap = new HashMap<Integer, Integer>();
	Map<Integer, Integer> Index2LabelMap = new HashMap<Integer, Integer>();
	
	public KNNPerformanceTester() {
	}

	List<List<IMappable>> groupedDataSet = new ArrayList<List<IMappable>>();

	public void init(String path) {
		List<IMappable> wholeTrainingSet = null;
		//TODO: INIT
		
		
		Map<Integer, ArrayList<IMappable>> labeledGroupingMap = GroupingUtil.getLabelGroupingList(wholeTrainingSet, new ILabeler<IMappable>() {
			@Override
			public int getLabel(IMappable e) {
				return e.getLabel();
			}
		});
		
		int labelIndex = 0;
		for(Integer label : labeledGroupingMap.keySet()) {
			label2IndexMap.put(label, labelIndex);
			Index2LabelMap.put(labelIndex, label);
			labelIndex++;
		}
		
		for(int i=0; i<exampleDivide; i++) {
			List<IMappable> singleSet = new ArrayList<IMappable>();
			for(int label : labeledGroupingMap.keySet()) {
				ArrayList<IMappable> arrayWithLabel = labeledGroupingMap.get(label);
				int piece_size = arrayWithLabel.size();
				singleSet.addAll(arrayWithLabel.subList(piece_size*i, //
						Math.min(arrayWithLabel.size(), piece_size * (i+1))));
			}
			groupedDataSet.add(singleSet);
		}
	}
	
	public int[][] crossTesting(int K) {
		int[][] confusionMatrix = new int[label2IndexMap.size()][label2IndexMap.size()];
		for(int i=0; i<exampleDivide; i++) {
			//indicats i is the testing set
			List<IMappable> currentTrainingSet = new ArrayList<IMappable>();
			for(int j=0; j<exampleDivide; j++) {
				if(j!=i) {
					currentTrainingSet.addAll(groupedDataSet.get(j));
				}
			}

			KNNAlgorithm algorithm = new KNNAlgorithm(currentTrainingSet);
			
			List<IMappable> testingSet = groupedDataSet.get(i);
			for(IMappable testElement : testingSet) {
				int cluster = algorithm.cluster(testElement, K);
				int labelIndex = label2IndexMap.get(testElement.getLabel());
				int clusterIndex = label2IndexMap.get(cluster);
				confusionMatrix[labelIndex][clusterIndex]++;
			}
		}
		return confusionMatrix;
	}

}
