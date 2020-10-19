package com.heyprescribe.utill;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.heyprescribe.base.BasePage;

public class Screenshort extends BasePage {

	WebDriver driver;

	public Screenshort(WebDriver driver) {
		this.driver = driver;
	}

	public String captureScreenshot(String screenShotName) throws IOException {
		try {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + screenShotName + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);

			return dest;
		} catch (Exception e)

		{
			// log.debug("An exception occurs while tacking screenshot" +
			// e.getMessage());

			return e.getMessage();

		}
	}

}
