package gui.controller.FilteredListTextBox;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public interface FilteredListTextBoxFactory<T> {
	
	/**
	 * set the arraylist of strings for to be matched 
	 * while user is typing
	 * @param data
	 */
	
	void setData(ObservableList<T> data);
	
	/**
	 * returns the data containing strings to be matched
	 * @return JavaFX's observable list
	 */
	ObservableList<T> getData();
	
	/**
	 * the main listview for the FilteredListTextBox
	 * @return
	 */
	ListView<T> getListview();
	
	/**
	 * the textbox of the FilteredListTextBox
	 * @return
	 */
	TextField getTextbox();
	
	/**
	 * Sets the max matched items to be displayed in listview
	 * @param limit
	 */
	void setListLimit(int limit);
	
	/**
	 * the current limit of matched items to be displayed
	 * @return int
	 */
	int getListLimit();
}
