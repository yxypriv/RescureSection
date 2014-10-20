package utils.visualization;

import java.awt.Graphics2D;
import java.awt.Point;

public class CoordinatesPaintingUtil {
	int coordinatesScale;
	int originX;
	int originY;
	int width;
	int height;

	public CoordinatesPaintingUtil(int coordinatesScale, int originX, int originY, int width, int height) {
		super();
		this.coordinatesScale = coordinatesScale;
		this.originX = originX;
		this.originY = originY;
		this.width = width;
		this.height = height;
	}

	public void paintPoint(double x, double y, Graphics2D g2) {
		int deltaX = (int) (x * coordinatesScale);
		int deltaY = (int) (y * coordinatesScale);
		PaintingUtil.drawOval(g2, new Point(originX + deltaX, originY - deltaY), 3);
	}

	public void paintXSplitLine(double x, double top, double bottom, Graphics2D g2) {
		int x0 = getXInCoordinates(x);
		int y0 = originY, yt = originY - height;
		if (top >= 0)
			yt = getYInCoordinates(top) + 1;
		if (bottom >= 0)
			y0 = getYInCoordinates(bottom) - 1;
		g2.drawLine(x0, y0, x0, yt);
	}  

	public void paintYSpltLine(double y, double left, double right, Graphics2D g2) {
		int y0 = getYInCoordinates(y);
		int x0 = originX, xt = originX + width;
		if (left >= 0)
			x0 = getXInCoordinates(left) + 1;
		if (right >= 0)
			xt = getXInCoordinates(right) - 1;
		g2.drawLine(x0, y0, xt, y0);
	}

	private int getXInCoordinates(double x) {
		return (int) (originX + x * coordinatesScale);
	}

	private int getYInCoordinates(double y) {
		return (int) (originY - y * coordinatesScale);
	}
}
