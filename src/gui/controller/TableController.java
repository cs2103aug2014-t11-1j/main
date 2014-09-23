package gui.controller;

import java.util.Date;

import javafx.collections.FXCollections;
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
	private ObservableList<ModelTask> taskList = FXCollections.observableArrayList();
	
	@FXML
	private ObservableList<ModelTask> searchedList = FXCollections.observableArrayList();
	
	
	
	public TableController(){
	}
	
	@FXML
	private void initialize() {
		taskList.add(new ModelTask("Test Task", new Date(), 1));
		taskTable.setItems(taskList);
		numColumn.setCellValueFactory(cellData -> cellData.getValue().getPositionStringProperty());
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateStringProperty());
		
		numColumn.setComparator(new NumStringComparator());
		dateColumn.setComparator(new DateStringComparator());
		taskColumn.setComparator(String.CASE_INSENSITIVE_ORDER);
	}
	
	
	protected void switchToSearch() {
		searchedList.add(new ModelTask("Searched Task", new Date(), 1));
		taskTable.setItems(searchedList);
	}

	protected void switchToNormal() {
		taskTable.setItems(taskList);
	}

}
