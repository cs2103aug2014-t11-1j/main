package gui.controller;

import parser.ParserFacade;
import storage.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class facilitates the
 * control of the elements in
 * helper view.
 * 
 * * Author: smallson
 */

public class HelperViewController {

	/**
	 * String constants
	 */
	private static final String STRING_DESCRIPTION = "Task Description: ";
	private static final String STRING_DEADLINE = "Deadline: ";
	private static final String STRING_START = "Start: ";
	private static final String STRING_END = "End: ";
	private static final String STRING_SPACE = " ";

	private static final String MESSAGE_CAUTION = "CAUTION!";
	private static final String MESSAGE_CLEAR_ALL = "You are about to clear ALL your tasks";
	private static final String MESSAGE_CLEAR_URGENT = "You are about to clear ALL URGENT tasks";
	private static final String MESSAGE_CLEAR_DONE = "You are about to clear ALL COMPLETED tasks";
	private static final String MESSAGE_ENTER_CONTINUE = "Press enter to continue..";

	@FXML
	Label firstLabel;
	@FXML
	Label secondLabel;
	@FXML 
	Label thirdLabel;
	@FXML 
	Label forthLabel;


	private static ParserFacade pf;

	public HelperViewController(){
		pf = ParserFacade.getInstance();
	}

	public void setAddHelperView(String input){
		Task task = pf.getTask(input);

		firstLabel.setVisible(true);
		secondLabel.setVisible(true);
		thirdLabel.setVisible(true);
		forthLabel.setVisible(true);

		if(task.getTaskDescription() != null){
			firstLabel.setText(STRING_DESCRIPTION + task.getTaskDescription());
		} else {
			firstLabel.setText(STRING_DESCRIPTION);
		}

		if(task.getDeadLine() != null){
			secondLabel.setText(STRING_DEADLINE + task.getDeadLine());
		} else {
			secondLabel.setText(STRING_DEADLINE);
		}

		if(task.getStartDate() != null && task.getStartTime() != null){
			thirdLabel.setText(STRING_START + task.getStartDate() + STRING_SPACE + task.getStartTime());
		} else if (task.getStartDate() == null && task.getStartTime() != null){
			thirdLabel.setText(STRING_START + task.getStartTime());
		} else if (task.getStartTime() == null && task.getStartDate() != null){
			thirdLabel.setText(STRING_START + task.getStartDate());
		} else {
			thirdLabel.setText(STRING_START);
		}

		if(task.getEndDate() != null && task.getEndTime() != null){
			forthLabel.setText(STRING_END + task.getEndDate() + STRING_SPACE + task.getEndTime());
		} else if (task.getEndDate() == null && task.getEndTime() != null) {
			forthLabel.setText(STRING_END + task.getEndTime());
		} else if (task.getEndTime() == null && task.getEndDate() != null){
			forthLabel.setText(STRING_END + task.getEndDate());
		} else {
			forthLabel.setText(STRING_END);
		}		
	}

	public void setClearAllHelperView(){

		firstLabel.setVisible(true);
		secondLabel.setVisible(true);
		thirdLabel.setVisible(true);
		forthLabel.setVisible(false);

		firstLabel.setText(MESSAGE_CAUTION);
		secondLabel.setText(MESSAGE_CLEAR_ALL);
		thirdLabel.setText(MESSAGE_ENTER_CONTINUE);
	}

	public void setClearUrgentHelperView(){

		firstLabel.setVisible(true);
		secondLabel.setVisible(true);
		thirdLabel.setVisible(true);
		forthLabel.setVisible(false);

		firstLabel.setText(MESSAGE_CAUTION);
		secondLabel.setText(MESSAGE_CLEAR_URGENT);
		thirdLabel.setText(MESSAGE_ENTER_CONTINUE);
	}

	public void setClearDoneHelperView(){

		firstLabel.setVisible(true);
		secondLabel.setVisible(true);
		thirdLabel.setVisible(true);
		forthLabel.setVisible(false);

		firstLabel.setText(MESSAGE_CAUTION);
		secondLabel.setText(MESSAGE_CLEAR_DONE);
		thirdLabel.setText(MESSAGE_ENTER_CONTINUE);
	}
}
