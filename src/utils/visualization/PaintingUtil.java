package utils.visualization;

import java.awt.Graphics2D;
import java.awt.Point;

public class PaintingUtil {
	public static void drawLine(Graphics2D g2, Point start, Point end) {
		g2.drawLine(start.x, start.y, end.x, end.y);
	}

	/**
	 * 
	 * @param angelInDegree
	 *            angel in degree
	 * @param radius
	 */
	public static void drawLine(Graphics2D g2, Point origin, double angelInDegree, double radius) {
		angelInDegree += 180;
		Double delta_x = Math.cos(angelInDegree / 180 * Math.PI) * radius;
		Double delta_y = Math.sin(angelInDegree / 180 * Math.PI) * radius;
		g2.drawLine(origin.x, origin.y, origin.x + delta_x.intValue(), origin.y + delta_y.intValue());
	}

	public static void drawOval(Graphics2D g2, Point origin, Integer radius) {
		g2.fillOval(origin.x - radius.intValue(), origin.y - radius.intValue(),//
				2 * radius + 1, 2 * radius + 1);
	}
	
	public static void drawOval(Graphics2D g2, Point origin, Integer radius, int scaleX, int scaleY) {
		g2.fillOval(origin.x - radius.intValue(), origin.y - radius.intValue(),//
				2 * radius + 1, 2 * radius + 1);
	}
}
