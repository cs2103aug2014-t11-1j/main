package logic;

import java.util.logging.Level;
import java.util.regex.Pattern;

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
	private final static Pattern numberPattern = Pattern.compile("\\d++");
	private final static int INITIAL_INPUT = -1;

	/**
	 * Constructor
	 */
	protected Delete(String input) {
		execute(input);
	}

	@Override
	protected void execute(String input) {
		try {
			int index = INITIAL_INPUT;

			if(isNumberInput(input)){
				index = getIndex(input);
			}
			else {
				tempList.clear();
				getDeleteList(input);
				
				if(isOnePossibility()){
					index = getTaskIndex();
				}
			}

			if(index != INITIAL_INPUT){
				list.remove(index);
				updatePositionsInTaskList(index);
				
				updateUndoAndRedoStacks();
				updateTaskList();
				isDone = true;
				
				CommandExecutor.setUserFeedBack(FeedbackMessages.SUCCESS_DELETE_MESSAGE);
				MyLogger.log(Level.INFO,FeedbackMessages.SUCCESS_DELETE_MESSAGE);
				CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
			}else{

				if(noPossibleTask()){
					CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_DELETE_MESSAGE);
					MyLogger.log(Level.INFO,FeedbackMessages.ERROR_DELETE_MESSAGE);
					CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
				}

				updateTempList();
				CommandExecutor.setUserFeedBack(FeedbackMessages.WAIT_DELETE_MESSAGE);
				MyLogger.log(Level.INFO,FeedbackMessages.WAIT_DELETE_MESSAGE);
				CommandExecutor.setGuiFeedBack(FeedbackMessages.SWITCH_TO_TEMP);
			}

		} catch (Exception ex) {
			CommandExecutor.setUserFeedBack(FeedbackMessages.ERROR_DELETE_MESSAGE);
			MyLogger.log(Level.INFO,FeedbackMessages.ERROR_DELETE_MESSAGE);
			CommandExecutor.setGuiFeedBack(FeedbackMessages.NORMAL_STATE);
			printMessage(ex.getMessage());
			return;
		}
	}

	private boolean noPossibleTask() {
		return tempList.getListSize() == 0;
	}

	private void updatePositionsInTaskList(int index) {
		for (int i = index; i < list.getListSize(); i++) {
			list.get(i).setPosition(i + 1);
		}
	}

	private void getDeleteList(String input) {
		for(ModelTask task : list){
			if(task.getEvent().contains(input)){
				System.out.println(task.getEvent());
				tempList.add(task);
			}
		}
	}

	private boolean isNumberInput(String input) {
		return input != null && numberPattern.matcher(input).matches();
	}
	
	private boolean isOnePossibility() {
		return tempList.getListSize() == 1;
	}
	
	private int getTaskIndex() {
		return tempList.get(0).getPosition() - 1;
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
