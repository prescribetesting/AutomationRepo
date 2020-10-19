package com.heyprescribe.page;

import java.util.Properties;

import org.openqa.selenium.By;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.utill.ReadPropertiesFile;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class Prescribe extends BasePage {
	Properties prescribeData;

	public Prescribe() {
		Properties prop = readProperties();
		prescribeData = ReadPropertiesFile.propertyfile(prop.getProperty("prescribeDataFile"));
	}

	By arrowLeft = By.cssSelector("svg[data-icon='arrow-left']");
	By btnVideoClose = By.cssSelector("svg[data-icon='close']");
	By lblHeading = By.cssSelector("div[class*='header-heading-left']");
	By btnVitals = By.cssSelector("img[src*='vitalExpand']");
	By txbBph = By.cssSelector("input[name='bp1']");
	By txbBpl = By.cssSelector("input[name='bp2']");
	By txbWeight = By.cssSelector("input[name='weight']");
	By txbPulse = By.cssSelector("input[name='pulse']");
	By txbTemperature = By.cssSelector("input[name='temperature']");
	By txbHeight = By.cssSelector("input[name='height']");
	By txbSymptoms = By.id("rc_select_0");
	By txbDiagnosis = By.id("rc_select_1");
	By btnAddRow = By.xpath("//span[text()='Add row']");
	By txbAddMedicine = By.id("rc_select_3");
	By txbDosages = By.cssSelector("input[list='dosages']");
	By txbWhen = By.cssSelector("input[placeholder='Choose a time']");
	By txbDurationInDays = By.cssSelector("input[placeholder*='Add Number of days']");
	By txbInstructions = By.cssSelector("input[placeholder='Add an Instruction']");
	By txbAdvice = By.cssSelector("textarea[name='advice']");
	By txbTestsRequested = By.id("rc_select_2");
	By btnGeneratePrescription = By.cssSelector("button span.anticon.anticon-send");
	By lblDialogTitle = By.cssSelector(".ant-modal-title");
	By btnSave = By.xpath("//span[text()='Save']");
	By msgSave = By.className("ant-message-notice-content");
	By btnConfirmSend = By.cssSelector(".ant-modal-title div button:first-child");
	By lnkprescription = By.cssSelector("h3.ant-typography a");
	By btnRecords = By.cssSelector("span[aria-label='paper-clip'] svg");
	By btnCloseIcon = By.cssSelector("span[class*='close-icon']");

	public boolean isCloseVideoButtonDisplay() {
		WebDriverExtension.waitForElementVisible(btnVideoClose);
		return WebElementExtension.getElement(btnVideoClose).isDisplayed();
	}

	public void closeVideo() {
		WebElementExtension.click(btnVideoClose);

	}

	public boolean checkRecordButon() {

		return WebElementExtension.getElement(btnRecords).isDisplayed();
	}

	public boolean verifyRecords() {
		WebElementExtension.click(btnRecords);
		boolean hearderTxt = WebElementExtension.getElement(lblDialogTitle).isDisplayed();
		WebElementExtension.click(btnCloseIcon);
		return hearderTxt;
	}

	public String getPatientInfo() {
		WebDriverExtension.waitForElementVisible(lblHeading);
		return WebElementExtension.getText(lblHeading);

	}

	public void addVitals() {
		WebElementExtension.click(btnVitals);
		WebDriverExtension.waitForElementVisible(txbBph);
		WebElementExtension.sendKeys(txbBph, prescribeData.getProperty("bph"));
		WebElementExtension.sendKeys(txbBpl, prescribeData.getProperty("bpl"));
		WebElementExtension.sendKeys(txbWeight, prescribeData.getProperty("weight"));
		WebElementExtension.sendKeys(txbPulse, prescribeData.getProperty("pulse"));
		WebElementExtension.sendKeys(txbTemperature, prescribeData.getProperty("temperature"));
		WebElementExtension.sendKeys(txbHeight, prescribeData.getProperty("height"));
	}

	public void addSymptoms() {
		WebElementExtension.getElement(txbSymptoms).sendKeys(prescribeData.getProperty("symptoms"));
	}

	public void addDiagnosis() {
		WebElementExtension.getElement(txbDiagnosis).sendKeys(prescribeData.getProperty("diagnosis"));
	}

	public void addMedicine() {
		WebElementExtension.click(btnAddRow);
		WebDriverExtension.waitForElementVisible(txbAddMedicine);
		WebElementExtension.sendKeys(txbAddMedicine, prescribeData.getProperty("medicine"));
		WebElementExtension.sendKeys(txbDosages, prescribeData.getProperty("dosages"));
		WebElementExtension.sendKeys(txbWhen, prescribeData.getProperty("dosagesTime"));
		WebElementExtension.sendKeys(txbDurationInDays, prescribeData.getProperty("durationInDays"));
		WebElementExtension.sendKeys(txbInstructions, prescribeData.getProperty("instructions"));

	}

	public void addAdvice() {
		WebElementExtension.sendKeys(txbAdvice, prescribeData.getProperty("advice"));
	}

	public void addTestsRequested() {
		WebElementExtension.sendKeys(txbTestsRequested, prescribeData.getProperty("testsRequested"));
	}

	public Boolean savePrescription(String saveMsg) {
		WebElementExtension.click(btnSave);
		return WebDriverExtension.waitForTextPresent(msgSave, saveMsg);

	}

	public String openPrescriptionPdf() {
		WebElementExtension.click(btnGeneratePrescription);
		WebDriverExtension.waitForElementVisible(lblDialogTitle);
		return WebElementExtension.getElement(lblDialogTitle).getText();

	}

	public boolean generateThePrescriptionPdf(String sentPrescriptionMsg) {

		WebElementExtension.click(btnConfirmSend);
		boolean msgStatus = WebDriverExtension.waitForTextPresent(msgSave, sentPrescriptionMsg);
		WebElementExtension.click(btnCloseIcon);
		return msgStatus;
	}

	public boolean prescriptionDownloadLink() {
		return WebElementExtension.getElement(lnkprescription).isDisplayed();
	}

	public void clickOnPrescriptionDownloadLink() {
		WebElementExtension.click(lnkprescription);
	}

	public void backToAllAppointmentPage() {
		WebElementExtension.click(arrowLeft);
	}
}
