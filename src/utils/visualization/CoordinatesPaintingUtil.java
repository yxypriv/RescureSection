package utils.visualization;

import java.awt.Graphics2D;
import java.awt.Point;

public class CoordinatesPaintingUtil {
	int coordinatesScale;
	int originX;
	int originY;

	public CoordinatesPaintingUtil(int coordinatesScale, int originX, int originY) {
		super();
		this.coordinatesScale = coordinatesScale;
		this.originX = originX;
		this.originY = originY;
	}

	public void paintPoint(double x, double y, Graphics2D g2) {
		int deltaX = (int) (x * coordinatesScale);
		int deltaY = (int) (y * coordinatesScale);
		PaintingUtil.drawOval(g2, new Point(originX + deltaX, originY - deltaY), 3);
	}
}
