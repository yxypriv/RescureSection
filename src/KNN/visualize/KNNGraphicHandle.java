package KNN.visualize;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import utils.kdtree.KDPoint;
import utils.kdtree.KDTreeHandle;
import utils.kdtree.KDTreeNode;
import KNN.visualize.models.KDTreeNodeVisualizePoint;

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
		List<KDPoint<Integer>> pointList = new ArrayList<KDPoint<Integer>>();
		pointList.add(new KDPoint<Integer>(new Integer[] { 1, 1 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 1, 5 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 2, 4 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 3, 3 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 4, 2 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 5, 1 }));
		pointList.add(new KDPoint<Integer>(new Integer[] { 6, 3 }));

		KDTreeHandle<Integer, KDPoint<Integer>> handle = new KDTreeHandle<Integer, KDPoint<Integer>>(2, pointList);

		final List<KDTreeNodeVisualizePoint> visList = new ArrayList<KDTreeNodeVisualizePoint>();

		dfs(handle.getRoot(), visList);
			
			
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				KNNGraphicHandle ex = new KNNGraphicHandle();
				for(KDTreeNodeVisualizePoint vis : visList) {
					ex.addPoint(vis);
				}
				ex.repaint();
				ex.setVisible(true);
			}
		});
	}

	private static void dfs(KDTreeNode<Integer, KDPoint<Integer>> current, List<KDTreeNodeVisualizePoint> list) {
		KDTreeNode<Integer, KDPoint<Integer>> parent = current.getParent();
		KDTreeNodeVisualizePoint vis = new KDTreeNodeVisualizePoint(current);
		if (null != parent) {
			if (parent.getDivideIndex() == 0) {
				if (current == parent.getLeftChild()) {
					vis.setAreaRight((Integer) parent.getFeatures()[0]);
				} else {
					vis.setAreaLeft((Integer) parent.getFeatures()[0]);
				}
			} else {
				if (current == parent.getLeftChild()) {
					vis.setAreaTop((Integer) parent.getFeatures()[1]);
				} else {
					vis.setAreaBottom((Integer) parent.getFeatures()[1]);
				}
			}
		}
		if(current.getDivideIndex() == 0) {
			vis.setColor(Color.green);
			vis.setDivide('X');
		} else {
			vis.setColor(Color.red);
			vis.setDivide('Y');
		}
		list.add(vis);
		if(current.getLeftChild()!=null)
			dfs(current.getLeftChild(), list);
		if(current.getRightChild()!=null)
			dfs(current.getRightChild(), list);
	}
}