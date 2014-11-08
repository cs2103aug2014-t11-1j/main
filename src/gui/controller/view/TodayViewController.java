package gui.controller.view;
/**
 * @author A0116018R
 * 
 * This class is the controller for todayview.
 */
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.ModelTask;

public class TodayViewController {
	private static final String EMPTY_STRING = "";
	@FXML
	private TableView<ModelTask> todayTable;
	@FXML
	private TableColumn<ModelTask, String> taskColumn;
	@FXML
	private TableColumn<ModelTask, String> timeColumn;

	private ObservableList<ModelTask> todayList_;
	
	public TodayViewController(){
	}
	
	@FXML
	private void initialize(){
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeStringProperty());

		todayTable.setPlaceholder(new Label(EMPTY_STRING));
	}
	
	protected void setTodayView(ObservableList<ModelTask> list){
		todayList_ = list;
		todayTable.setItems(todayList_);
	}
	
}
