package com.util;

/**
 * @author Zhang Yongkai
 * this class is meant for logging. It is a static class so every class will log using this logger
 * There is therefore no need to create object for this class anywhere. 
 * to use it, just need to call it as a static class like:
 * eg: MyLogger.log(Level level, String msg);
 */

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	public static Logger logger;
	private static Handler fileHandler;
	private static Formatter plainText;

	private MyLogger() throws IOException {
		// instance the logger
		logger = Logger.getLogger(MyLogger.class.getName());
		// instance the filehandler
		fileHandler = new FileHandler("phantomLog.txt", true);
		// instance formatter, set formatting, and handler
		plainText = new SimpleFormatter();
		fileHandler.setFormatter(plainText);
		logger.addHandler(fileHandler);
		// change configuration to use only root
		logger.setUseParentHandlers(false);
		// set preference to log everything
		logger.setLevel(Level.ALL);

	}

	private static Logger getLogger() {
		if (logger == null) {
			try {
				new MyLogger();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logger;
	}

	public static void log(Level level, String msg) {
		getLogger().log(level, msg);
		// System.out.println(msg);
		// fileHandler.close();
	}

	public static void closeLogger() {
		fileHandler.close();
	}
}