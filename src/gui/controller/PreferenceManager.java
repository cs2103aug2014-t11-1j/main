package gui.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import javafx.scene.layout.AnchorPane;

import com.util.MyLogger;

/**
 * This class is used to handle
 * user preferences and settings.
 * 
 * * Author: smallson
 */
//@author A0116211B
public class PreferenceManager {
	private static final String PREF_FILE_NAME = "Settings.txt";
	private static final String defaultCSS_ = "DarkTheme";
	private static final String[] DICTIONARY_THEMES = {"BlueTheme", "ChineseTheme","ChristmasTheme" ,"CogTheme", "DarkTheme",
		"DragonTheme", "GhostsTheme","GhostTheme" ,"GreenTheme", "HalloweenTheme",
		"RedTheme", "RoyalTheme","SingaporeTheme" ,"SnakeTheme", "WolfTheme"};

	private static PreferenceManager pm = new PreferenceManager();
	
	private File prefFile_;

	private String themeUrl_;

	private AnchorPane overallView_;

	private PreferenceManager(){
	}

	public static PreferenceManager getInstance(){		
		return pm;
	}

	public void initViews(AnchorPane overallView){
		this.overallView_ = overallView;
		initialize(PREF_FILE_NAME);
	}
	
	public void saveCSSPref(String cssFileName) throws IOException{	

		FileWriter fileWriter = new FileWriter(prefFile_, false);
		BufferedWriter buffer = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(buffer);

		String text = null;

		text = cssFileName;
		printWriter.println(cssFileName);

		printWriter.close();
		buffer.close();
		fileWriter.close();		
	}	
	
	public void setCss(String cssFileName){
		if(!dictionaryContains(DICTIONARY_THEMES,cssFileName)){
			cssFileName = defaultCSS_;
		}
		
		try{
			themeUrl_ = "main/Resources/css/" + cssFileName +".css";

			overallView_.getStylesheets().clear();
			overallView_.getStylesheets().add(themeUrl_);
			
		}catch(Exception e){
			System.out.println("error setting theme");
			MyLogger.log(Level.WARNING,"error setting theme");
		}
	}
	
	public String getThemeUrl(){
		return themeUrl_;
	}
	
	private void initialize(String prefFileName2) {
		prefFile_ = new File(PREF_FILE_NAME);
		try {
			if(!prefFile_.exists()){
				prefFile_.createNewFile();
			} else {
				setCssFromFile();
			}
		}catch (IOException e) {
			System.out.println("error");
		}

	}

	private void setCssFromFile() throws IOException {

		String cssFileName = defaultCSS_;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PREF_FILE_NAME));
			cssFileName = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(!dictionaryContains(DICTIONARY_THEMES,cssFileName)){
			cssFileName = defaultCSS_;
		}

		try{
			themeUrl_ = "main/Resources/css/" + cssFileName +".css";

			overallView_.getStylesheets().clear();
			overallView_.getStylesheets().add(themeUrl_);
		}catch(Exception e){
			System.out.println("theme uninitilised");
			MyLogger.log(Level.WARNING,"theme uninitilised");
		}
	}

	private boolean dictionaryContains(String[] dictionary, String keyword) {
		boolean isFound = false;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equalsIgnoreCase(keyword)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
}
