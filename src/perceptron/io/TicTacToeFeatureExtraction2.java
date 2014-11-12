package perceptron.io;

public class TicTacToeFeatureExtraction2 {
	public static int[] extractFeature(char[] matrix) {
		int[] features = new int[34];
		fillFeatureFor('x', 0, matrix, features);
		fillFeatureFor('o', 9, matrix, features);
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
	}
}
