package gui;

import java.io.IOException;
import gui.controller.PhantomController;
import gui.controller.PreferenceManager;
import gui.controller.Reminder;
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

	private Stage primaryStage;
	private AnchorPane overallView;
	private TrayApplication ta;
	private ShortcutManager sm;
	// private BorderPane rootLayout;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Phantom");
		this.primaryStage.centerOnScreen();
		this.primaryStage.initStyle(StageStyle.TRANSPARENT);

		ta = TrayApplication.getInstance();
		try {
			ta.createTrayIcon(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sm = ShortcutManager.getInstance();
		Platform.setImplicitExit(false);
		sm.setHotKeys(primaryStage);

		// initRootLayout();
		showPhantomOverallView();
	}

	// public void initRootLayout() {
	// try {
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
	// rootLayout = (BorderPane) loader.load();
	//
	// Scene scene = new Scene(rootLayout);
	// primaryStage.setScene(scene);
	// primaryStage.show();
	//
	// rootLayout.setOnMousePressed(new EventHandler<MouseEvent>() {
	// @Override
	// public void handle(MouseEvent event) {
	// xOffset = event.getSceneX();
	// yOffset = event.getSceneY();
	// }
	// });
	// rootLayout.setOnMouseDragged(new EventHandler<MouseEvent>() {
	// @Override
	// public void handle(MouseEvent event) {
	// primaryStage.setX(event.getScreenX() - xOffset);
	// primaryStage.setY(event.getScreenY() - yOffset);
	// }
	// });
	//
	// this.primaryStage.getIcons().add(new
	// Image("file:Resources/images/ghost.png"));
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public void showPhantomOverallView() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/OverallView.fxml"));
			overallView = (AnchorPane) loader.load();
			System.out.println("overall view loaded");

			Scene scene = new Scene(overallView);
			primaryStage.setScene(scene);
			primaryStage.show();

			// rootLayout.setCenter(overallView);
			

			PhantomController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			controller.setOverallView(overallView);
			controller.initPrefManager();
			
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
						controller.executeCommand("UNDO","");

					}
				}
			});
			/**
			 */
			
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
						controller.executeCommand("REDO","");
					}
				}
			});
			/**
			 */
			
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
						controller.executeCommand("CLEAR","");
					}
				}
			});
			/**
			 */
			

			overallView.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});
			overallView.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					primaryStage.setX(event.getScreenX() - xOffset);
					primaryStage.setY(event.getScreenY() - yOffset);
				}
			});

			overallView.addEventHandler(KeyEvent.KEY_PRESSED,
					new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent t) {
							if (t.getCode() == KeyCode.ESCAPE) {
								primaryStage.close();
							}
						}
					});

			//loading the reminder service. reminder will be activated 5 min before the task starttime
			TimeService.startReminderService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}