package com.heyprescribe.utill;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.heyprescribe.base.BasePage;

public class WebDriverExtension extends BasePage {

	public static WebElement waitForElementPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static List<WebElement> waitForAllElementPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public static WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static List<WebElement> waitForAllElementsVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		List<WebElement> ele = WebElementExtension.getElements(locator);
		return wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public static Boolean waitForElementInVisible(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

	}

	public static WebElement waitForElementClickable(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public static WebElement waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static Boolean waitForTextPresent(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));

	}

}
