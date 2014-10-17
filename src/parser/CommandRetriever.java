package parser;

/**
 * This class is used to extract
 * the command word in the user's
 * input.
 * 
 * * Author: smallson
 */


public class CommandRetriever {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	
	private final String ALPHANUM_REGEX = "^[a-zA-Z0-9|!]+$";

	private static CommandRetriever cr = new CommandRetriever();
	
	private CommandRetriever(){
	}

	public static CommandRetriever getInstance() {
		return cr;
	}

	protected String getCommandString(String input){
		if(input == null){
			return "Invalid command";
		}
		input = input.trim();
		
		if(!input.split(STRING_SPACE)[0].matches(ALPHANUM_REGEX)){
			return "Invalid command";
		}
		
		return input.split(STRING_SPACE)[0];
	}
}
