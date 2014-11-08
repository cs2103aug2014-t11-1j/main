package gui.controller.view;
/**
 * @author A0116018R
 * 
 * This is the controller for TableView
 * Handling of table selection and scrolling 
 * is done here. 
 */
import gui.ResourceLoader;

import java.util.logging.Level;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;

import com.EventAndDone;
import com.ModelTask;
import com.util.MyLogger;

public class TableController{
	private static final String EMPTY_STRING = "";
	@FXML
	private TableView<ModelTask> taskTable;
	@FXML
	private TableColumn<ModelTask, EventAndDone> taskColumn;
	@FXML
	private TableColumn<ModelTask, String> dateColumn;
	@FXML
	private TableColumn<ModelTask, String> numColumn;
	@FXML
	private TableColumn<ModelTask, String> timeColumn;
	@FXML
	private TableColumn<ModelTask, Boolean> urgentColumn;

	private boolean isSearched_;

	private ObservableList<ModelTask> taskList_;

	private ObservableList<ModelTask> searchedList_;

	private int viewIndex_;

	private static int INITIAL_VIEW_INDEX = 0;
	private static int NEXT_NUMBER_OF_ROWS = 8;

	public TableController(){
	}

	@FXML
	private void initialize() {
		numColumn.setCellValueFactory(cellData -> cellData.getValue().getPositionStringProperty());
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventAndDoneProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateStringProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeStringProperty());
		urgentColumn.setCellValueFactory(cellData -> cellData.getValue().getIsUrgentBooleanProperty());
		
		//special cell factories for these columns
		taskColumn.setCellFactory(new Callback<TableColumn<ModelTask, EventAndDone>, TableCell<ModelTask, EventAndDone>>(){
			@Override
			public TableCell<ModelTask, EventAndDone> call(TableColumn<ModelTask, EventAndDone> param){
				return new TableCell<ModelTask, EventAndDone>(){      
					Text text;
					{
						text = new Text(EMPTY_STRING);
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						setGraphic(text);
						text.setFill(Color.WHITE);
					}
					@Override
					public void updateItem(EventAndDone eventAndDone, boolean empty) {
						super.updateItem(eventAndDone, empty);

						if (!empty && eventAndDone!=null && eventAndDone.getIsDone()) {
							text.setText(eventAndDone.getEvent());
							text.setStrikethrough(true);
						}
						else if(!empty && eventAndDone!=null){
							text.setText(eventAndDone.getEvent());
							text.setStrikethrough(false);                          
						}
						else{
							text.setText(EMPTY_STRING);
						}
					}
				};
			}
		});

		urgentColumn.setCellFactory(new Callback<TableColumn<ModelTask, Boolean>, TableCell<ModelTask, Boolean>>(){
			@Override
			public TableCell<ModelTask, Boolean> call(TableColumn<ModelTask, Boolean> param){
				return new TableCell<ModelTask, Boolean>(){
					ImageView imageview;
					{
						imageview = new ImageView(); 
						imageview.setPreserveRatio(true);
						imageview.setFitWidth(17);
						
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						setGraphic(imageview);
					}

					@Override
					public void updateItem(Boolean isUrgent, boolean empty) {
						super.updateItem(isUrgent, empty);
						if (!empty && isUrgent!=null && isUrgent.booleanValue()) {
							Image image = new Image(ResourceLoader.load("star.png"));
							imageview.setImage(image);
							setAlignment(Pos.CENTER);
						}
						else {
							imageview.setImage(null);
						}
					}
				};
			}
		});
		
		
		taskTable.setPlaceholder(new Label(EMPTY_STRING));

		isSearched_ = false;

		viewIndex_ = INITIAL_VIEW_INDEX;

	}
	
	protected boolean isSearched(){
		return isSearched_;
	}
	
	//methods for external classes to change list view in table
	protected void setAllView(ObservableList<ModelTask> list){
		taskList_ = list;
		taskTable.setItems(taskList_);
		viewIndex_ = INITIAL_VIEW_INDEX;
		isSearched_ = false;
		
		//select last used task
		taskList_.addListener(new ListChangeListener<ModelTask>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends ModelTask> c) {
				while (c.next()){
					if(c.wasAdded()){
						if(c.getAddedSize() == 1){
							scrollToAndSelectTask(c.getAddedSubList().get(0));
						}
					}else if(c.wasRemoved()){
						removeSelection();
					}
				}
			}
		});
		
	}

	protected void switchToSearch(ObservableList<ModelTask> list) {
		searchedList_ = list;
		taskTable.setItems(searchedList_);
		viewIndex_ = INITIAL_VIEW_INDEX;
		isSearched_ = true;
	}

	protected void switchToAll() {
		taskTable.setItems(taskList_);
		viewIndex_ = INITIAL_VIEW_INDEX;
		isSearched_ = false;
	}
	
	//methods for scrolling
	protected void scrollToNext() {
		viewIndex_ += NEXT_NUMBER_OF_ROWS;
		if(viewIndex_ > taskList_.size()){
			viewIndex_ -= NEXT_NUMBER_OF_ROWS;
		}
		taskTable.scrollTo(viewIndex_);		
	}

	protected void scrollToTop(){
		viewIndex_ = INITIAL_VIEW_INDEX;
		taskTable.scrollTo(viewIndex_);
	}

	protected void scrollToBack(){
		viewIndex_ -= NEXT_NUMBER_OF_ROWS;
		if(viewIndex_ < INITIAL_VIEW_INDEX){
			viewIndex_ = INITIAL_VIEW_INDEX;
		}
		taskTable.scrollTo(viewIndex_);
	}
	
	protected void scrollToAndSelectTask(ModelTask task){
		int index = taskList_.indexOf(task);
		scrollToAndSelect(index);
	}
	
	protected void scrollToAndSelect(int index) {
		viewIndex_ = index;
		taskTable.scrollTo(index);
		try{
			taskTable.getSelectionModel().clearAndSelect(index);
		}catch(Exception e){
			System.out.println("error with clear and select");
			MyLogger.log(Level.WARNING, "error with clear and select");
		}
	}

	private void removeSelection() {
		taskTable.getSelectionModel().clearSelection();
	}

}
