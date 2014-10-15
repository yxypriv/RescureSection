package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class GroupingUtil {
	public static <E> Map<Integer, ArrayList<E>> getLabelGroupingList(Collection<E> set, ILabeler<E> labeler) {
		Map<Integer, ArrayList<E>> result = new HashMap<Integer, ArrayList<E>>();
		for (E e : set) {
			int label = labeler.getLabel(e);
			if (!result.containsKey(label))
				result.put(label, new ArrayList<E>());
			result.get(label).add(e);
		}
		return result;
	}
}
