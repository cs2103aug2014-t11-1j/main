package gui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import storage.ModelTask;

public class TableController{
	@FXML
	private TableView<ModelTask> taskTable;
	@FXML
	private TableColumn<ModelTask, String> taskColumn;
	@FXML
	private TableColumn<ModelTask, String> dateColumn;
	@FXML
	private TableColumn<ModelTask, String> numColumn;
	@FXML
	private TableColumn<ModelTask, String> timeColumn;
	
	private boolean isSearched;
	
	@FXML
	private ObservableList<ModelTask> taskList;
	
	@FXML
	private ObservableList<ModelTask> searchedList;
	
	private int viewIndex;
	
	private static int INITIAL_VIEW_INDEX = 0;
	private static int NEXT_NUMBER_OF_ROWS = 8;
	
	public TableController(){
		System.out.println("table constructor");
	}
	
	@FXML
	private void initialize() {
		System.out.println("table initilising");
		numColumn.setCellValueFactory(cellData -> cellData.getValue().getPositionStringProperty());
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateStringProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeStringProperty());
		
		numColumn.setComparator(new NumStringComparator());
		dateColumn.setComparator(new DateStringComparator());
		taskColumn.setComparator(String.CASE_INSENSITIVE_ORDER);
		
		taskTable.setPlaceholder(new Label(""));
		
		isSearched = false;
		
		viewIndex = INITIAL_VIEW_INDEX;
	}
	
	protected boolean isSearched(){
		return isSearched;
	}
	
	protected void setAllView(ObservableList<ModelTask> list){
		taskList = list;
		taskTable.setItems(taskList);
		viewIndex = INITIAL_VIEW_INDEX;
		isSearched = false;
	}
	
	protected void switchToSearch(ObservableList<ModelTask> list) {
		searchedList = list;
		taskTable.setItems(searchedList);
		viewIndex = INITIAL_VIEW_INDEX;
		isSearched = true;
	}

	protected void switchToAll() {
		taskTable.setItems(taskList);
		viewIndex = INITIAL_VIEW_INDEX;
		isSearched = false;
	}

	protected void scrollToNext() {
		viewIndex += NEXT_NUMBER_OF_ROWS;
		if(viewIndex > taskList.size()){
			viewIndex -= NEXT_NUMBER_OF_ROWS;
		}
		taskTable.scrollTo(viewIndex);		
	}
	
	protected void scrollToTop(){
		viewIndex = INITIAL_VIEW_INDEX;
		taskTable.scrollTo(viewIndex);
	}
	
	protected void scrollToBack(){
		viewIndex -= NEXT_NUMBER_OF_ROWS;
		if(viewIndex < INITIAL_VIEW_INDEX){
			viewIndex = INITIAL_VIEW_INDEX;
		}
		taskTable.scrollTo(viewIndex);
	}

}
