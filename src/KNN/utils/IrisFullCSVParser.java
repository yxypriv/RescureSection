package KNN.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import KNN.IMappable;
import KNN.models.KNNPoint;

public class IrisFullCSVParser {
	public static List<IMappable> parse(String path) {
		return parse(path, 2, new int[] { 0, 1, 2, 3 }, 4);
	}

	public static List<IMappable> parse(String path, int skipline, int[] featureColumnIndex, int labelColumn) {
		List<IMappable> result = new ArrayList<IMappable>();

		InputStream stream = IrisFullCSVParser.class.getClassLoader().getResourceAsStream(path);
		CSVParser parse;
		List<CSVRecord> records = null;
		try {
			parse = CSVFormat.EXCEL.parse(new InputStreamReader(stream, "ISO-8859-1"));
			records = parse.getRecords();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (CSVRecord record : records) {
			if (record.getRecordNumber() <= skipline)
				continue;
			double[] features = new double[featureColumnIndex.length];

			for (int i = 0; i < featureColumnIndex.length; i++) {
				double feature = Double.parseDouble(record.get(featureColumnIndex[i]));
				features[i] = feature;
			}
			result.add(new KNNPoint(features, record.get(labelColumn)));
		}
		return result;
	}

	public static void main(String[] args) {
		List<IMappable> points = IrisFullCSVParser.parse("KNN/irisFull.csv", 2, new int[] { 0, 1, 2, 3 }, 4);
		System.out.println(points.size());
		System.out.println(points);
	}
}
