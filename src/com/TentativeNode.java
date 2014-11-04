package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TentativeNode {
	StringProperty taskCol;
	StringProperty positionStringProperty;
	
	String taskColString;
	String position;
	
	public TentativeNode(String taskCol, String position) {
		this.taskCol = new SimpleStringProperty();
		this.positionStringProperty = new SimpleStringProperty();
		
		setTaskCol(taskCol);
		setPosition(position);
	}

	public void setPosition(String position) {
		this.position = position;
		this.positionStringProperty.set(position);
	}
	
	public void setTaskCol(String str){
		taskColString = str;
		taskCol.set(str);
	}
	
	public StringProperty getTaskCol(){
		return taskCol;
	}
	
	public StringProperty getPositionStringProperty(){
		return positionStringProperty;
	}
	
	public String getTaskColString(){
		return taskColString;
	}
	
	public String getPosition(){
		return position;
	}

}
