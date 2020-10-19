package com.heyprescribe.utill;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.heyprescribe.base.BasePage;

public class ReadPropertiesFile extends BasePage {

	public static Properties propfile;

	public static Properties propertyfile(String fileName) {

		try {
			propfile = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + fileName);
			propfile.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propfile;
	}
}
