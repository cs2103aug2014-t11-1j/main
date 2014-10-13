package gui.controller;

import javax.imageio.ImageIO;

import gui.MainApp;
import gui.ResourceLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import storage.EventAndDone;
import storage.ModelTask;

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
	private TableColumn<ModelTask, Boolean> doneColumn;
	
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
		doneColumn.setCellValueFactory(cellData -> cellData.getValue().getisDoneBooleanProperty());
		
		taskColumn.setCellFactory(new Callback<TableColumn<ModelTask, EventAndDone>, TableCell<ModelTask, EventAndDone>>(){
			  @Override
              public TableCell<ModelTask, EventAndDone> call(TableColumn<ModelTask, EventAndDone> param){
				  return new TableCell<ModelTask, EventAndDone>(){      
                      @Override
                      public void updateItem(EventAndDone eventAndDone, boolean empty) {
                          super.updateItem(eventAndDone, empty);
                          System.out.println("updating" + this.getIndex());
                          if (!empty && eventAndDone!=null && eventAndDone.getIsDone()) {
                        	  System.out.println(eventAndDone.getEvent());
                        	  setText(eventAndDone.getEvent());
                        	  setStyle("-fx-text-fill: green");
                          }
                          else if(!empty && eventAndDone!=null){
                        	  setText(eventAndDone.getEvent());
                        	  setStyle("-fx-text-fill: white");
                          }
                          else{
                        	  setText("");
                          }
                      }
				  };
			  }
		});
		
		doneColumn.setCellFactory(new Callback<TableColumn<ModelTask, Boolean>, TableCell<ModelTask, Boolean>>(){
			  @Override
              public TableCell<ModelTask, Boolean> call(TableColumn<ModelTask, Boolean> param){
				  return new TableCell<ModelTask, Boolean>(){
					  ImageView imageview;
                      {
                          imageview = new ImageView(); 
                          imageview.setFitHeight(15);
                          imageview.setFitWidth(15);
                          Image image = new Image(ResourceLoader.load("tick.png"));
                          
                          imageview.setImage(image);
                          setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                          setGraphic(imageview);
                      }
                      
                      @Override
                      public void updateItem(Boolean isDone, boolean empty) {
                          super.updateItem(isDone, empty);
                          if (!empty && isDone!=null && isDone.booleanValue()) {
                        	  Image image = new Image(ResourceLoader.load("tick.png"));
                              imageview.setImage(image);
                          }
                          else {
                              imageview.setImage(null);
                          }
                      }
				  };
			  }
		});
		
		numColumn.setComparator(new NumStringComparator());
		dateColumn.setComparator(new DateStringComparator());
		//taskColumn.setComparator(String.CASE_INSENSITIVE_ORDER);
		
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
