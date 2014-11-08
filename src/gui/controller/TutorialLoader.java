package gui.controller;

/**
 * @author A0116018R
 * 
 * Unused: not fully developed.
 * This class is used to load the tutorialView into the AnchorPane given.
 * Note: should only be called by PhantomController.
 * Note: the current themeUrl should be inserted as well
 */

import java.io.IOException;

import gui.MainApp;
import gui.controller.view.TutorialViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class TutorialLoader {
	AnchorPane tutorialView_;
	AnchorPane overallView_;
	
	public TutorialLoader(AnchorPane overallView, String themeUrl){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TutorialView.fxml"));
			tutorialView_ = (AnchorPane) loader.load();
			
			overallView_ = overallView;
			overallView.getChildren().add(tutorialView_);
			
			tutorialView_.getStylesheets().clear();
			tutorialView_.getStylesheets().add(themeUrl);
			
			TutorialViewController controller = loader.getController();
			controller.setTutorialView(tutorialView_);
			controller.setOverallView(overallView);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		overallView_.getChildren().remove(tutorialView_);
	}
}
