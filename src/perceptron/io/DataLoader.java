package perceptron.io;

import java.util.ArrayList;
import java.util.List;

import utils.io.FileUtil;
import utils.io.FileUtil.FileLineProcess;

public class DataLoader {
	public static List<double[]> loadData(String path) {
		final List<double[]> result = new ArrayList<double[]>();
		FileUtil.iteratePathFileByLine(path, new FileLineProcess() {
			
			public boolean process(String line) {
				String[] split = line.split(",");
				double[] data = new double[split.length];
				for (int i = 0; i < split.length; i++) {
					data[i] = Double.parseDouble(split[i].trim());
				}
				result.add(data);
				return true;
			}
			
		});
		return result;
	}
	
	public static void main(String[] args) {
		List<double[]> loadData = loadData("C:/Users/Xingyu/Downloads/data/label0.dat");
		System.out.println(loadData.size());
	}
}
