package parser;

public class CommandRetriever {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";


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
		return input.split(STRING_SPACE)[0];
	}
}
