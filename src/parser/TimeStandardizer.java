package parser;

/**
 * This class is used to standardize
 * the time format input by the user.
 * 
 * * Author: smallson
 */
//@author A0116211B

public class TimeStandardizer {

	/**
	 * String constants
	 */
	private final String STRING_SPACE = " ";


	private static enum FormatType {
		SPT, DT, MT, DTF, DTN, INVALID
	};

	private static final String FORMAT_SPECIAL = "SPT";
	private static final String FORMAT_DEFAULT = "DT";
	private static final String FORMAT_MILITARY = "MT";	
	private static final String FORMAT_DASH_TIME_FIRST = "DTF";
	private static final String FORMAT_DASH_TIME_NEXT = "DTN";

	public TimeStandardizer(){
	}

	public String formatTime(String time){

		String timeFormat = time.split(STRING_SPACE).clone()[0];
		time = time.replaceFirst(timeFormat, "").trim();
		FormatType formatType = getFormatType(timeFormat);
		System.out.println(timeFormat);
		TimeFormatter tf = new TimeFormatter();

		switch (formatType) {
		case SPT :
			//System.out.println("time Format: SPT");
			time = tf.convertSPTformat(time);
			break;
		case DT :
			//System.out.println("time Format: DT");
			time = tf.convertDTformat(time);
			break;
		case MT :
			//System.out.println("time Format: MT");
			time = tf.convertMTformat(time);
			break;
		case DTF :
			time = tf.convertDTFformat(time);
			break;
		case DTN :
			time = tf.convertDTNformat(time);
			break;
		case INVALID :
			System.out.print("Error");
			break;
		default :
			assert true;
		}


		return time;
	}

	private static FormatType getFormatType(String format) {

		if (format.equalsIgnoreCase(FORMAT_SPECIAL)) {
			return FormatType.SPT;
		} else if (format.equalsIgnoreCase(FORMAT_DEFAULT)) {
			return FormatType.DT;
		} else if (format.equalsIgnoreCase(FORMAT_MILITARY)) {
			return FormatType.MT;
		} else if (format.equalsIgnoreCase(FORMAT_DASH_TIME_FIRST)) {
			return FormatType.DTF;
		} else if (format.equalsIgnoreCase(FORMAT_DASH_TIME_NEXT)) {
			return FormatType.DTN;
		} else {
			return FormatType.INVALID;
		}
	}
}


