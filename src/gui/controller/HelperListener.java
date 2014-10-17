package gui.controller;

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
			ah.displayHelper();
			helperViewController.setHelperView(newValue);
		} else if (newValue.length() < 2 && newValue.length() > 0) {
			ah.revertView();
			helperViewController.setHelperView("");
		}
	}

}
