package com.heyprescribe.page;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.heyprescribe.base.BasePage;
import com.heyprescribe.data.PatientInfo;
import com.heyprescribe.utill.ReadPropertiesFile;
import com.heyprescribe.utill.WebDriverExtension;
import com.heyprescribe.utill.WebElementExtension;

public class BookAppoitnment extends BasePage {
	Properties patientDetail;

	public BookAppoitnment() {
		Properties prop = readProperties();
		patientDetail = ReadPropertiesFile.propertyfile(prop.getProperty("patientDetailFile"));
		PatientInfo.details();
	}

	By lstAppointmentDate = By.cssSelector("select[name='user_date']");
	By lstAllAppointmentDate = By.cssSelector("select[name='user_date'] option");

	By lstAvailableSlot = By.cssSelector("select[name='user_slot']");
	By txtFirstName = By.cssSelector("input[name='first_name']");
	By txtlastName = By.cssSelector("input[name='last_name']");
	By txtPhoneNumber = By.cssSelector("input[name='phone']");
	By lstSex = By.cssSelector("select[name='sex']");
	By txtAge = By.cssSelector("input[name='age']");
	By lstFollowup = By.cssSelector("select[name='followup']");
	By txtEmail = By.cssSelector("input[name='email']");
	By txtAddress = By.cssSelector("input[name='address']");
	By chkTermAndCondition = By.cssSelector("input[type='checkbox']");
	By btnConfirmBooking = By.cssSelector("button[type='submit']");

	String appointmentDate;
	String appointmenSlot;

	// public void addingPatientDetail() {
	//
	// WebElementExtension.sendKeys(txtFirstName,
	// patientDetail.getProperty("firstName"));
	// WebElementExtension.sendKeys(txtlastName,
	// patientDetail.getProperty("lastName"));
	// WebElementExtension.sendKeys(txtPhoneNumber,
	// patientDetail.getProperty("phoneNumber"));
	// WebElementExtension.selectByText(lstSex,
	// patientDetail.getProperty("sex"));
	// WebElementExtension.sendKeys(txtAge, patientDetail.getProperty("age"));
	// WebElementExtension.selectByText(lstFollowup,
	// patientDetail.getProperty("followup"));
	// WebElementExtension.sendKeys(txtEmail,
	// patientDetail.getProperty("emailId"));
	// WebElementExtension.sendKeys(txtAddress,
	// patientDetail.getProperty("address"));
	//
	// }

	public String[] getAppointmentDateAndSlot() throws InterruptedException {
		Thread.sleep(3000);
		appointmenSlot = WebElementExtension.selectByFirstIndex(lstAvailableSlot).getText();
		if (appointmenSlot.contains("No Slots Available, Kindly Change Booking Date.")) {
			WebElementExtension.selectByIndex(lstAppointmentDate, 1);
			appointmentDate = WebElementExtension.selectByFirstIndex(lstAppointmentDate).getText();
			Thread.sleep(3000);
			WebDriverExtension.waitForElementVisible(lstAvailableSlot);
			appointmenSlot = WebElementExtension.selectByFirstIndex(lstAvailableSlot).getText();
			String[] appointment = { appointmentDate, appointmenSlot };
			return appointment;
		} else {
			WebDriverExtension.waitForElementVisible(lstAppointmentDate);
			appointmentDate = WebElementExtension.selectByFirstIndex(lstAppointmentDate).getText();
			WebDriverExtension.waitForElementVisible(lstAvailableSlot);
			appointmenSlot = WebElementExtension.selectByFirstIndex(lstAvailableSlot).getText();
			String[] appointment = { appointmentDate, appointmenSlot };
			return appointment;
		}
	}

	public boolean VerifyDoctorAvailability(String appointmentDate) throws InterruptedException {
		WebElementExtension.getElement(lstAppointmentDate).click();
		List<WebElement> allAppointmentDate = WebElementExtension.getElements(lstAllAppointmentDate);
		boolean isFound = false;
		for (int i = 0; i < allAppointmentDate.size(); i++) {
			String date = allAppointmentDate.get(i).getText();
			if (appointmentDate.equals(date))

				isFound = true;
		}
		return isFound;
	}

	public void addingPatientDetail() {

		WebElementExtension.sendKeys(txtFirstName, PatientInfo.firstName);
		WebElementExtension.sendKeys(txtlastName, PatientInfo.lastName);
		WebElementExtension.sendKeys(txtPhoneNumber, Integer.toString(PatientInfo.phoneNo));
		WebElementExtension.selectByText(lstSex, PatientInfo.sex);
		WebElementExtension.sendKeys(txtAge, Integer.toString(PatientInfo.age));
		// WebElementExtension.selectByText(lstFollowup,
		// patientDetail.getProperty("followup"));
		WebElementExtension.sendKeys(txtEmail, PatientInfo.emailId);
		WebElementExtension.sendKeys(txtAddress, PatientInfo.address);
	}

	public void selectTermAndCondition() {
		WebElementExtension.click(chkTermAndCondition);
	}

	public void clickOnConfirmBookingButton() {
		WebElementExtension.click(btnConfirmBooking);
	}

}
