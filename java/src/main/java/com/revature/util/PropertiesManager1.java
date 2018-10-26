package com.revature.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesManager1 {
	private static Properties properties;
	private static String PATH = "src/main/resources/props.properties";

	private PropertiesManager1() {
	}

	private static Properties load() {
		if (properties == null) {
			properties = new Properties();
		} else {
			return properties;
		}
		try {
			if (!Files.exists(Paths.get(PATH))) {
				Files.createFile(Paths.get(PATH));
			}
			properties.load(new FileInputStream(PATH));
			return properties;
		} catch (IOException e) {
			LogInterface.logStackTrace(e);
			return properties = null;
		}
	}

	public static String get(String key) {
		properties = load();
		if (properties == null) {
			return null;
		}
		return load().getProperty(key);
	}

	public static void set(String key, String value) {
		if (load() != null) {
			properties.setProperty(key, value);
			try {
				properties.store(new FileOutputStream(PATH), "");
			} catch (IOException e) {
				LogInterface.logStackTrace(e);
			}
		}
	}
}
