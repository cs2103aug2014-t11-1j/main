package gui.controller;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;

public class ThemeMenuHandler {
	private Menu themeMenu;
	
	private static int DARK_THEME = 0;
	private static int BLUE_THEME = 1;
	private static int RED_THEME = 2;
	private static int GREEN_THEME = 3;
	
	public ThemeMenuHandler(Menu themeMenu){
		this.themeMenu = themeMenu;
	}

	public void setDarkTheme() {
		setAllThemeSelectedFalse();	
		((CheckMenuItem) themeMenu.getItems().get(DARK_THEME)).setSelected(true);
	}
	
	public void setBlueTheme(){
		setAllThemeSelectedFalse();	
		((CheckMenuItem) themeMenu.getItems().get(BLUE_THEME)).setSelected(true);
	}
	
	public void setRedTheme(){
		setAllThemeSelectedFalse();	
		((CheckMenuItem) themeMenu.getItems().get(RED_THEME)).setSelected(true);
	}
	
	public void setGreenTheme(){
		setAllThemeSelectedFalse();	
		((CheckMenuItem) themeMenu.getItems().get(GREEN_THEME)).setSelected(true);
	}
	
	private void setAllThemeSelectedFalse() {
		for(int i = 0; i < themeMenu.getItems().size(); i++){
			((CheckMenuItem) themeMenu.getItems().get(i)).setSelected(false);
		}
	}
}
