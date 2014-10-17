package logic;

import java.util.logging.Level;

import storage.ModelTask;
import storage.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.MyLogger;

/**
 *
 * @author Jireh
 */
public class Delete extends CommandFactory {

	private boolean isDone;

	/**
	 * Constructor
	 */
	protected Delete(String input) {
		execute(input);
	}

	@Override
	protected void execute(String input) {
		try {
			int index = -1;
			
			if(isNumberInput(input)){
				index = getIndex(input);
			}
			else {
				tempList.clear();
				getDeleteList(input);
				if(tempList.getListSize() == 1){
					index = tempList.get(0).getPosition() - 1;
				}
			}

			if(index >= 0){
				list.remove(index);
				for (int i = index; i < list.getListSize(); i++) {
					list.get(i).setPosition(i + 1);
				}
				updateUndoAndRedoStacks();
				updateTaskList();
				isDone = true;
				CommandExecutor.setFeedBack(ErrorMessages.SUCCESS_DELETE_MESSAGE);
				MyLogger.log(Level.INFO,ErrorMessages.SUCCESS_DELETE_MESSAGE);
			}
			
			if(tempList.getListSize() == 0){
				CommandExecutor.setFeedBack(ErrorMessages.ERROR_DELETE_MESSAGE);
				MyLogger.log(Level.INFO,ErrorMessages.ERROR_DELETE_MESSAGE);
			}
			
			CommandExecutor.setFeedBack(ErrorMessages.WAIT_DELETE_MESSAGE);
			MyLogger.log(Level.INFO,ErrorMessages.WAIT_DELETE_MESSAGE);
			
		} catch (Exception ex) {
			CommandExecutor.setFeedBack(ErrorMessages.ERROR_DELETE_MESSAGE);
			MyLogger.log(Level.INFO,ErrorMessages.ERROR_DELETE_MESSAGE);
			printMessage(ex.getMessage());
			return;
		}
	}

	private void getDeleteList(String input) {
		// TODO Auto-generated method stub
	}

	private boolean isNumberInput(String input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isDone() {
		return isDone;
	}

	private static int getIndex(String input) {
		int index;
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException("Invalid index!");
		} else {
			index = Integer.parseInt(input.trim().split("\\s+")[0]) - 1;
		}
		if (!isValidLineNumber(index)) {
			throw new IllegalArgumentException("Invalid index!");
		} else {
			return index;
		}
	}
}
