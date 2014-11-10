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
//@author A0116211B
/**
 * This class is used to initialize
 * the tray application and to give
 * the gui tray functionalities
 * 
 * * Author: smallson
 */

public class TrayApplication {
	
	private static TrayApplication ta_ = new TrayApplication();
	private boolean firstTime_ = true;
    private TrayIcon trayIcon_;
    private java.awt.Image image_ = null;
	
	private TrayApplication(){
	}
	
	public static TrayApplication getInstance(){
		return ta_;
	}
	
	public void createTrayIcon(final Stage stage) throws IOException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            image_ = ImageIO.read(ResourceLoader.load("ghost.png"));
          
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
            trayIcon_ = new TrayIcon(image_, "Phantom", popup);
            // set the TrayIcon properties
            trayIcon_.addActionListener(showListener);
            // add the tray image
            try {
                tray.add(trayIcon_);
            } catch (AWTException e) {
                System.err.println(e);
            }
        }
    }

    public void showProgramIsMinimizedMsg() {
        if (firstTime_) {
            trayIcon_.displayMessage("Phantom",
                    " is running in the background",
                    TrayIcon.MessageType.INFO);
            firstTime_ = false;
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
