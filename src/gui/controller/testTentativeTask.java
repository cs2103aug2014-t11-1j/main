package gui.controller;

/**
 * @author A0116018R
 * This is a dummy class for testing the tentativeView.
 */
import java.util.ArrayList;

import com.TentativeNode;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

public class testTentativeTask {
	private String event_;
	private int position_;
	
	private ArrayList<String> dates_;
	
	private TreeItem<TentativeNode> node_;
	
	public testTentativeTask(String event, String date1, String date2, int position){
		dates_ = new ArrayList<String>();
		node_ = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
		node_.setExpanded(true);
		setEvent(event);
		setPosition(position);
		dates_.add(date1);
		dates_.add(date2);
		setChildren();
	}

	public void setPosition(int position) {
		position_ = position;
		node_.getValue().setPosition(String.valueOf(position));
	}
	
	public void setEvent(String event){
		event_ = event;
		node_.getValue().setPosition(event);
	}
	
	public void addDate(String date){
		dates_.add(date);
		setChild(date);
	}
	
	public String getEvent(){
		return event_;
	}
	
	public ArrayList<String> getDates(){
		return dates_;
	}
	
	public int getPosition(){
		return position_;
	}
	
	private void setChildren(){
		for(String date : dates_){
			TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
			node_.getChildren().add(leaf);
			node_.setExpanded(true);
		}
	}
	
	private void setChild(String date){
		TreeItem<TentativeNode> leaf = new TreeItem<TentativeNode>(new TentativeNode(date, null));
		node_.getChildren().add(leaf);
		node_.setExpanded(true);
	}

	public TreeItem<TentativeNode> getTentativeNode(){
		return node_;
	}
}
