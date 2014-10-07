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

	@FXML
	private Parent tableView;
	@FXML
	private Parent todayView;
	@FXML
	private Parent helperView;
	private AnchorPane overallView;

	private PreferenceManager(){
	}

	public static PreferenceManager getInstance(){		
		return pm;
	}

	public void initViews(Parent tableView, Parent todayView, Parent helperView, AnchorPane overallView){
		this.tableView = tableView;
		this.todayView = todayView;
		this.helperView = helperView;
		this.overallView = overallView;
		initialize(prefFileName);
	}

	private void initialize(String prefFileName2) {
		prefFile = new File(prefFileName);
		try {
			if(!prefFile.exists()){
				prefFile.createNewFile();
			} else {
				setCSS();
			}
		}catch (IOException e) {
			System.out.println("error");
		}

	}

	private void setCSS() throws IOException {
		
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

		themeUrl = getClass().getResource("../view/" + cssFileName +".css").toExternalForm();

		overallView.getStylesheets().clear();
		overallView.getStylesheets().add(themeUrl);

		tableView.getStylesheets().clear();
		tableView.getStylesheets().add(themeUrl);

		todayView.getStylesheets().clear();
		todayView.getStylesheets().add(themeUrl);

		helperView.getStylesheets().clear();
		helperView.getStylesheets().add(themeUrl);
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
