package com.heyprescribe.page;

import org.openqa.selenium.By;

import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class AppoitnmentConfirmation {

	By msgConfirmation = By.className("pay-text");
	By msgConfirmationDetail = By.cssSelector(".main-para");
	By msgConsultationDate = By.cssSelector(".time-date b:first-child");
	By msgSlot = By.cssSelector(".time-date b:last-child");

	By btnUploadPrescription = By.cssSelector("input[type='file']");
	By txtFileName = By.cssSelector(".file-name p");
	By btnUploadedPrescription = By.cssSelector(".upload-btn svg");
	By msgUploadMsg = By.cssSelector("h2.swal2-title");
	By btnOk = By.cssSelector("swal2-confirm swal2-styled");

	By lnkVideoLink = By.cssSelector(".video-btn");
	By btnClose = By.cssSelector("close-btn");

	public String confirmationMesage() {
		WebDriverExtension.waitForElementVisible(msgConfirmation);
		return WebElementExtension.getText(msgConfirmation);
	}

	public String confirmationDetails() {
		WebDriverExtension.waitForElementVisible(msgConfirmationDetail);
		return WebElementExtension.getText(msgConfirmationDetail);
	}

	public boolean UploadPrescriptionFileToPage(String file) throws InterruptedException {

		String filePath = System.getProperty("user.dir") + file;
		WebElementExtension.getElement(btnUploadPrescription).sendKeys(filePath);
		Thread.sleep(3000);
		WebDriverExtension.waitForElementVisible(txtFileName);
		return WebElementExtension.getElement(txtFileName).isDisplayed();
	}

	public String[] getAppointmentDateAndSlot() {
		String[] appointment = { WebElementExtension.getText(msgConsultationDate),
				WebElementExtension.getText(msgSlot) };
		return appointment;
	}

	public Boolean getDocumentUploadConfirmationMsg(String msg) {
		WebElementExtension.click(btnUploadedPrescription);
		return WebDriverExtension.waitForTextPresent(msgUploadMsg, msg);

	}

	public boolean checkVideoLink() {
		return WebElementExtension.getElement(lnkVideoLink).isDisplayed();
	}

}
