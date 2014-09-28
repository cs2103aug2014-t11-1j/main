package gui;

import java.io.InputStream;

/**
 * This class is used to load
 * external resources for use
 * in the GUI.
 * 
 * * Author: smallson
 */

final public class ResourceLoader {
	
	public static InputStream load(String path){
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if(input == null){
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}
		return input;
	}
}
