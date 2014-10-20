package KNN.visualize.v1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import utils.kdtree.KDPoint;
import utils.kdtree.KDTreeHandle;
import utils.kdtree.KDTreeNode;
import KNN.visualize.v1.models.KDTreeNodeVisualizePoint;

public class KNNGraphicHandle extends JFrame {
	private static final long serialVersionUID = -5159650743320787762L;

	KNNPointPanel pointPanel = new KNNPointPanel(600, 400);

	public KNNGraphicHandle() {
		add(pointPanel);
		setTitle("KNN Dispaly");
		setSize(600, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void addPoint(KDTreeNodeVisualizePoint vis) {
		pointPanel.addPoint(vis);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		pointPanel.repaint();
	}

	public static void main(String[] args) {
		List<KDPoint<Double>> pointList = new ArrayList<KDPoint<Double>>();
		// pointList.add(new KDPoint<Integer>(new Integer[] { 1, 1 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 1, 5 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 2, 4 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 3, 3 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 4, 2 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 5, 1 }));
		// pointList.add(new KDPoint<Integer>(new Integer[] { 6, 3 }));

		Random rand = new Random();
		for (int i = 0; i < 9; i++) {
			pointList.add(new KDPoint<Double>(new Double[] //
					{ rand.nextDouble() * 25, rand.nextDouble() * 15 }));
		}

		// KDTreeHandle<Integer, KDPoint<Integer>> handle = new
		// KDTreeHandle<Integer, KDPoint<Integer>>(2, pointList);
		KDTreeHandle<Double, KDPoint<Double>> handle = new KDTreeHandle<Double, KDPoint<Double>>(2, pointList);

		final List<KDTreeNodeVisualizePoint> visList = new ArrayList<KDTreeNodeVisualizePoint>();

		dfs(handle.getRoot(), visList, new double[] { -1, -1, -1, -1 });

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				KNNGraphicHandle ex = new KNNGraphicHandle();
				for (KDTreeNodeVisualizePoint vis : visList) {
					ex.addPoint(vis);
				}
				ex.repaint();
				ex.setVisible(true);
			}
		});
	}

	private static void dfs(KDTreeNode<Double, KDPoint<Double>> kdTreeNode, List<KDTreeNodeVisualizePoint> list, double[] parentRestrictions) {
		KDTreeNode<Double, KDPoint<Double>> parent = kdTreeNode.getParent();
		KDTreeNodeVisualizePoint vis = new KDTreeNodeVisualizePoint(kdTreeNode, parentRestrictions);
		if (null != parent) {
			if (parent.getDivideIndex() == 0) {
				if (kdTreeNode == parent.getLeftChild()) {
					vis.setAreaRight(parent.getFeatures()[0]);
				} else {
					vis.setAreaLeft(parent.getFeatures()[0]);
				}
			} else {
				if (kdTreeNode == parent.getLeftChild()) {
					vis.setAreaTop(parent.getFeatures()[1]);
				} else {
					vis.setAreaBottom(parent.getFeatures()[1]);
				}
			}
		}
		if (kdTreeNode.getDivideIndex() == 0) {
			vis.setColor(Color.green);
			vis.setDivide('X');
		} else {
			vis.setColor(Color.red);
			vis.setDivide('Y');
		}
		list.add(vis);
		if (kdTreeNode.getLeftChild() != null)
			dfs(kdTreeNode.getLeftChild(), list, vis.getRestrictions());
		if (kdTreeNode.getRightChild() != null)
			dfs(kdTreeNode.getRightChild(), list, vis.getRestrictions());
	}

}