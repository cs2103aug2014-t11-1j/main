package gui.controller.view;

import com.EventAndDone;
import com.ModelTask;

import gui.ResourceLoader;
import gui.controller.DateStringComparator;
import gui.controller.NumStringComparator;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

public class TableController{
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
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().getEventAndDoneProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().getDateStringProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeStringProperty());
		urgentColumn.setCellValueFactory(cellData -> cellData.getValue().getIsUrgentBooleanProperty());

		taskColumn.setCellFactory(new Callback<TableColumn<ModelTask, EventAndDone>, TableCell<ModelTask, EventAndDone>>(){
			@Override
			public TableCell<ModelTask, EventAndDone> call(TableColumn<ModelTask, EventAndDone> param){
				return new TableCell<ModelTask, EventAndDone>(){      
					Text text;
					{
						text = new Text("");
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
							text.setText("");
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
						imageview.setFitHeight(15);
						imageview.setFitWidth(15);

						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						setGraphic(imageview);
					}

					@Override
					public void updateItem(Boolean isUrgent, boolean empty) {
						super.updateItem(isUrgent, empty);
						if (!empty && isUrgent!=null && isUrgent.booleanValue()) {
							Image image = new Image(ResourceLoader.load("star.png"));
							imageview.setImage(image);
						}
						else {
							imageview.setImage(null);
						}
					}
				};
			}
		});

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

		System.out.println("setting all view");
		taskList.addListener(new ListChangeListener<ModelTask>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends ModelTask> c) {
				while (c.next()){
					if(c.wasAdded()){
						System.out.println("was added");
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

	protected void removeSelection() {
		taskTable.getSelectionModel().clearSelection();
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
	
	protected void scrollToAndSelectTask(ModelTask task){
		int index = taskList.indexOf(task);
		viewIndex = index;
		taskTable.scrollTo(index);
		taskTable.getSelectionModel().clearAndSelect(index);
	}

}
