package gui.controller.FilteredListTextBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FilteredListTextBox<T> extends Control implements FilteredListTextBoxFactory<T> {
	
	private TextField textbox;
	
	private ListView<T> listview;
	
	private ObservableList<T> data = FXCollections.observableArrayList();
	
	private int limit;
	
	//Constructors
	public FilteredListTextBox(ObservableList<T> data) {
		init();
		this.data = data;
	}
	
	public FilteredListTextBox() {
		init();
	}
	
	//Initialisation
	private void init() {
		textbox = new TextField();
		listview = new ListView<T>();
		limit = 4;	
	}
	
	//commonly used textfield and listview methods
	public void requestFocus() {
		super.requestFocus();
		textbox.requestFocus();
	}
	
	public void setPromptText(String text){
		textbox.setPromptText(text);
	}
	
	public T getSelectedItem() {
		return listview.getSelectionModel().getSelectedItem();
	}
	
	public String getText() {
		return textbox.getText();
	}
	
	public void addData(T data) {
		this.data.add(data);
	}
	
	//overridden methods
	//From FilteredListBoxFactory
	@Override
	public void setData(ObservableList<T> data) {
		this.data = data;	
	}

	@Override
	public ObservableList<T> getData() {
		return data;
	}

	@Override
	public ListView<T> getListview() {
		return listview;
	}

	@Override
	public TextField getTextbox() {
		return textbox;
	}

	@Override
	public void setListLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public int getListLimit() {
		return limit;
	}
	
	//sizing the FilteredListTextBox
	@Override
	public void setMinSize(double minWidth, double minHeight) {
		super.setMinSize(minWidth, minHeight);
		textbox.setMinSize(minWidth, minHeight);
	}
	
	@Override
	public void setPrefSize(double prefWidth, double prefHeight) {
		super.setPrefSize(prefWidth, prefHeight);
		textbox.setPrefSize(prefWidth, prefHeight);
	}
	
	@Override
	public void setMaxSize(double maxWidth, double maxHeight) {
		super.setMaxSize(maxWidth, maxHeight);
		textbox.setMaxSize(maxWidth, maxHeight);
	}
}
