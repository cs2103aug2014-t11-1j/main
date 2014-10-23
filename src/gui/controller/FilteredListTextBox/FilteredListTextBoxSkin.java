package gui.controller.FilteredListTextBox;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Callback;

public class FilteredListTextBoxSkin<T> extends SkinBase<FilteredListTextBox<T>>
implements ChangeListener<String>, 
EventHandler{

	private ListView listview;

	private TextField textbox;

	private FilteredListTextBox filteredListTextbox;

	private ObservableList data;

	private Popup popup;

	private String temporaryTxt = "";

	public FilteredListTextBoxSkin(FilteredListTextBox<T> control) {
		super(control);

		filteredListTextbox = control;

		data = control.getData();

		FXCollections.sort(data);

		initListView(control);

		initTextBox(control);

		initPopup();
	}

	public void selectList() {
		Object i = listview.getSelectionModel().getSelectedItem();
		if (i != null) {
			textbox.setText(i.toString());
			listview.getItems().clear();
			textbox.requestFocus();
			textbox.requestLayout();
			textbox.end();
			temporaryTxt = "";
			hidePopup();
		}
	}
	
    public Window getWindow() {
        return filteredListTextbox.getScene().getWindow();
    }

	@Override
	public void handle(Event event) {
		if(event.getSource() == textbox) {
			handleTextboxEvent(event);
		}else if(event.getSource() == listview){
			handleListviewEvent(event);
		}
	}

	private void handleTextboxEvent(Event event) {
		if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode() == KeyCode.DOWN) {
				if(popup.isShowing()) {
					listview.requestFocus();
					listview.getSelectionModel().select(0);
				}
			}
		}
	}
	
	private void handleListviewEvent(Event event) {
		KeyEvent keyEvent = (KeyEvent)event;
		if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			if(keyEvent.getCode() == KeyCode.ENTER) {
				selectList();
			}else if(keyEvent.getCode() == KeyCode.UP){
				if(listview.getSelectionModel().getSelectedIndex() == 0) {
					textbox.requestFocus();
				}
			}
		}else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			selectList();
		}
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		// TODO Auto-generated method stub

	}
	
	private void initListView(FilteredListTextBox<T> control) {
		listview = control.getListview();
		listview.setItems(data);

		listview.itemsProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue,
					Object newValue) {
				if(listview.getItems().size() > 0 && listview.getItems() != null) {
					showPopup();
				}else{
					hidePopup();
				}
				System.out.println(newValue.toString());
			}	
		});

		listview.setOnMouseReleased(this);
		listview.setOnKeyReleased(this);

		listview.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> param) {
				ListCell cell = new ListCell() {
					@Override
					public void updateItem(Object item, boolean empty){
						super.updateItem(item, empty);
						if(item != null) {
							setText(item.toString());
						}
					}

				};
				cell.focusedProperty().addListener(new InvalidationListener() {
					@Override
					public void invalidated(Observable observable) {
						if(cell.getItem() != null && cell.isFocused()) {
							String prev;							
							if(temporaryTxt.length() <= 0){
								if(listview.getItems().size() != data.size()){
									temporaryTxt = textbox.getText();
								}
							}
							prev = temporaryTxt;
							textbox.setText(cell.getItem().toString());
							textbox.selectRange(prev.length(), cell.getItem().toString().length());
						}

					}
				});				
				return cell;
			}     	
		});
	}

	private void initTextBox(FilteredListTextBox<T> control) {
		textbox = control.getTextbox();
		textbox.setOnKeyPressed(this);
		textbox.textProperty().addListener(this);

		textbox.focusedProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue observable, Object oldValue,
					Object newValue) {
				textbox.end();
				System.out.println(newValue.toString());
			}

		});

		getChildren().addAll(textbox);
	}


	private void initPopup() {
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(listview);
	}

	private void showPopup() {
		listview.setPrefWidth(textbox.getWidth());
		if (listview.getItems().size() > 6) {
			listview.setPrefHeight((6 * 24));
		} else {
			listview.setPrefHeight((listview.getItems().size() * 24));
		}

		popup.show(
				getWindow(),
				getWindow().getX() + textbox.localToScene(0, 0).getX() + textbox.getScene().getX(),
				getWindow().getY() + textbox.localToScene(0, 0).getY() + textbox.getScene().getY());

		listview.getSelectionModel().clearSelection();
		listview.getFocusModel().focus(-1);
	}

	private void hidePopup() {
		popup.hide();
	}

}
