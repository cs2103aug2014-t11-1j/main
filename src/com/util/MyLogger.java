package com.util;

/**
 * this class is meant for logging. It is a static class so every class will log using this logger
 * eg: MyLogger.log(Level level, String msg);
 */

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@author A0110567L
public class MyLogger {
	public static Logger logger;
	private static Handler fileHandler;
	private static Formatter plainText;

	private MyLogger() throws IOException {
		logger = Logger.getLogger(MyLogger.class.getName());
		fileHandler = new FileHandler("phantomLog.txt", true);
		plainText = new SimpleFormatter();
		fileHandler.setFormatter(plainText);
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
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
	}

	public static void closeLogger() {
		fileHandler.close();
	}
}