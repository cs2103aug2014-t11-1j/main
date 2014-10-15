package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class HelperListener implements ChangeListener<String>{

	private HelperViewController helperViewController;
	private AnimationHandler ah;
	
	public HelperListener(HelperViewController helperViewController){
		this.helperViewController = helperViewController;
		ah = AnimationHandler.getInstance();
	}
	
	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		String firstWord = newValue.split(" ")[0];
		if (firstWord.equalsIgnoreCase("add") || firstWord.equalsIgnoreCase("add!")) {
			ah.displayHelper();
		} else if (newValue.length() < 2) {
			ah.revertView();
		}
		
		helperViewController.setHelperView(newValue);
		
	}

}
