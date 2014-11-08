package com;

/**
 * @AUTHOR A0116018R
 * 
 * Unused: not fully developed
 * 
 * This class is the node needed to build the tree for
 * TentativeView. 
 * 
 * It stores StringProperties and String information
 * of what should be in the taskCol and positionCol
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TentativeNode {
	StringProperty taskCol_;
	StringProperty positionStringProperty_;
	
	String taskColString_;
	String position_;
	
	public TentativeNode(String taskCol, String position) {
		this.taskCol_ = new SimpleStringProperty();
		this.positionStringProperty_ = new SimpleStringProperty();
		
		setTaskCol(taskCol);
		setPosition(position);
	}

	public void setPosition(String position) {
		this.position_ = position;
		this.positionStringProperty_.set(position);
	}
	
	public void setTaskCol(String str){
		taskColString_ = str;
		taskCol_.set(str);
	}
	
	public StringProperty getTaskCol(){
		return taskCol_;
	}
	
	public StringProperty getPositionStringProperty(){
		return positionStringProperty_;
	}
	
	public String getTaskColString(){
		return taskColString_;
	}
	
	public String getPosition(){
		return position_;
	}

}
