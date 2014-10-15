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
    
    //used by GUI to create tree
    private String event;
    private int position;
	private ArrayList<String> dates;
    
    private TreeItem<TentativeNode> node;
  
    public TentativeTask(String event, int position) {
        blockedTimePeriods = new ArrayList<TimePeriod>();
		dates = new ArrayList<String>();
		
		//set dates?
		
        node = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
        node.setExpanded(true);
        
        setEvent(event);
        setPosition(position);
        //setChildren();
    }
	
	//Mutators
	public void setEvent(String event){
		this.event = event;
		node.getValue().setPosition(event);
	}
	
	public void setPosition(int position) {
		this.position = position;
		node.getValue().setPosition(String.valueOf(position));
	}

	public void addDate(String date){
		dates.add(date);
		setChild(date);
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
	
	public ArrayList<String> getDates(){
		return dates;
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

	private void setChildren(){
		for(String date : dates){
			TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
			node.getChildren().add(leaf);
			node.setExpanded(true);
		}
	}
	
	private void setChild(String date){
		TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
		node.getChildren().add(leaf);
		node.setExpanded(true);
	}
}
