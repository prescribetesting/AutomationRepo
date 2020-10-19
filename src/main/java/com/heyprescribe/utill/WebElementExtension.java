package com.heyprescribe.utill;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.heyprescribe.base.BasePage;

public class WebElementExtension extends BasePage {

	public static WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			log.debug("An exception occurs while getting element" + e.getMessage());
		}
		return element;
	}

	public static List<WebElement> getElements(By locator) {

		List<WebElement> element = null;
		try {
			element = driver.findElements(locator);
		} catch (Exception e) {
			log.debug("An exception occurs while getting elements" + e.getMessage());
		}
		return element;
	}

	public static void click(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			log.debug("An exception occurs while clicking on WebElement" + e.getMessage());
		}
	}

	public static void sendKeys(By locator, String value) {
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			log.debug("An exception occurs while using Sendkeys" + e.getMessage());
		}
	}

	public static String getText(By locator) {
		try {
			return getElement(locator).getText();

		} catch (Exception e) {
			log.debug("An exception occurs while getting the text" + e.getMessage());
		}
		return null;
	}

	public static boolean isDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();

		} catch (Exception e) {
			log.debug("An exception occurs while verifying the element is display or not" + e.getMessage());
		}
		return false;
	}

	public static boolean isEnabled(By locator) {
		try {
			return getElement(locator).isEnabled();

		} catch (Exception e) {
			log.debug("An exception occurs while verifying the element is Enabled or not" + e.getMessage());
		}
		return false;
	}

	public static void jsWaitForPageLoad() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.readyState").equals("complete");
		} catch (Exception e) {
			log.debug("An exception occurs while waiting for page load" + e.getMessage());
		}
	}

	public static void jsClick(By locator) {
		try {
			WebElement ele = getElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			log.debug("An exception occurs while clicking on WebElement using JavascriptExecutor" + e.getMessage());
		}
	}

	public static void jsScroll(By locator) {
		try {
			WebElement ele = getElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} catch (Exception e) {
			log.debug("An exception occurs while scrolling the webpage using JavascriptExecutor" + e.getMessage());
		}
	}

	public static void moveToElement(By locator) {
		try {
			WebElement ele = getElement(locator);
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
		} catch (Exception e) {
			log.debug("An exception occurs while using moveToElement method" + e.getMessage());
		}
	}

	public static void selectByText(By locator, String value) {
		try {
			WebElement ele = getElement(locator);
			Select drp = new Select(ele);
			drp.selectByVisibleText(value);

		} catch (Exception e) {
			log.debug("An exception occurs while using select By Text method" + e.getMessage());
		}
	}

	public static void selectByIndex(By locator, int index) {
		try {
			WebElement ele = getElement(locator);
			Select drp = new Select(ele);
			drp.selectByIndex(index);

		} catch (Exception e) {
			log.debug("An exception occurs while using select By index method" + e.getMessage());
		}
	}

	public static WebElement selectByFirstIndex(By locator) {
		try {
			WebElement ele = getElement(locator);
			Select drp = new Select(ele);
			return drp.getFirstSelectedOption();

		} catch (Exception e) {
			log.debug("An exception occurs while using select By First Index method" + e.getMessage());
		}
		return null;
	}

	public static List<WebElement> getAllSelectedOptions(By locator) {
		try {
			WebElement ele = getElement(locator);
			Select drp = new Select(ele);
			return drp.getAllSelectedOptions();
		} catch (Exception e) {
			log.debug("An exception occurs while using get All Selected Options method" + e.getMessage());
		}
		return null;
	}
}
