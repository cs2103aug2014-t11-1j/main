package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTask {
	private final StringProperty event;
	private final StringProperty dateStringProperty;
	private final StringProperty positionStringProperty;

	private int position;

	private final SimpleDateFormat dateFormatter;
	private Date date;

	public ModelTask(String event, Date date, int position){
		this.event = new SimpleStringProperty(event);

		dateFormatter = new SimpleDateFormat("EEE, MMM d");
		this.date = date;

		if(date != null){
			String dateString = dateFormatter.format(date);
			this.dateStringProperty =  new SimpleStringProperty(dateString);
		}
		else{
			this.dateStringProperty =  new SimpleStringProperty("");
		}
		this.position = position;
		String positionString = String.valueOf(position);
		this.positionStringProperty = new SimpleStringProperty(positionString);
	}

	public String getEvent(){
		return event.get();
	}

	public void setEvent(String event){
		this.event.set(event);
	}

	public StringProperty getEventProperty(){
		return event;
	}

	public Date getDate(){
		return date;
	}

	public void setDate(Date date){
		this.date = date;
		dateStringProperty.set(dateFormatter.format(date));
	}

	public StringProperty getDateStringProperty(){
		return dateStringProperty;
	}

	public int getPosition(){
		return position;
	}

	public void setPosition(int position){
		this.position = position;
		positionStringProperty.set(String.valueOf(position));
	}

	public StringProperty getPositionStringProperty(){
		return positionStringProperty;
	}
}
