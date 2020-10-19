package com.heyprescribe.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class AllAppointments {

	By lnkAllAppointments = By.cssSelector("a[href='/']");
	By dpChangeDate = By.cssSelector("input[placeholder='Select date']");

	private By btnPrescribe(String pName, String age) {
		return By.xpath("//tr//td//span[text()='" + pName + "']//ancestor::tr//td//span[text()='" + age
				+ "']//ancestor::tr//td//button//img");
	}

	private By lblStatus(String pName, String age) {
		return By.xpath("//tr//td//span[text()='" + pName + "']//ancestor::tr//td//span[text()='" + age
				+ "']//ancestor::tr//td[@class='ant-table-cell']");
	}

	By lblHeading = By.cssSelector("div[class*='header-heading-left']");

	public boolean checkAllAppointmentLink() {
		WebDriverExtension.waitForElementVisible(lnkAllAppointments);
		return WebElementExtension.getElement(lnkAllAppointments).isDisplayed();

	}

	public void selectDate(String date) {

		WebDriverExtension.waitForElementClickable(dpChangeDate);
		WebElementExtension.getElement(dpChangeDate).sendKeys(Keys.CONTROL + "a");
		WebElementExtension.getElement(dpChangeDate).sendKeys(date);
		WebElementExtension.click(lnkAllAppointments);
	}

	public boolean checkPatientAppointmentRecord(String pName, String age) throws InterruptedException {
		Thread.sleep(3000);
		return WebElementExtension.getElement(btnPrescribe(pName, age)).isDisplayed();
	}

	public void selectPrescribe(String pName, String age) {
		WebElementExtension.click(btnPrescribe(pName, age));
	}

	public boolean getStatus(String pName, String age, String statusValue) {
		WebDriverExtension.waitForAllElementPresence(lblStatus(pName, age));
		List<WebElement> allElement = WebElementExtension.getElements(lblStatus(pName, age));
		boolean sts = false;
		for (int i = 0; i < allElement.size(); i++) {
			WebElement status = allElement.get(i);
			if (status.getText().contains(statusValue)) {
				sts = true;
				return sts;
			}
		}
		return sts;

	}
}
