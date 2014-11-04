package gui.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;

import com.MyLogger;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * This class is used to handle
 * user preferences and settings.
 * 
 * * Author: smallson
 */

public class PreferenceManager {


	private static PreferenceManager pm = new PreferenceManager();
	private File prefFile;
	private String prefFileName = "Settings.txt";
	private String defaultCSS = "DarkTheme";
	private String themeUrl;

	private String[] DICTIONARY_THEMES = {"BlueTheme", "ChineseTheme","ChristmasTheme" ,"CogTheme", "DarkTheme",
			"DragonTheme", "GhostsTheme","GhostTheme" ,"GreenTheme", "HalloweenTheme",
			"RedTheme", "RoyalTheme","SingaporeTheme" ,"SnakeTheme", "WolfTheme"};

	private AnchorPane overallView;

	private PreferenceManager(){
	}

	public static PreferenceManager getInstance(){		
		return pm;
	}

	public void initViews(AnchorPane overallView){
		this.overallView = overallView;
		initialize(prefFileName);
	}
	
	public void saveCSSPref(String cssFileName) throws IOException{	

		FileWriter fileWriter = new FileWriter(prefFile, false);
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
			cssFileName = defaultCSS;
		}
		
		try{
			themeUrl = "css/" + cssFileName +".css";

			overallView.getStylesheets().clear();
			overallView.getStylesheets().add(themeUrl);
			
		}catch(Exception e){
			System.out.println("error setting theme");
			MyLogger.log(Level.WARNING,"error setting theme");
		}
	}

	private void initialize(String prefFileName2) {
		prefFile = new File(prefFileName);
		try {
			if(!prefFile.exists()){
				prefFile.createNewFile();
			} else {
				setCssFromFile();
			}
		}catch (IOException e) {
			System.out.println("error");
		}

	}

	private void setCssFromFile() throws IOException {

		String cssFileName = defaultCSS;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(prefFileName));
			cssFileName = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(!dictionaryContains(DICTIONARY_THEMES,cssFileName)){
			cssFileName = defaultCSS;
		}

		try{
			themeUrl = "css/" + cssFileName +".css";

			overallView.getStylesheets().clear();
			overallView.getStylesheets().add(themeUrl);
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
