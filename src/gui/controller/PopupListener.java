package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class PopupListener implements ChangeListener<String>{
	private TextField commandLine;
	
	private Popup popup;
	
	private ListView<String> listView;
	
	private ObservableList<String> data;
	
	public PopupListener(TextField commandLine){
		this.commandLine = commandLine;
	}
	
	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		// TODO Auto-generated method stub
		
	}

}
