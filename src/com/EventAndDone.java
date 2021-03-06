package com;
/**
 * @author A0116018R
 * 
 *	This class wraps a String event and a boolean isDone.
 *	For use in ModelTask.
 */
public class EventAndDone {
	
	private String event;
	private boolean isDone;
	
	public EventAndDone(String event, boolean isDone){
		this.event = event;
		this.isDone = isDone;
	}
	
	public void setEvent(String event){
		this.event = event;
	}
	
	public void setIsDone(boolean isDone){
		this.isDone = isDone;
	}
	
	public String getEvent(){
		return event;
	}
	
	public boolean getIsDone(){
		return isDone;
	}
}
