package utils.visualization;

import java.awt.Graphics;

public class Coordination2D {
	double[][] range;
	int[] origin;
	int width;
	int height;
	int padding = 50;

	public Coordination2D(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		init();
		;
	}

	public int[] getSize() {
		return new int[] { width, height };
	}

	void init() {
		range = new double[2][];
		origin = new int[2];
		origin[0] = padding;
		origin[1] = height - padding;
	}

	public void extendRange(double[] point) {
		for (int i = 0; i < 2; i++) {
			if (range[i] == null) {
				range[i] = new double[] { point[i], point[i] };
			} else {
				if (range[i][0] > point[i])
					range[i][0] = point[i];
				if (range[i][1] < point[i])
					range[i][1] = point[i];
			}
		}
	}

	public double[] getScale() {
		double[] scale = new double[2];
		for (int i = 0; i < 2; i++) {
			int rangeContain = (int) (range[i][1] - range[i][0] + 1);
			double axisLength = getSize()[i] - 2 * padding;
			scale[i] = axisLength / rangeContain;
		}
		return scale;
	}
	
	public void plot(double[] point, Graphics g) {
		
	}
	
	public void paintCoorinates(Graphics g) {
		
	}
}
