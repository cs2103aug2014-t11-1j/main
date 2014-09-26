package gui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
	
	@FXML
	private ObservableList<ModelTask> taskList;
	
	@FXML
	private ObservableList<ModelTask> searchedList;
	
	
	
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
	}
	
	protected void setAllView(ObservableList<ModelTask> list){
		taskList = list;
		taskTable.setItems(taskList);
	}
	
	protected void switchToSearch(ObservableList<ModelTask> list) {
		searchedList = list;
		taskTable.setItems(searchedList);
	}

	protected void switchToAll() {
		taskTable.setItems(taskList);
	}

}
