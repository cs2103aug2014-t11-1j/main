package parser;

/**
 * This class is used to extract
 * the command word in the user's
 * input.
 * 
 * * Author: smallson
 */

//@author A0116211B
public class CommandRetriever {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	
	/**
	 * Message constants
	 */
	private final String MESSAGE_INVALID_COMMAND = "Invalid command";
	
	/**
	 * Regex strings
	 */
	private final String ALPHANUM_REGEX = "^[a-zA-Z0-9|!]+$";

	private static CommandRetriever cr = new CommandRetriever();
	
	private CommandRetriever(){
	}

	public static CommandRetriever getInstance() {
		return cr;
	}

	protected String getCommandString(String input){
		if(input == null){
			return MESSAGE_INVALID_COMMAND;
		}
		input = input.trim();
		
		if(!input.split(STRING_SPACE)[0].matches(ALPHANUM_REGEX)){
			return MESSAGE_INVALID_COMMAND;
		}
		
		return input.split(STRING_SPACE)[0];
	}
}
