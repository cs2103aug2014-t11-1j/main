package storage;

import java.util.ArrayList;

import javafx.scene.control.TreeItem;

public class ModelTentativeTask extends ModelTask{
	
    final static boolean isTentative = true;

	private ArrayList<String> dates;
	private ArrayList<TimePeriod> blockedTimePeriods;
	    
    private TreeItem<TentativeNode> node;
	
	public ModelTentativeTask(String event, int position){
		super(event, null, null, null, null, position, false, false);
		dateStringProperty.set("Tentative");
		timeStringProperty.set("Tentative");
		
		dates = new ArrayList<String>();
		blockedTimePeriods = new ArrayList<TimePeriod>();
		
		node = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
        node.setExpanded(true);
	}
	
	@Override
	public ModelTask copyTask(){
		ModelTentativeTask temp = new ModelTentativeTask(getEvent(), position);
		temp.setBlockedTimePeriods(blockedTimePeriods);
		return temp;
	}
	
	//mutators
	@Override
	public void setEvent(String event){
		super.setEvent(event);
		node.getValue().setPosition(event);
	}
	
	@Override 
	public void setPosition(int position){
		super.setPosition(position);
		node.getValue().setPosition(String.valueOf(position));
	}
	
	public void addDate(String date){
		dates.add(date);
		setChild(date);
	}
	
	public void setDates(ArrayList<String> dates){
		this.dates = dates;
		setChildren();
	}
	
	public void setBlockedTimePeriods(ArrayList<TimePeriod> timePeriods){
		blockedTimePeriods = timePeriods;
	}
	
	//accessors
	public ArrayList<String> getDates(){
		return dates;
	}
	
	public ArrayList<TimePeriod> getBlockedTimePeriods(){
		return blockedTimePeriods;
	}
	
	public TreeItem<TentativeNode> getNode(){
		return node;
	}
	
	//private methods
	private void setChildren(){
		for(String date : dates){
			setChild(date);
		}
	}
	
	private void setChild(String date){
		TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
		node.getChildren().add(leaf);
		node.setExpanded(true);
	}

}
