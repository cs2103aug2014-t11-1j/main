package gui;

/**
 *  @author A0116018R
 * The MainApp loads and initialises the application
 * including the main overallView and PhantomController
 * on startup.
 *  
 */
import java.io.IOException;

import gui.controller.PreferenceManager;
import gui.controller.Reminder;
import gui.controller.view.PhantomController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainApp extends Application {

	private Stage primaryStage_;
	private AnchorPane overallView_;
	private TrayApplication ta_;
	private ShortcutManager sm_;

	private double xOffset_ = 0;
	private double yOffset_ = 0;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage_ = primaryStage;
		this.primaryStage_.setTitle("Phantom");
		this.primaryStage_.centerOnScreen();
		this.primaryStage_.initStyle(StageStyle.TRANSPARENT);
		
		//@author A0116211B
		ta_ = TrayApplication.getInstance();
		try {
			ta_.createTrayIcon(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sm_ = ShortcutManager.getInstance();
		Platform.setImplicitExit(false);
		sm_.setHotKeys(primaryStage);
		
		//@author A0116018R
		showPhantomOverallView();
	}
	
	public void showPhantomOverallView() {
		try {
			// Load overallView.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("controller/view/OverallView.fxml"));
			overallView_ = (AnchorPane) loader.load();

			Scene scene = new Scene(overallView_);
			primaryStage_.setScene(scene);
			primaryStage_.setResizable(false);
			primaryStage_.show();

			PhantomController controller = loader.getController();
			controller.setPrimaryStage(primaryStage_);
			controller.setOverallView(overallView_);
			controller.initPrefManager();
			
			//@author A0116211B
			/**
			 * This snippet of code allows 
			 * the user to remotely execute undo
			 * commands when CTRL+Z is pressed - smallson
			 */
			final KeyCombination keyCombUndo = new KeyCodeCombination(KeyCode.Z,
					KeyCombination.CONTROL_DOWN);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (keyCombUndo.match(event)) {
						controller.executeCommand("UNDO");

					}
				}
			});
			
			/**
			 * This snippet of code allows 
			 * the user to remotely execute redo
			 * commands when CTRL+R is pressed - smallson
			 */
			final KeyCombination keyCombRedo = new KeyCodeCombination(KeyCode.R,
					KeyCombination.CONTROL_DOWN);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (keyCombRedo.match(event)) {
						controller.executeCommand("REDO");
					}
				}
			});
			
			/**
			 * This snippet of code allows 
			 * the user to remotely execute clear
			 * commands when CTRL+L is pressed - smallson
			 */
			final KeyCombination keyCombClear = new KeyCodeCombination(KeyCode.L,
					KeyCombination.CONTROL_DOWN);
			scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (keyCombClear.match(event)) {
						controller.executeCommand("CLEAR");
					}
				}
			});
			
			/**
			 * @author A0116018R
			 * This allows Phantom to be draggable.
			 */
			overallView_.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset_ = event.getSceneX();
					yOffset_ = event.getSceneY();
				}
			});
			overallView_.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage_.setX(event.getScreenX() - xOffset_);
					primaryStage_.setY(event.getScreenY() - yOffset_);
				}
			});

			overallView_.addEventHandler(KeyEvent.KEY_PRESSED,
					new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent t) {
							if (t.getCode() == KeyCode.ESCAPE) {
								primaryStage_.close();
							}
						}
					});
			
			//@author A0110567L
			//loading the reminder service. reminder will be activated 5 min before the task starttime
			TimeService timeService = TimeService.getInstance();
			timeService.startReminderService();
		
			//@author A0116018R
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}