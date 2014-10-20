package storage;

import javafx.beans.property.StringProperty;

public class ModelTentativeTask extends ModelTask{
	
	public ModelTentativeTask(String event, int position){
		super(event, null, null, null, null, position, false, false);
		dateStringProperty.set("Tentative");
		timeStringProperty.set("Tentative");
	}

}
