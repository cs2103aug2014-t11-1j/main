package storage;

import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Jireh and Krystal
 */
public class TentativeTask {
	
	//used by Logic to check for clashes
    private static ArrayList<TimePeriod> globalBlockedTimePeriods;
    private ArrayList<TimePeriod> blockedTimePeriods;
    
    private String event;
    private int position;
  
    public TentativeTask() {
        blockedTimePeriods = new ArrayList<TimePeriod>();
		        
        setEvent(event);
        setPosition(position);

    }
	
	//Mutators
	public void setEvent(String event){
		this.event = event;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}



    public boolean blockTimePeriod(TimePeriod tp) {
        for (TimePeriod period : globalBlockedTimePeriods) {
            if (!isValidTimePeriod(tp, period)) {
                return false;
            }
        }
        globalBlockedTimePeriods.add(tp);
        blockedTimePeriods.add(tp);
        return true;
    }
    
    //Accessors
    public static ArrayList<TimePeriod> getGlobalBlockedTimePeriods() {
        return globalBlockedTimePeriods;
    }

    public ArrayList<TimePeriod> getBlockedTimePeriods() {
        return blockedTimePeriods;
    }
    
	public String getEvent(){
		return event;
	}
	
	public int getPosition(){
		return position;
	}
	
	//private methods
    private static boolean isValidTimePeriod(TimePeriod tp, TimePeriod toCompare) {
        Date start = tp.getStartDate();
        Date end = tp.getEndDate();
        Date compareStart = toCompare.getStartDate();
        Date compareEnd = toCompare.getEndDate();

        boolean isStartBeforeEnd = start.before(end);
        boolean isStartValid = !(start.after(compareStart) && start.before(compareEnd));
        boolean isEndValid = !(end.after(compareStart) && end.before(compareEnd));

        return isStartBeforeEnd && isStartValid && isEndValid;
    }

}
