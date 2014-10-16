package gui.controller;

import java.util.ArrayList;

import storage.TentativeNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TreeItem;

public class testTentativeTask {
	private String event;
	private int position;
	
	private ArrayList<String> dates;
	
	private TreeItem<TentativeNode> node;
	
	public testTentativeTask(String event, String date1, String date2, int position){
		dates = new ArrayList<String>();
		node = new TreeItem<TentativeNode>(new TentativeNode(event, String.valueOf(position)));
		node.setExpanded(true);
		setEvent(event);
		setPosition(position);
		dates.add(date1);
		dates.add(date2);
		setChildren();
	}

	public void setPosition(int position) {
		this.position = position;
		node.getValue().setPosition(String.valueOf(position));
	}
	
	public void setEvent(String event){
		this.event = event;
		node.getValue().setPosition(event);
	}
	
	public void addDate(String date){
		dates.add(date);
		setChild(date);
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

	public TreeItem<TentativeNode> getTentativeNode(){
		return node;
	}
}
