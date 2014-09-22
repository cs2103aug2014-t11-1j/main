package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class EditListener implements ChangeListener<String> {

	private CommandGenerator commandGenerator;
	private TextField commandLine;

	public EditListener(CommandGenerator commandGenerator, TextField commandLine) {
		this.commandGenerator = commandGenerator;
		this.commandLine = commandLine;

	}

	@Override
	public void changed(ObservableValue<? extends String> observable,
			String oldValue, String newValue) {
		String[] subStrings = newValue.trim().split(" ");
		if (subStrings.length == 2) {
			if (subStrings[0].equalsIgnoreCase("edit")
					&& isInteger(subStrings[1])) {
				if (!getTaskDescription(subStrings[1]).equals("")) {
					commandLine.setText(newValue + " "
							+ getTaskDescription(subStrings[1]));
				}
			}
		}
	}

	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	private String getTaskDescription(String token) {
		String textInBox = commandGenerator.getDisplayString() + "\n";

		if (textInBox.contains(token + ".")) {
			String partialString = textInBox.substring(textInBox.indexOf(token
					+ ".") + 3);
			return partialString.substring(0, partialString.indexOf("\n"));
		}
		return "";
	}

}
