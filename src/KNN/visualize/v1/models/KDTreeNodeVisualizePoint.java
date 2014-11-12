package KNN.visualize.v1.models;

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

	public <T extends Comparable<T>> KDTreeNodeVisualizePoint(
			KDTreeNode<T, KDPoint<T>> node) {
		Comparable<T>[] features = node.getFeatures();
		x = (java.lang.Double) features[0];
		y = (java.lang.Double) features[1];
	}

	public <T extends Comparable<T>> KDTreeNodeVisualizePoint(//
			KDTreeNode<T, KDPoint<T>> node, //
			double[] restrictions) {
		Comparable<T>[] features = node.getFeatures();
		x = (java.lang.Double) features[0];
		y = (java.lang.Double) features[1];

		areaTop = restrictions[0];
		areaRight = restrictions[1];
		areaBottom = restrictions[2];
		areaLeft = restrictions[3];
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

	public void setAreaTop(Comparable<java.lang.Double> comparable) {
		this.areaTop = (java.lang.Double) comparable;
	}

	public double getAreaLeft() {
		return areaLeft;
	}

	public void setAreaLeft(Comparable<java.lang.Double> comparable) {
		this.areaLeft = (java.lang.Double) comparable;
	}

	public double getAreaRight() {
		return areaRight;
	}

	public void setAreaRight(Comparable<java.lang.Double> comparable) {
		this.areaRight = (java.lang.Double) comparable;
	}

	public double getAreaBottom() {
		return areaBottom;
	}

	public void setAreaBottom(Comparable<java.lang.Double> comparable) {
		this.areaBottom = (java.lang.Double) comparable;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double[] getRestrictions() {
		return new double[] { areaTop, areaRight, areaBottom, areaLeft };
	}
}
