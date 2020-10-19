package com.heyprescribe.utill;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.heyprescribe.base.BasePage;

public class OptionsManager extends BasePage {

	public ChromeOptions co;
	public FirefoxOptions fo;
	Properties prop;

	public ChromeOptions getChromeOptions() {
		prop = readProperties();
		co = new ChromeOptions();
		co.addArguments("--incognito");

		if (prop.getProperty("headless").equals("yes")) {
			co.addArguments("--headless");
			co.addArguments("--window-size=1920,1080");
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		fo.addArguments("--incognito");

		if (prop.getProperty("headless").equals("yes")) {
			fo.addArguments("--headless");
		}
		return fo;
	}
}
