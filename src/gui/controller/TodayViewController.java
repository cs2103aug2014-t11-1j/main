package gui.controller;

import storage.ModelTask;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TodayViewController {
	@FXML
	private TableView<ModelTask> todayTable;
	@FXML
	private TableColumn<ModelTask, String> taskColumn;
	@FXML
	private TableColumn<ModelTask, String> timeColumn;
	@FXML
	private ObservableList<ModelTask> todayList;
	
	public TodayViewController(){
		System.out.println("today view constructor");
	}
	
	@FXML
	private void initialize(){
		System.out.println("today view initilising");
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getDateStringProperty());
	}
	
	protected void setTodayView(ObservableList<ModelTask> list){
		todayList = list;
		todayTable.setItems(todayList);
	}
	
}