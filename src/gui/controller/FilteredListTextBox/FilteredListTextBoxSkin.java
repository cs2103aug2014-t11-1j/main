package gui.controller.FilteredListTextBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.stage.Popup;

public class FilteredListTextBoxSkin<T> extends SkinBase<FilteredListTextBox<T>>
		implements ChangeListener<String>, 
		EventHandler{
	
	private ListView<T> listview;
	
	private TextField textbox;
	
	private FilteredListTextBox<T> filteredListTextbox;
	
	private ObservableList<T> data;
	
	private Popup popup;
	
	public FilteredListTextBoxSkin(FilteredListTextBox<T> control) {
		super(control);

		filteredListTextbox = control;
		
		data = control.getData();
		
		initListView(control);
		
		initTextBox(control);
		
		initPopup();
	}

	private void initListView(FilteredListTextBox<T> control) {
		listview = control.getListview();
		listview.setItems(data);
	}
	
	private void initTextBox(FilteredListTextBox<T> control) {
		textbox = control.getTextbox();
		textbox.setOnKeyPressed(this);
		textbox.textProperty().addListener(this);
		
		textbox.focusedProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue,
					Object newValue) {
				textbox.end();
				System.out.println(newValue.toString());
			}
			
		});
		
		getChildren().addAll(textbox);
	}
	

	private void initPopup() {
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(listview);
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		// TODO Auto-generated method stub
		
	}

}
