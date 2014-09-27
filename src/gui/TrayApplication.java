package gui;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import gui.ResourceLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class is used to initialize
 * the tray application and to give
 * the gui tray functionalities
 * 
 * * Author: smallson
 */

public class TrayApplication {
	
	private static TrayApplication ta = new TrayApplication();
	private boolean firstTime = true;
    private TrayIcon trayIcon;
    private java.awt.Image image = null;
	
	private TrayApplication(){
	}
	
	public static TrayApplication getInstance(){
		return ta;
	}
	
	public void createTrayIcon(final Stage stage) throws IOException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            image = ImageIO.read(ResourceLoader.load("ghost.png"));
          
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    hide(stage);
                }
            });
            // create a action listener to listen for default action executed on the tray icon
            final ActionListener closeListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            };

            ActionListener showListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.show();
                        }
                    });
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "Phantom", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(showListener);
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
        }
    }

    public void showProgramIsMinimizedMsg() {
        if (firstTime) {
            trayIcon.displayMessage("Phantom",
                    " is running in the background",
                    TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }

    private void hide(final Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (SystemTray.isSupported()) {
                	
                    stage.hide();
                    showProgramIsMinimizedMsg();
                } else {
                    System.exit(0);
                }
            }
        });
    }
}
