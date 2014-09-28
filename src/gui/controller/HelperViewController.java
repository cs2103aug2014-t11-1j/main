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
	
	@FXML
	Label taskDescription;
	@FXML
	Label deadline;
	@FXML 
	Label start;
	@FXML 
	Label end;
	
	
	private static ParserFacade pf;
	
	public HelperViewController(){
		taskDescription = new Label();
		deadline = new Label();
		pf = ParserFacade.getInstance();
	}
	
	public void setHelperView(String input){
		Task task = pf.getTask(input);
		
		if(task.getTaskDescription() != null){
			taskDescription.setText(STRING_DESCRIPTION + task.getTaskDescription());
		}else{
			taskDescription.setText(STRING_DESCRIPTION);
		}
		
		if(task.getDeadLine() != null){
			deadline.setText(STRING_DEADLINE + task.getDeadLine());
		}else{
			deadline.setText(STRING_DEADLINE);
		}
		
		if(task.getStartDate() != null && task.getStartTime() != null){
			start.setText(STRING_START + task.getStartDate() + task.getStartTime());
		}else if(task.getStartDate() == null && task.getStartTime() != null){
			start.setText(STRING_START + task.getStartTime());
		}else if(task.getStartTime() == null && task.getStartDate() != null){
			start.setText(STRING_START + task.getStartDate());
		}else{
			start.setText(STRING_START);
		}
		
		if(task.getEndDate() != null && task.getEndTime() != null){
			end.setText(STRING_END + task.getEndDate() + task.getEndTime());
		}else if(task.getEndDate() == null && task.getEndTime() != null) {
			end.setText(STRING_END + task.getEndTime());
		}else if(task.getEndTime() == null && task.getEndDate() != null){
			end.setText(STRING_END + task.getEndDate());
		}else{
			end.setText(STRING_END);
		}		
	}
}
