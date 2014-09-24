package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.LogicFacade;

public class CommandLineController{
	@FXML
	private TextField commandLine;
	
	private LogicFacade logicFacade;
	
	private PhantomController phantomController;
	
	public CommandLineController(){
		System.out.println("cmd constructor");
	}
	
	@FXML
	private void initialize() {
		System.out.println("cmd initilising");
	}
	
	protected void setController(PhantomController controller){
		phantomController = controller;
	}
	
	@FXML
	private void handleKeyPressed(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			String input = commandLine.getText();
			commandLine.clear();
			String feedback;
			try {
				feedback = logicFacade.getFeedBack(input);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void printStuff(){
		System.out.println("pringting cmd");
	}

	public void setLogicFacade(LogicFacade logicFacade) {
		this.logicFacade = logicFacade;	
	}
	
	
}
