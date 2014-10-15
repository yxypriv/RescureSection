package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HitsUtil {
	/**
	 * 
	 * @param set
	 * @param labelMethod
	 * @return a list of int array stands for [label, hits] with descending order by hits.
	 */
	public static <E> List<int[]> getOrderedHits(Collection<E> set, ILabeler<E> labelMethod) {
		Map<Integer, Integer> hitsMap = getHitsMap(set, labelMethod);
		
		List<int[]> result = new ArrayList<int[]>();
		for (Integer label : hitsMap.keySet()) {
			int[] labelHits = new int[2];
			labelHits[0] = label;
			labelHits[1] = hitsMap.get(label);
			result.add(labelHits);
		}
		Collections.sort(result, Collections.reverseOrder(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return new Integer(o1[1]).compareTo(o2[1]);
			}
		}));
		return result;
	}
	/**
	 * 
	 * @param set
	 * @param labelMethod
	 * @return most hit label integer pair stands for [label, hits]  
	 */
	
	public static <E> int[] getMostHits(Collection<E> set, ILabeler<E> labelMethod) {
		Map<Integer, Integer> hitsMap = getHitsMap(set, labelMethod);
		int[] result = new int[2]; 
		for(Integer label : hitsMap.keySet()) {
			int hits = hitsMap.get(label);
			if(hits > result[1]) {
				result[0] = label;
				result[1] = hits;
			}
		}
		return result;
	}
	/**
	 * 
	 * @param set
	 * @param labelMethod
 	 * @return hitmap mapping from label to hits.s
	 */

	public static <E> Map<Integer, Integer> getHitsMap(Collection<E> set, ILabeler<E> labelMethod) {
		Map<Integer, Integer> hitsMap = new HashMap<Integer, Integer>();
		for (E e : set) {
			int label = labelMethod.getLabel(e);
			if (!hitsMap.containsKey(label))
				hitsMap.put(label, 1);
			else
				hitsMap.put(label, hitsMap.get(label) + 1);
		}
		return hitsMap;
	}
}
