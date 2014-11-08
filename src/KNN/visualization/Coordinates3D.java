package KNN.visualization;

import java.awt.Graphics2D;

public class Coordinates3D {
	double range[][]; // [0] is x, [1] is y, [2] is z.
	double scale[];
	// double viewPoint[];
	double panelWidth = 800;
	double panelHeight = 600;
	double panelLeft = 0;
	double panelTop = 0;
	Double origin_x = null;
	Double origin_y = null;

	public Coordinates3D() {
		init();
	}

	public Coordinates3D(double panelLeft, double panelTop, double panelWidth, double panelHeight) {
		super();
		this.panelLeft = panelLeft;
		this.panelTop = panelTop;
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;
		init();

	}

	private void init() {
		origin_x = (panelWidth - panelLeft) / 3 + panelLeft;
		origin_y = (panelHeight - panelTop) * 2 / 3 + panelLeft;

		scale = new double[] { 10, 10, 10 };
	}

	public double[] getScale() {
		return scale;
	}

	public void setScale(double[] scale) {
		this.scale = scale;
	}

	public double getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(double panelWidth) {
		this.panelWidth = panelWidth;
	}

	public double getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(double panelHeight) {
		this.panelHeight = panelHeight;
	}

	public double getPanelLeft() {
		return panelLeft;
	}

	public void setPanelLeft(double panelLeft) {
		this.panelLeft = panelLeft;
	}

	public double getPanelTop() {
		return panelTop;
	}

	public void setPanelTop(double panelTop) {
		this.panelTop = panelTop;
	}

	public void paintCoordinates(Graphics2D g2) {
		double panel_right = panelLeft + panelWidth;
		double panel_bottom = panelTop + panelHeight;

		g2.drawLine(origin_x.intValue(), (int) origin_y.intValue(), (int) panel_right, (int) origin_y.intValue());
		
		g2.drawLine(origin_x.intValue(), origin_y.intValue(), origin_x.intValue(), (int)panelTop);
		
	}
}
