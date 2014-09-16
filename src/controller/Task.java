package controller;

public class Task {
	private final int INDEX_OF_EVENT = 0;
	private final int INDEX_OF_DATE = 1;
	private final int INDEX_OF_START = 2;
	private final int INDEX_OF_END = 3;
	private final int INDEX_OF_ISDONE = 4;
	
	private String event;
	private String start = null;
	private String end = null;
	private String date = null;
	private boolean isDone = false;
	
	
	public Task(String str){
		event = new String();
		
		if(str.contains("[") && str.contains("]")){
			
			String input = str.substring(0, str.indexOf('['));
			if(!input.endsWith(" ")){
				input = input + " ";
			}
			setEvent(input);
			
			String date = str.substring(str.indexOf('[') + 1,str.indexOf(']'));
			setDate(date);
			
			String newstr = str.substring(str.indexOf(']') + 1);
			
			if(newstr.contains("[") && newstr.contains("]")){
				
				String start = newstr.substring(newstr.indexOf('[') + 1, newstr.indexOf(']'));
				setStart(start);
				newstr = newstr.substring(newstr.indexOf(']') + 1);

				if(newstr.contains("[")){
					
					String end = newstr.substring(newstr.indexOf('['));
					setEnd(end);
				}
				
			}

		}
		else{
			setEvent(str);
		}
		this.isDone = false;
	}
	
	public Task(String str, boolean fromSave){
		String[] array = str.split(";");
		
		String input;
		this.event = array[INDEX_OF_EVENT];
		
		input = array[INDEX_OF_DATE];
		if(input.equals("null")){
			this.date = null;
		}
		else{
			this.date = input;
		}
		input = array[INDEX_OF_START];
		if(input.equals("null")){
			this.start = null;
		}
		else{
			this.start = input;
		}
		input = array[INDEX_OF_END];
		if(input.equals("null")){
			this.end = null;
		}
		else{
			this.end = input;
		}
		
		input = array[INDEX_OF_ISDONE];
		if(input.equalsIgnoreCase("true")){
			this.isDone = true;
		}
		else{
			this.isDone = false;
		}

	}
	
	public void setEvent(String e){
		event = e;
	}
	
	public void setStart(String s){
		start = s;
	}
	
	public void setEnd(String endt){
		end = endt;
	}
	
	public void setDate(String d){
		date = d;
	}
	
	public void setDone(boolean b){
		isDone = b;
	}
	
	public String getEvent(){
		return event;
	}
	
	
	public String getDate(){
		return date;
	}
	
	public boolean getIsDone(){
		return isDone;
	}
	
	public String toString(){
		if(start == null && end == null && date == null)
			return event;
		else if(start == null && end == null)
			return event + "by " + date;
		else if(end == null)
			return event + "by " + date + " from " + start;
		else
			return event + "by "+ date + " from " + start + " to " + end;
	}
	
	public String toSave(){
		String saveString = event + ";" + date + ";" + start + ";" + end + ";" + isDone;
		return saveString;
	}
}
