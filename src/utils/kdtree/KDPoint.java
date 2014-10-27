package utils.kdtree;

public class KDPoint<T extends Comparable<T>> implements IKDOrder<T> {
	T[] coordinates;

	public KDPoint(T[] coordinates) {
		super();
		this.coordinates = coordinates;
	}

	public T[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(T[] coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public Comparable<T>[] getKDValue() {
		return coordinates;
	}

}
