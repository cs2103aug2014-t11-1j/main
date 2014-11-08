package parser;


/**
 * This class is used to manage
 * the parse free inputs indicated
 * by the user with quotation marks.
 * 
 * * Author: smallson
 */
//@author A0116211B

public class ParseFreeManager {
	

	private static ParseFreeManager pfm = new ParseFreeManager();
	
	private static final String STRING_QUOTE = "\"";
	
	private static final int NOT_FOUND = -1;
	private static final int INT_ZERO = 0;
	private static final int INT_OFFSET = 1;
	
	private String parseFreeString_;
	private String parseFreeInput_ = "";

	private ParseFreeManager(){
	}

	public static ParseFreeManager getInstance(){
		return pfm;
	}
	
	public String getParseFreeInput(String input){
		
		parseFreeString_ = "";
		parseFreeInput_ = input;
		
		int startIndex = NOT_FOUND;
		int endIndex = NOT_FOUND;
		
		if(input.contains(STRING_QUOTE)){
			startIndex = input.indexOf(STRING_QUOTE);
			endIndex = input.lastIndexOf(STRING_QUOTE);
				
			if(isOnlyOneQuote(startIndex,endIndex)){
				return input;
			} else {
				parseFreeInput_ = extractParseFreeInput(input, startIndex, endIndex);
				parseFreeString_ = extractParseFreeString(input, startIndex, endIndex);
				return parseFreeInput_;
			}			
		}
		
		return input;
	}

	public String replaceParseFree(String input){
		return input.replace(STRING_QUOTE + STRING_QUOTE, STRING_QUOTE + parseFreeString_ + STRING_QUOTE);
	}

	private String extractParseFreeString(String input, int start, int end) {
		return input.substring(start + INT_OFFSET,end);
	}

	private String extractParseFreeInput(String input, int start, int end) {	
		return input.substring(INT_ZERO, start + INT_OFFSET) + input.substring(end);
	}

	private boolean isOnlyOneQuote(int startIndex, int endIndex) {
		return (startIndex == endIndex);
	}
}