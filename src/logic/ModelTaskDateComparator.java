package logic;

/**
 * @author: zhang yongkai
 * 
 * this class compares 2 date object. if one of the date is null, the one that is not null is bigger.
 */


import java.util.Comparator;
import java.util.Date;

import com.ModelTask;

public class ModelTaskDateComparator implements Comparator<ModelTask> {

	private static final int SMALLER = -100;
	private static final int BIGGER = 100;

	@Override
	public int compare(ModelTask task1, ModelTask task2) {
		Date date1, date2;

		if (task1.getDeadLine() != null && task2.getDeadLine() != null) {
			date1 = task1.getDeadLine();
			date2 = task2.getDeadLine();
			return date1.compareTo(date2);
		} else if (task1.getDeadLine() != null) {
			return SMALLER;
		} else if (task2.getDeadLine() != null) {
			return BIGGER;
		}
		return 0;
	}

}
