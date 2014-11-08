package KNN;

import utils.interfaces.Labelabel;

public interface IMappable extends Labelabel {
	double getDistance(IMappable obj2);
}
