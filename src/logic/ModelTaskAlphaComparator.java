
package logic;

import java.util.Comparator;

import com.ModelTask;

/**
 *
 * @author Jireh
 */
public class ModelTaskAlphaComparator implements Comparator<ModelTask>{

    @Override
    public int compare(ModelTask task1, ModelTask task2) {
        String name1 = task1.getEvent();
        String name2 = task2.getEvent();
        
        return name1.compareToIgnoreCase(name2);
    }
    
}
