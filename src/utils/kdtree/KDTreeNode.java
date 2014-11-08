package utils.kdtree;

public class KDTreeNode<T extends Comparable<T>, D extends IKDOrder<T>> {
	int dimention;
	Integer divideIndex;

	
	Comparable<T>[] features;

	IKDOrder<T> data;

	KDTreeNode<T, D> leftChild;
	KDTreeNode<T, D> rightChild;
	KDTreeNode<T, D> parent;

	public KDTreeNode(int dimention) {
		super();
		this.dimention = dimention;
	}

	public IKDOrder<T> getData() {
		return data;
	}

	public void setData(IKDOrder<T> ikdOrder) {
		this.data = ikdOrder;
	}

	public void setLeftChild(KDTreeNode<T, D> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(KDTreeNode<T, D> rightChild) {
		this.rightChild = rightChild;
	}

	public Comparable<T>[] getFeatures() {
		return features;
	}

	public void setFeatures(Comparable<T>[] comparables) {
		this.features = comparables;
	}

	public Integer getDivideIndex() {
		return divideIndex;
	}

	public void setDivideIndex(Integer divideIndex) {
		this.divideIndex = divideIndex;
	}

	public KDTreeNode<T, D> getLeftChild() {
		return leftChild;
	}

	public KDTreeNode<T, D> getRightChild() {
		return rightChild;
	}

	public KDTreeNode<T, D> getParent() {
		return parent;
	}

	public void setParent(KDTreeNode<T, D> parent) {
		this.parent = parent;
	}

}
