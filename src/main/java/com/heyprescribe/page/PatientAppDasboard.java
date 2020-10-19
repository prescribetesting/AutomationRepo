package com.heyprescribe.page;

import org.openqa.selenium.By;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class PatientAppDasboard extends BasePage {

	By header = By.cssSelector(".header");
	By bookAppointment = By.className("book");

	By hospitalAddress = By.cssSelector(".hospital-address");

	private By btnSelectDoctor(String doctorName) {
		return By.xpath("//div[text()='" + doctorName + "']//parent::div//child::div[@class='btn-text']//a");
	}

	public String getTitle() {
		WebDriverExtension.waitForElementVisible(header);
		return WebElementExtension.getText(header);
	}

	public String getHospitalAddress() {
		WebDriverExtension.waitForElementPresence(hospitalAddress);
		return WebElementExtension.getText(hospitalAddress);

	}

	public void clickOnBookAppointment() {
		WebDriverExtension.waitForElementClickable(bookAppointment);
		WebElementExtension.click(bookAppointment);
	}

	public void selectDoctor(String doctor) {
		WebDriverExtension.waitForElementClickable(btnSelectDoctor(doctor));
		WebElementExtension.click(btnSelectDoctor(doctor));
	}

}
