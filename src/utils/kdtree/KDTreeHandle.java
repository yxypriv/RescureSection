package utils.kdtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTreeHandle<T extends Comparable<T>, D extends IKDOrder<T>> {
	int dimention;
	KDTreeNode<T, D> root;

	public KDTreeHandle(int dimention, List<? extends IKDOrder<T>> data) {
		super();
		this.dimention = dimention;
		int currrentDimention = 0;
		root = constructTree(data, currrentDimention);
	}

	public KDTreeNode<T, D> getRoot() {
		return root;
	}

	private KDTreeNode<T, D> constructTree(List<? extends IKDOrder<T>> data, int currentDimention) {
		if (data.size() == 0)
			return null;
		if (data.size() == 1) {
			KDTreeNode<T, D> currentRoot = _constructKDTreeNode(//
					data.get(0), dimention, currentDimention);
			return currentRoot;
		}
		DimentionValueComparator<T, D> comparator = new DimentionValueComparator<T, D>(currentDimention);
		Collections.sort(data, comparator);
		int currentRootIndex = data.size() / 2;
		KDTreeNode<T, D> currentRoot = _constructKDTreeNode(//
				data.get(currentRootIndex), dimention, currentDimention);
		if (currentRootIndex != 0) {
			List<? extends IKDOrder<T>> leftData = data.subList(0, currentRootIndex);
			KDTreeNode<T, D> leftChild = constructTree(leftData, (currentDimention + 1) % dimention);
			currentRoot.setLeftChild(leftChild);
			if (null != leftChild)
				leftChild.setParent(currentRoot);
		}
		if (currentRootIndex + 1 < data.size()) {
			List<? extends IKDOrder<T>> rightData = data.subList(currentRootIndex + 1, data.size());
			KDTreeNode<T, D> rightChild = constructTree(rightData, (currentDimention + 1) % dimention);
			currentRoot.setRightChild(rightChild);
			if (null != rightChild)
				rightChild.setParent(currentRoot);
		}
		return currentRoot;
	}

	public KDTreeNode<T, D> searchLeaf(IKDOrder<T> query) {
		return searchLeaf(query, root);
	}

	@SuppressWarnings("unchecked")
	private KDTreeNode<T, D> searchLeaf(IKDOrder<T> query, KDTreeNode<T, D> currentRoot) {
		Integer divideIndex = currentRoot.getDivideIndex();
		Comparable<T> divideValue = currentRoot.getFeatures()[divideIndex];
		Comparable<T> queryDivideValue = query.getKDValue()[divideIndex];
		if (queryDivideValue.compareTo((T) divideValue) < 0) {
			if (currentRoot.getLeftChild() != null) {
				return searchLeaf(query, currentRoot.getLeftChild());
			} else {
				return currentRoot;
			}
		} else {
			if (currentRoot.getRightChild() != null) {
				return searchLeaf(query, currentRoot.getRightChild());
			} else {
				return currentRoot;
			}
		}
	}

	private static <T extends Comparable<T>, D extends IKDOrder<T>> KDTreeNode<T, D> _constructKDTreeNode(//
			IKDOrder<T> singleData, int dimention, int currentDimention) {
		KDTreeNode<T, D> currentRoot = new KDTreeNode<T, D>(dimention);
		currentRoot.setData(singleData);
		currentRoot.setFeatures(singleData.getKDValue());
		currentRoot.setDivideIndex(currentDimention);
		return currentRoot;
	}

	private static class DimentionValueComparator<T extends Comparable<T>, D extends IKDOrder<T>> implements Comparator<IKDOrder<T>> {
		int currentIndex;

		public DimentionValueComparator(int currentIndex) {
			super();
			this.currentIndex = currentIndex;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compare(IKDOrder<T> o1, IKDOrder<T> o2) {
			Comparable<T>[] array1 = o1.getKDValue();
			Comparable<T>[] array2 = o2.getKDValue();
			return array1[currentIndex].compareTo((T) array2[currentIndex]);
		}

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

		KDTreeNode<Integer, KDPoint<Integer>> leaf = handle.searchLeaf(new KDPoint<Integer>(new Integer[] { 3, 4 }));
		for (Comparable<Integer> fi : leaf.getFeatures())
			System.out.println(fi);
	}
}
