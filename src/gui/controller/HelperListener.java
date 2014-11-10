package gui.controller;
/**
 * This class is a listener for changes in the 
 * commandLine_ textfield.
 * 
 * It will listen for specific changes  
 * to decide when to animate the helper view.
 * @author A0116211B
 */
import gui.controller.view.HelperViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class HelperListener implements ChangeListener<String> {

	private HelperViewController helperViewController_;
	private AnimationHandler ah_;

	public HelperListener(HelperViewController helperViewController) {
		this.helperViewController_ = helperViewController;
		ah_ = AnimationHandler.getInstance();
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
			helperViewController_.setAddHelperView(newValue);
			ah_.displayHelper();
		} else if (newValue.length() < 2 && newValue.length() > 0) {
			helperViewController_.setAddHelperView("");
			ah_.revertView();
		}

		if(firstWord.equalsIgnoreCase("CLEAR")){
			setClearHelper(newValue);
		} else if (newValue.length() < 3 && newValue.length() > 0) {
			ah_.revertView();
		}
	}

	private void setClearHelper(String newValue) {		

		if (newValue.equalsIgnoreCase("CLEAR DONE")) {
			helperViewController_.setClearDoneHelperView();
			ah_.displayHelper();
		} else if (newValue.equalsIgnoreCase("CLEAR URGENT")) {
			helperViewController_.setClearUrgentHelperView();
			ah_.displayHelper();
		} else {
			helperViewController_.setClearAllHelperView();
			ah_.displayHelper();
		} 
	}
}
