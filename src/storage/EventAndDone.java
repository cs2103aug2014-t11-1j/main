package storage;

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
		System.out.println("event class isDone");
	}
	
	public String getEvent(){
		return event;
	}
	
	public boolean getIsDone(){
		return isDone;
	}
}
