package gui;

import gui.controller.PhantomController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.HotkeyListener;

/**
 * This class is used to manage
 * the global keyboard shortcuts
 * using JIntellitype library.
 * * Author: smallson
 */

public class ShortcutManager {

	private static ShortcutManager sm = new ShortcutManager();
	private static JIntellitype ji;
	private static TrayApplication ta;
	private static PhantomController pc;

	private ShortcutManager(){
	}

	public static ShortcutManager getInstance(){
		return sm;
	}

	public void setHotKeys(Stage primaryStage){
		ji = JIntellitype.getInstance();
		ta = TrayApplication.getInstance();	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/OverallView.fxml"));
		pc = loader.getController();
		ji.registerHotKey(1, JIntellitype.MOD_ALT, (int)'Q');
		ji.registerHotKey(2, JIntellitype.MOD_ALT, (int)'W');
		ji.registerHotKey(3, JIntellitype.MOD_ALT + JIntellitype.MOD_CONTROL, (int) 'Q');

		ji.addHotKeyListener(new HotkeyListener()
		{
			@Override
			public void onHotKey(int key) {
				if(key == 1){
					Platform.runLater(new Runnable(){
						public void run(){
							primaryStage.close();
							ta.showProgramIsMinimizedMsg();
						}
					});

				}
				if(key == 2){
					Platform.runLater(new Runnable(){
						public void run(){
							primaryStage.show();
						}
					});

				}
				if(key == 3){
					Platform.runLater(new Runnable(){
						public void run(){
							System.exit(0);
						}
					});
				}
			}
		});
	}
}
