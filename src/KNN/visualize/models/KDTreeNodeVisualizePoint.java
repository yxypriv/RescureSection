package KNN.visualize.models;

import java.awt.Color;
import java.awt.Point;

import utils.kdtree.KDPoint;
import utils.kdtree.KDTreeNode;

public class KDTreeNodeVisualizePoint extends Point {
	private static final long serialVersionUID = -1684663000236177424L;

	double x;
	double y;
	Character divide;

	double areaTop = -1;
	double areaLeft = -1;
	double areaRight = -1;
	double areaBottom = -1;

	Color color;

	public <T extends Comparable<T>> KDTreeNodeVisualizePoint(KDTreeNode<T, KDPoint<T>> node) {
		Comparable<T>[] features = node.getFeatures();
		x = (Integer) features[0];
		y = (Integer) features[1];
	}

	public double getX() {
		return (double) x;
	}

	public double getY() {
		return (double) y;
	}

	public Character getDivide() {
		return divide;
	}

	public void setDivide(Character divide) {
		this.divide = divide;
	}

	public double getAreaTop() {
		return areaTop;
	}

	public void setAreaTop(double areaTop) {
		this.areaTop = areaTop;
	}

	public double getAreaLeft() {
		return areaLeft;
	}

	public void setAreaLeft(double areaLeft) {
		this.areaLeft = areaLeft;
	}

	public double getAreaRight() {
		return areaRight;
	}

	public void setAreaRight(double areaRight) {
		this.areaRight = areaRight;
	}

	public double getAreaBottom() {
		return areaBottom;
	}

	public void setAreaBottom(double areaBottom) {
		this.areaBottom = areaBottom;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
