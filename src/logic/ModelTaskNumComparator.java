package logic;

import java.util.Comparator;

import com.ModelTask;

/**
 * Comparator to allow comparing tasks numerically by their positions.
 *
 * @author Jireh
 */
public class ModelTaskNumComparator implements Comparator<ModelTask> {

    @Override
    public int compare(ModelTask task1, ModelTask task2) {
        int position1 = task1.getPosition();
        int position2 = task2.getPosition();

        if (position1 == position2) {
            return 0;
        } else {
            return position1 > position2 ? 1 : -1;
        }
    }

}
