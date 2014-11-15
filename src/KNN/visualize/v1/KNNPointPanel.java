package KNN.visualize.v1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import KNN.visualize.v1.models.KDTreeNodeVisualizePoint;
import utils.visualization.CoordinatesPaintingUtil;
import utils.visualization.PaintingUtil;

public class KNNPointPanel extends JPanel {
	private static final long serialVersionUID = -8243445124526025440L;

	private static final int default_padding = 30;

	private int padding_left;
	private int padding_right;
	private int padding_top;
	private int padding_bottom;

	private int width;
	private int height;

	private int pointRadius = 2;
	private int arraySize = 5;

	List<KDTreeNodeVisualizePoint> pointsList;
	Point origin;

	public KNNPointPanel(int width, int height) {
		padding_left = default_padding;
		padding_right = default_padding;
		padding_bottom = default_padding;
		padding_top = default_padding;
		this.width = width;
		this.height = height;
		origin = new Point(padding_left, height - padding_bottom);
		pointsList = new ArrayList<KDTreeNodeVisualizePoint>();
	}

	public void addPoint(KDTreeNodeVisualizePoint kdtp) {
		pointsList.add(kdtp);
	}

	// --------------------------------------------------------------

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		paintCoordinatesSystem(g2, Color.black);
		paintPoints(g2);
	}

	private void paintCoordinatesSystem(Graphics2D g2, Color color) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Color originalColor = g2.getColor();
		g2.setColor(color);

		Point topEnd = new Point(padding_left, padding_top);
		Point rightEnd = new Point(width - padding_right, height - padding_bottom);

		// x-axis
		PaintingUtil.drawLine(g2, origin, rightEnd);
		// y-axis
		PaintingUtil.drawLine(g2, origin, topEnd);

		// x-axis arrow
		PaintingUtil.drawLine(g2, topEnd, -45, arraySize);
		PaintingUtil.drawLine(g2, topEnd, -135, arraySize);

		// y-axis arrow
		PaintingUtil.drawLine(g2, rightEnd, -45, arraySize);
		PaintingUtil.drawLine(g2, rightEnd, 45, arraySize);

		// draw origin Point
		PaintingUtil.drawOval(g2, origin, pointRadius);

		// draw text "X" and draw text "Y"
		g2.drawString("X", rightEnd.x - 10, rightEnd.y + 10);
		g2.drawString("Y", topEnd.x - 10, topEnd.y - 5);
		g2.drawString("(0, 0)", origin.x - 30, origin.y + 10);

		// numerate axis
		// int xCoordNumbers = 10;
		// int yCoordNumbers = 10;
		int xLength = 20;
		int yLength = 20;

		// draw x-axis numbers
		for (int i = 1; i * xLength < rightEnd.x - origin.x; i++) {
			g2.drawLine(origin.x + (i * xLength), origin.y - 5, origin.x + (i * xLength), origin.y + 5);
			g2.drawString(Integer.toString(i), origin.x + (i * xLength) - 7, origin.y + 20);
		}

		// draw y-axis numbers
		for (int i = 1; i * yLength < origin.y - topEnd.y; i++) {
			g2.drawLine(origin.x - 5, origin.y - (yLength * i), origin.x + 5, origin.y - (yLength * i));
			g2.drawString(Integer.toString(i), origin.x - 20, origin.y - (yLength * i) + 3);
		}
		g2.setColor(originalColor);
	}

	private void paintPoints(Graphics2D g2) {
		Color originalColor = g2.getColor();
		CoordinatesPaintingUtil cpaintUtil = new CoordinatesPaintingUtil(//
				20, origin.x, origin.y, //
				width - padding_left - padding_right, //
				height - padding_top - padding_bottom);
		for (KDTreeNodeVisualizePoint point : pointsList) {
			g2.setColor(point.getColor());
			// PaintingUtil.drawOval(g2, point, 2);
			cpaintUtil.paintPoint(point.getX(), point.getY(), g2);
			// System.out.println("~~~~~~~~~~" + point.getX() + "\t" +
			// point.getY());
			switch (point.getDivide()) {
			case 'X':
				cpaintUtil.paintXSplitLine(point.getX(), point.getAreaTop(),//
						point.getAreaBottom(), g2);
				break;
			case 'Y':
				cpaintUtil.paintYSpltLine(point.getY(), point.getAreaLeft(),//
						point.getAreaRight(), g2);
				break;
			}
		}

		g2.setColor(originalColor);
	}

}
