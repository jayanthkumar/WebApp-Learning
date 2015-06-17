package com.jsomiset.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	private static final Properties configProperties = new Properties();

	static {
		try {
			loadProperties("config.properties");
		} catch (IOException e) {
			System.out.println("unable to load Properties");
		}
	}

	public static void loadProperties(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemClassLoader()
				.getResourceAsStream(fileName);
		if (stream != null) {
			configProperties.load(stream);
		} else {
			System.out.println("File not found");
		}
	}

	public static String getProperty(String key) {
		return configProperties.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println("Value is "+  PropertyLoader.getProperty("key"));
	}
}
