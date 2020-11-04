package com.heyprescribe.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class EditSlots extends BasePage {

	By imgPrescribe = By.cssSelector("h6 img");
	By btnSelectDoctor = By.cssSelector("button[data-toggle='dropdown']");

	private By lstDoctors(String docName) {
		return By.xpath("//a[contains(text(),'" + docName + "')]");
	}

	private By btnDate(String date) {
		return By.xpath("//div[contains(@class,'unfocused') and text()='" + date + "']");
	}

	By txtAddNewTiming = By.xpath("//h4[contains(text(),'Add New Timings')]");
	By txtbSetSlotduration = By.xpath("(//input[@placeholder='Slot Duration'])[1]");
	By txtbSetFirstTimeFee = By.xpath("(//input[@placeholder='Slot Duration'])[2]");
	By txtbRepetitiveFee = By.xpath("(//input[@placeholder='Slot Duration'])[3]");
	By txtbFreePeriod = By.xpath("//input[@placeholder='Free Period']");

	By btnDelete = By.xpath("//button[text()='Delete']");
	By txtbFrom = By.cssSelector("input[placeholder='Start']");
	By txtbEnd = By.cssSelector("input[placeholder='End']");
	By btnAdd = By.xpath("//button[text()='Add']");
	By btnSave = By.xpath("//button[text()='Save']");

	public boolean isAddNewTimingPageAvailable() {
		WebDriverExtension.waitForElementPresence(txtAddNewTiming);
		return WebElementExtension.isDisplayed(txtAddNewTiming);
	}

	public void selectDoctor(String docName) {
		WebElementExtension.click(btnSelectDoctor);
		WebDriverExtension.waitForElementVisible(lstDoctors(docName));
		WebElementExtension.click(lstDoctors(docName));
	}

	public void selectDate(String date) {
		WebElementExtension.click(btnDate(date));

	}

	public void deleteSession() {
		WebDriverExtension.waitForElementClickable(btnDelete);
		List<WebElement> allDeleteButton = WebElementExtension.getElements(btnDelete);
		for (int i = 0; i < allDeleteButton.size(); i++) {
			allDeleteButton.get(i).click();
		}

	}

	public void saveTiming() {
		WebDriverExtension.waitForElementClickable(btnSave);
		WebElementExtension.click(btnSave);
		WebDriverExtension.waitForAlertPresent();
		getDriver().switchTo().alert().accept();
	}

	public void addNewTiming(String from, String till) throws InterruptedException {
		if (WebElementExtension.getElement(btnDelete).isDisplayed()) {
			deleteSession();
			WebElementExtension.getElement(btnAdd).click();
			addValueInSession(from, till);
		} else {
			addValueInSession(from, till);
		}
	}

	private void addValueInSession(String from, String till) {
		WebDriverExtension.waitForElementClickable(txtbFrom);
		WebElementExtension.sendKeys(txtbFrom, from);
		WebElementExtension.sendKeys(txtbEnd, till);
	}

	public void clickOnPrescribeLink() {
		WebElementExtension.click(imgPrescribe);
	}

}
