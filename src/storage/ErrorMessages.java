package storage;

/**
 * This class is used to store Error Messages for
 * later use in other classes.
 * 
 */

public class ErrorMessages {
	
	/**
	 * General purpose Error Message String
	 */	
	private static String ERROR_GENERAL;
	
	/**
	 * Preset Error Message Strings
	 */	
	private static final String ERROR_NO_ARGUMENTS = "No arguments.";
	
	/**
	 * Constructor
	 */	
	public ErrorMessages(){
		
	}
	
	/**
	 * Assessors
	 */	
	public String getGeneralErrorMessage(){
		return ERROR_GENERAL;
	}
	
	/**
	 * Mutators
	 */	
	public void setGeneralErrorMessage(String errorMessage){
		ERROR_GENERAL = errorMessage;
	}

}
