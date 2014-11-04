package gui.controller;

import gui.controller.view.HelperViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class HelperListener implements ChangeListener<String> {

	private HelperViewController helperViewController;
	private AnimationHandler ah;

	public HelperListener(HelperViewController helperViewController) {
		this.helperViewController = helperViewController;
		ah = AnimationHandler.getInstance();
	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {

		String firstWord = "";
		if ((newValue != null) && (newValue.replaceAll("\\s+", "")) != "") {
			String[] tokens = newValue.split("\\s+");
			if (tokens.length >= 1) {
				firstWord = newValue.split(" ")[0];
			}
		}

		if (firstWord.equalsIgnoreCase("add")
				|| firstWord.equalsIgnoreCase("add!")) {
			helperViewController.setAddHelperView(newValue);
			ah.displayHelper();
		} else if (newValue.length() < 2 && newValue.length() > 0) {
			helperViewController.setAddHelperView("");
			ah.revertView();
		}

		if(firstWord.equalsIgnoreCase("CLEAR")){
			setClearHelper(newValue);
		} else if (newValue.length() < 3 && newValue.length() > 0) {
			ah.revertView();
		}
	}

	private void setClearHelper(String newValue) {		

		if (newValue.equalsIgnoreCase("CLEAR DONE")) {
			helperViewController.setClearDoneHelperView();
			ah.displayHelper();
		} else if (newValue.equalsIgnoreCase("CLEAR URGENT")) {
			helperViewController.setClearUrgentHelperView();
			ah.displayHelper();
		} else {
			helperViewController.setClearAllHelperView();
			ah.displayHelper();
		} 
	}
}
