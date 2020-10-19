package com.heyprescribe.utill;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;

import com.heyprescribe.base.BasePage;

public class EmailUtill extends BasePage {

	By txtbUserName = By.cssSelector("input[type='email']");
	By txtbPassword = By.cssSelector("input[type='password']");
	By btnUnNext = By.cssSelector("#identifierNext > div > button");
	By btnPwdNext = By.cssSelector("#passwordNext > div > button");
	By lnkInbox = By.cssSelector("span.nU a[href*='inbox']");
	By lstAllEmail = By.xpath("//td[contains(@class,'a4W')]");
	By lstBookingConfirmationEmail = By.cssSelector("span[role='gridcell'][class='gD']");

	private By msgDetails(String patientName) {
		return By.xpath("//h2[contains(text(),'" + patientName + "')]");
	}

	public void gmailLogin(String url, String userName, String password) {

		driver.get(url);
		WebDriverExtension.waitForElementVisible(txtbUserName);
		WebElementExtension.sendKeys(txtbUserName, userName);
		WebDriverExtension.waitForElementClickable(btnUnNext);
		WebElementExtension.click(btnUnNext);
		WebDriverExtension.waitForElementVisible(txtbPassword);
		WebElementExtension.sendKeys(txtbPassword, password);
		WebDriverExtension.waitForElementClickable(btnPwdNext);
		WebElementExtension.click(btnPwdNext);
	}

	public void selectEmail() {
		WebElementExtension.jsWaitForPageLoad();
		WebDriverExtension.waitForElementVisible(lnkInbox);
		WebElementExtension.getElements(lstAllEmail).get(0).click();
	}

	public boolean verifyBookingConfirmation(String patientName) {
		return WebElementExtension.getElement(msgDetails(patientName)).isDisplayed();
	}

	public String readPDFContent(String appUrl) throws Exception {
		System.out.println("url +" + appUrl);
		URL url = new URL(appUrl);
		InputStream input = url.openStream();
		BufferedInputStream fileToParse = new BufferedInputStream(input);
		PDDocument document = null;
		String output = null;

		try {
			document = PDDocument.load(fileToParse);
			output = new PDFTextStripper().getText(document);
			System.out.println(output);

		} finally {
			if (document != null) {
				document.close();
			}
			fileToParse.close();
			// is.close();
		}
		return output;
	}
	// List<WebElement> allBookingConfirmationEmail =
	// WebElementExtension.getElements(lstBookingConfirmationEmail);
	// int bceSize = allBookingConfirmationEmail.size();
	// boolean recordFound = false;
	// if (bceSize != 0) {
	// for (int i = 0; i < bceSize; i++) {
	// allBookingConfirmationEmail.get(i).click();
	// if
	// (WebElementExtension.getElement(msgDetails(patientName)).isDisplayed()) {
	// recordFound = true;
	// break;
	// }
	// }
	//
	// } else {
	// WebElementExtension.getElement(msgDetails(patientName)).isDisplayed();
	// recordFound = true;
	// }
	// return recordFound;
	// }

}
