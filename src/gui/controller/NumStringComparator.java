package gui.controller;
/**
 * Depreciated class; table doesn't allow visual sorting
 * by clicking anymore
 * Comparator to compare the positions in table
 * 
 * The positions are String numbers eg: "1"
 * @author A0116018R
 */
import java.util.Comparator;

public class NumStringComparator implements Comparator<String>{
	
	@Override
	/* From java.util.Comparator:
	 * Compares its two arguments for order. 
	 * Returns a negative integer, zero, or a positive integer as the first argument 
	 * is less than, equal to, or greater than the second.
	 * (non-Javadoc)
	 */
	public int compare(String str1, String str2) {
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
		
		if(num1 < num2){
			return -1;
		}
		else if (num1 == num2){
			return 0;
		}
		else{
			return 1;
		}
	}
}
