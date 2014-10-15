package KNN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import utils.HitsUtil;
import utils.ILabeler;

public class KNNAlgorithm {

	List<IMappable> points;
	int default_K = 5;

	public KNNAlgorithm(List<IMappable> points) {
		this.points = points;
	}
	
	public int cluster(IMappable newPoint) {
		return cluster(newPoint, default_K);
	}
	
	public int cluster(IMappable newPoint, int K) {
		List<IMappable> knn = getKNN(newPoint, K);
		int[] mostHits = HitsUtil.getMostHits(knn, new ILabeler<IMappable>() {
			@Override
			public int getLabel(IMappable e) {
				return e.getLabel();
			}
		});
		return mostHits[0];
	}

	/**
	 * PriorityBlockingQueue
	 * 
	 * @param newPoint
	 * @param K
	 * @return
	 */
	public List<IMappable> getKNN(IMappable newPoint, int K) {
		Queue<OneWayEdge> tempResult = new PriorityBlockingQueue<KNNAlgorithm.OneWayEdge>(K, Collections.reverseOrder());
		for (IMappable point : points) {
			double distance = newPoint.getDistance(point);
			if (tempResult.size() < K)
				tempResult.add(new OneWayEdge(point, distance));
			else {
				if (distance < tempResult.peek().distance) {
					tempResult.poll();
					tempResult.add(new OneWayEdge(point, distance));
				}
			}
		}

		List<IMappable> result = new ArrayList<IMappable>();
		for (OneWayEdge e : tempResult) {
			result.add(e.end);
		}
		return result;
	}

	/**
	 * manual
	 * 
	 * @param newPoint
	 * @param K
	 * @return
	 */
	public List<IMappable> getKNN2(IMappable newPoint, int K) {
		OneWayEdge[] tempResult = new OneWayEdge[K];

		for (IMappable point : points) {
			double distance = newPoint.getDistance(point);
			int j;
			for (j = K - 1; j >= 0 && (tempResult[j] == null || tempResult[j].distance > distance); j--)
				;
			// now j is either -1 or result[j] <= distance
			j++;
			// now j is the place to put
			for (int loader = K - 1; loader > j; loader--) {
				tempResult[loader] = tempResult[loader - 1];
			}
			tempResult[j] = new OneWayEdge(point, distance);
		}

		List<IMappable> result = new ArrayList<IMappable>();
		for (OneWayEdge e : tempResult) {
			result.add(e.end);
		}
		return result;
	}

	private static class OneWayEdge implements Comparable<OneWayEdge> {
		public OneWayEdge(IMappable end, Double distance) {
			super();
			this.end = end;
			this.distance = distance;
		}

		IMappable end;
		Double distance;

		@Override
		public int compareTo(OneWayEdge o) {
			return this.distance.compareTo(o.distance);
		}
	}
}
