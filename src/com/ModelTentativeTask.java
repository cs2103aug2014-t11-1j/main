package com;

/**
 * @author A0116018R
 * Unused: not fully developed
 * 
 * This class extends ModelTask and includes tentative dates.
 * Ths class stores tentative dates for the GUI.
 */
import java.util.ArrayList;

import javafx.scene.control.TreeItem;

public class ModelTentativeTask extends ModelTask{
	
    private static final String TENTATIVE = "Tentative";

	final static boolean IS_TENTATIVE = true;

	private ArrayList<String> dates_;
	private ArrayList<TimePeriod> blockedTimePeriods_;
	    
    private TreeItem<TentativeNode> node_;
	
	public ModelTentativeTask(String event, int position){
		super(event, null, null, null, null, position, false, false);
		dateStringProperty_.set(TENTATIVE);
		timeStringProperty_.set(TENTATIVE);
		
		dates_ = new ArrayList<String>();
		blockedTimePeriods_ = new ArrayList<TimePeriod>();
		
		node_ = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
        node_.setExpanded(true);
	}
	
	@Override
	public ModelTask copyTask(){
		ModelTentativeTask temp = new ModelTentativeTask(getEvent(), position_);
		temp.setBlockedTimePeriods(blockedTimePeriods_);
		return temp;
	}
	
	//mutators
	@Override
	public void setEvent(String event){
		super.setEvent(event);
		node_.getValue().setPosition(event);
	}
	
	@Override 
	public void setPosition(int position){
		super.setPosition(position);
		node_.getValue().setPosition(String.valueOf(position));
	}
	
	public void addDate(String date){
		dates_.add(date);
		setChild(date);
	}
	
	public void setDates(ArrayList<String> dates){
		this.dates_ = dates;
		setChildren();
	}
	
	public void setBlockedTimePeriods(ArrayList<TimePeriod> timePeriods){
		blockedTimePeriods_ = timePeriods;
	}
	
	//accessors
	public ArrayList<String> getDates(){
		return dates_;
	}
	
	public ArrayList<TimePeriod> getBlockedTimePeriods(){
		return blockedTimePeriods_;
	}
	
	public TreeItem<TentativeNode> getNode(){
		return node_;
	}
	
	//private methods
	private void setChildren(){
		for(String date : dates_){
			setChild(date);
		}
	}
	
	private void setChild(String date){
		TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
		node_.getChildren().add(leaf);
		node_.setExpanded(true);
	}

}
