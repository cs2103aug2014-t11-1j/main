package gui.controller;

/**
 * This class is used to load the tutorialView into the AnchorPane given.
 * Note: should only be called by PhantomController.
 * Note: the current themeUrl should be inserted as well
 * 
 * Author: Krystal
 */

import java.io.IOException;

import gui.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class TutorialLoader {
	AnchorPane tutorialView;
	AnchorPane overallView;
	
	public TutorialLoader(AnchorPane overallView, String themeUrl){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TutorialView.fxml"));
			tutorialView = (AnchorPane) loader.load();
			tutorialView.getStylesheets().clear();
			tutorialView.getStylesheets().add(themeUrl);
			this.overallView = overallView;
			overallView.getChildren().add(tutorialView);
			
			TutorialViewController controller = loader.getController();
			controller.setTutorialView(tutorialView);
			controller.setOverallView(overallView);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		overallView.getChildren().remove(tutorialView);
	}
}
