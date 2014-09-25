package parser;

/**
 * This class is used to standardize
 * the date format input by the user.
 * 
 * * Author: smallson
 */

public class DateStandardizer {
	
	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";
	

	private static enum FormatType {
		DD, SD, DFS, MFS, NDD, DDF, DDN, INVALID
		};
	
	private static final String FORMAT_DICTIONARY_DAY = "DD";
	private static final String FORMAT_SLASH_DATE = "SD";
	private static final String FORMAT_DAY_FIRST_STRING = "DFS";
	private static final String FORMAT_MONTH_FIRST_STRING = "MFS";
	private static final String FORMAT_NEXT_DICTIONARY_DAY = "NDD";	
	private static final String FORMAT_DASH_DATE_FIRST = "DDF";	
	private static final String FORMAT_DASH_DATE_NEXT = "DDN";	
	
	public DateStandardizer(){
	}
	
	public String formatDate(String date){
		
		String dateFormat = date.split(STRING_SPACE).clone()[0];
		date = date.replaceFirst(dateFormat, "").trim();
		FormatType formatType = getFormatType(dateFormat);
		DateFormatter df = new DateFormatter();
		
		switch (formatType) {
		case DD :
	//		System.out.println("Date Format: DD");
			date = df.convertDDformat(date);
			break;
		case SD :
	//		System.out.println("Date Format: SD");
			date = df.convertSDformat(date);
			break;
		case DFS :
	//		System.out.println("Date Format: DFS");
			date = df.convertDFSformat(date);
			break;
		case MFS :
	//		System.out.println("Date Format: MFS");
			date = df.convertMFSformat(date);
			break;
		case NDD:
	//		System.out.println("Date Format: NDD");
			date = df.convertNDDformat(date);
			break;
		case DDF:
	//		System.out.println("Date Format: DDF");
			date = df.convertDDFformat(date);
			break;
		case DDN:
	//		System.out.println("Date Format: DDN");
			date = df.convertDDNformat(date);
			break;
		case INVALID :
			System.out.print("Error");
			break;
		default :
			throw new Error("TELL USER TO MAKE A SANDWICH FOR ME");
		}
		
		
		return date;
	}
	
	private static FormatType getFormatType(String format) {
		
		if (format.equalsIgnoreCase(FORMAT_DICTIONARY_DAY)) {
			return FormatType.DD;
		} else if (format.equalsIgnoreCase(FORMAT_SLASH_DATE)) {
			return FormatType.SD;
		} else if (format.equalsIgnoreCase(FORMAT_DAY_FIRST_STRING)) {
			return FormatType.DFS;
		} else if (format.equalsIgnoreCase(FORMAT_MONTH_FIRST_STRING)) {
			return FormatType.MFS;
		} else if (format.equalsIgnoreCase(FORMAT_NEXT_DICTIONARY_DAY)) {
			return FormatType.NDD;
		}else if (format.equalsIgnoreCase(FORMAT_DASH_DATE_FIRST)) {
			return FormatType.DDF;
		}else if (format.equalsIgnoreCase(FORMAT_DASH_DATE_NEXT)) {
			return FormatType.DDN;
		}else {
			return FormatType.INVALID;
		}
	}
}
