package com.heyprescribe.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.heyprescribe.listeners.ExtentRepoertListeners;
import com.heyprescribe.utill.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	Properties prop;
	private WebDriver dvr;
	// public static WebDriver driver;

	public static Logger log = Logger.getLogger("devpinoyLogger");
	private static final ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static ExtentReports extent = ExtentRepoertListeners.startReport();

	private static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public ExtentTest getTest() {
		return testReport.get();
	}

	public void setExtentTest(ExtentTest test) {
		testReport.set(test);
	}

	public static WebDriver getDriver() {
		return dr.get();
	}

	private void setDriver(WebDriver driver) {
		dr.set(driver);
	}

	public Properties readProperties() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/heyprescribe/config/config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	public void browserInitialization(String browser) {

		PropertyConfigurator.configure(System.getProperty("user.dir") + prop.getProperty("log4jPropertyFile"));
		OptionsManager optionsManager = new OptionsManager();
		// String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			dvr = new ChromeDriver(optionsManager.getChromeOptions());
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			dvr = new FirefoxDriver();
		} else {
			log.debug("Browser name  " + browser + "   is not found");

		}
		setDriver(dvr);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		// driver = getDriver();
	}

	public void tearDown() {
		getDriver().close();
	}

	public void goToUrl(String url) {
		getDriver().get(url);
	}

	public void switchTab(int tabPosition) {

		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());

		getDriver().switchTo().window(tabs.get(tabPosition));
	}
}
