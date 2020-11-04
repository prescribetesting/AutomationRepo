package com.heyprescribe.testcase;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.heyprescribe.base.BasePage;
import com.heyprescribe.base.Page;
import com.heyprescribe.data.Constants;
import com.heyprescribe.utill.DateTimeUtill;

public class EditSlotsTest extends BasePage {

	Page page;
	String accessionID;
	Properties prop;

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {
		prop = readProperties();
		browserInitialization(browser);
		page = new Page();
		DateTimeUtill.dateMonthYear();

	}

	@Test(priority = 1)
	public void verifytheAdminLoginFunctionality() {
		ExtentTest test = extent.createTest("Verify user is able to login in admin portal",
				"User should be able to login with valid credentials");
		setExtentTest(test);
		page.adminLogin.login(prop.getProperty("adminURL"), prop.getProperty("adminUserName"),
				prop.getProperty("adminPassword"));
		Assert.assertTrue(page.adminHomePage.userIcon(), "Verify the user icon after sucesful login");
	}

	@Test(priority = 2)
	public void verifyTheAdminPageAfterDeletingSession() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the time slot on admin page after deleting the session",
				"Deleted time slot should not available on admin home page");
		setExtentTest(test);
		page.adminHomePage.selectSubItem("Edit Slots");
		Assert.assertTrue(page.editSlots.isAddNewTimingPageAvailable(), "Verify user is on Edit Slot page");
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.todaysDate));
		page.editSlots.deleteSession();
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
		getDriver().navigate().refresh();
		page.adminHomePage.userIcon();
		Assert.assertFalse(
				page.adminHomePage.getAvailableDoctor(Constants.doctorOnAdmin)
						.contains(DateTimeUtill.date(DateTimeUtill.todaysDate)),
				"Verify the doctor availability on admin home page after deleting the session");
	}

	@Test(priority = 3)
	public void verifyAppointmentDateWhenDoctorIsOnLeaveOnDayOne() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the time slot on patient portal When Doctor is on leave on day one",
				"Only working day time slot should be available for the selected doctor");
		setExtentTest(test);
		goToUrl(prop.getProperty("patientappURL"));
		page.patientAppDasboard.clickOnBookAppointment();
		page.patientAppDasboard.selectDoctor(Constants.doctor);
		Assert.assertFalse(page.bookAppoitnment.VerifyDoctorAvailability(DateTimeUtill.todaysDate),
				"Verify The appointment date when doctor is on leave on day one");
	}

	@Test(priority = 4)
	public void verifyTheAdminPageAfterUpdatingSession() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the time slot on admin page after updating the session",
				"updating the session should be available on admin home page");
		setExtentTest(test);
		goToUrl(prop.getProperty("adminURL"));
		page.adminHomePage.selectSubItem("Edit Slots");
		Assert.assertTrue(page.editSlots.isAddNewTimingPageAvailable(), "Verify user is on Edit Slot page");
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.todaysDate));
		page.editSlots.addNewTiming("9:00", "11:00");
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
		getDriver().navigate().refresh();
		page.adminHomePage.userIcon();
		String details = page.adminHomePage.getAvailableDoctor(Constants.doctorOnAdmin);
		Assert.assertTrue(details.contains("9:00"),
				"Verify the doctor availability on admin home page after updating the session");
		Assert.assertTrue(details.contains("11:00"),
				"Verify the doctor availability on admin home page after updating the session");
	}

	@Test(priority = 5)
	public void verifyAppointmentDateWhenDoctorIsOnLeaveOnMidWeek() throws InterruptedException {
		ExtentTest test = extent.createTest(
				"Verify the time slot on patient portal When Doctor is on leave on mid week",
				"Only working day time slot should be available for the selected doctor");
		setExtentTest(test);
		goToUrl(prop.getProperty("adminURL"));
		page.adminHomePage.selectSubItem("Edit Slots");
		Assert.assertTrue(page.editSlots.isAddNewTimingPageAvailable(), "Verify user is on Edit Slot page");
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.WeekMidDate));
		page.editSlots.deleteSession();
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
		goToUrl(prop.getProperty("patientappURL"));
		page.patientAppDasboard.clickOnBookAppointment();
		page.patientAppDasboard.selectDoctor(Constants.doctor);
		Assert.assertFalse(page.bookAppoitnment.VerifyDoctorAvailability(DateTimeUtill.WeekMidDate),
				"Verify the appointment date when doctor is on leave on mid week");
		goToUrl(prop.getProperty("adminURL"));
		page.adminHomePage.selectSubItem("Edit Slots");
		page.editSlots.isAddNewTimingPageAvailable();
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.WeekMidDate));
		page.editSlots.addNewTiming("9:00", "11:00");
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
	}

	@Test(priority = 6)
	public void verifyAppointmentDateWhenDoctorIsOnLeaveOnWeekLastDate() throws InterruptedException {
		ExtentTest test = extent.createTest(
				"Verify the time slot on patient portal When Doctor is on leave on week last day",
				"Only working day time slot should be available for the selected doctor");
		setExtentTest(test);
		goToUrl(prop.getProperty("adminURL"));
		page.adminHomePage.selectSubItem("Edit Slots");
		Assert.assertTrue(page.editSlots.isAddNewTimingPageAvailable(), "Verify user is on Edit Slot page");
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.WeekLastDate));
		page.editSlots.deleteSession();
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
		goToUrl(prop.getProperty("patientappURL"));
		page.patientAppDasboard.clickOnBookAppointment();
		page.patientAppDasboard.selectDoctor(Constants.doctor);
		Assert.assertFalse(page.bookAppoitnment.VerifyDoctorAvailability(DateTimeUtill.WeekLastDate),
				"Verify the appointment date when doctor is on leave on week last day");
		goToUrl(prop.getProperty("adminURL"));
		page.adminHomePage.selectSubItem("Edit Slots");
		page.editSlots.isAddNewTimingPageAvailable();
		page.editSlots.selectDoctor(Constants.doctorOnAdmin);
		page.editSlots.selectDate(DateTimeUtill.date(DateTimeUtill.WeekLastDate));
		page.editSlots.addNewTiming("9:00", "11:00");
		page.editSlots.saveTiming();
		page.editSlots.clickOnPrescribeLink();
	}

	@AfterTest
	public void afterTest() {
		tearDown();
	}

}
