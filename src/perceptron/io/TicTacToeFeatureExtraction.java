package perceptron.io;

public class TicTacToeFeatureExtraction {
	public static int[] extractFeature(char[] matrix) {
		int[] features = new int[34];
		fillFeatureFor('x', 0, matrix, features);
		fillFeatureFor('o', 17, matrix, features);
		return features;
	}

	private static void fillFeatureFor(char player, int start, char[] matrix, int[] features) {
		int[] count = new int[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == player)
				count[i] = 1;
		}
		for (int i = 0; i < 9; i++) {
			features[start + i] = count[i];
		}
		for (int i = 0; i < 3; i++) {
			features[start + 9 + i] = 0;
			features[start + 12 + i] = 0;
			for (int j = 0; j < 3; j++) {
				features[start + 9 + i] += count[i * 3 + j];
				features[start + 12 + i] += count[i + j * 3];
			}
		}
		features[start + 15] = 0;
		features[start + 16] = 0;
		for (int i = 0; i < 3; i++) {
			features[start + 15] += count[i * 3 + i];
			features[start + 15] += count[(i + 1) * 3 - (i + 1)];
		}
	}
}
