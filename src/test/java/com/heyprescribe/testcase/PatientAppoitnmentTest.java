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
import com.heyprescribe.data.PatientInfo;
import com.heyprescribe.utill.ReadPropertiesFile;

public class PatientAppoitnmentTest extends BasePage {

	Page page;
	String accessionID;
	Properties prop;
	String confirmationDetails;
	String pdfContent;
	Properties prescribeData;

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {
		prop = readProperties();
		browserInitialization(browser);
		page = new Page();
		prescribeData = ReadPropertiesFile.propertyfile(prop.getProperty("prescribeDataFile"));
	}

	@Test(priority = 1)
	public void verifythePatientDashboard() throws Exception {
		ExtentTest test = extent.createTest("Verify the welcome message on book appoitnment dashboard",
				"Welcome to message should be display on book appoitnment dashboard");
		setExtentTest(test);
		goToUrl(prop.getProperty("patientappURL"));
		Assert.assertEquals(page.patientAppDasboard.getTitle(), Constants.patientAppHomePageTitle,
				"Verify Welcome message on Dashboard page");

	}

	@Test(priority = 2, dependsOnMethods = "verifythePatientDashboard")
	public void verifyPatientIsAbleToBookTheAppoitnment() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify user is able to book the Appoitnment",
				"A confirmation message should be appear after booking the appoitnment");
		setExtentTest(test);

		page.patientAppDasboard.clickOnBookAppointment();
		page.patientAppDasboard.selectDoctor(Constants.doctor);
		Constants.appointmentDateAndSlot = page.bookAppoitnment.getAppointmentDateAndSlot();
		page.bookAppoitnment.addingPatientDetail();
		page.bookAppoitnment.selectTermAndCondition();
		page.bookAppoitnment.clickOnConfirmBookingButton();
		Assert.assertEquals(page.appoitnmentConfirmation.confirmationMesage(), Constants.appoitnmentConfirmationMessage,
				"Verify Confirmation message on Appointment confirmation page");

	}

	@Test(priority = 3, dependsOnMethods = "verifyPatientIsAbleToBookTheAppoitnment")
	public void verifyThePatientInformationOnAppoitnmentConfirmationPage() {
		ExtentTest test = extent.createTest("Verify the patient information on appoitnment confirmation page",
				"Added patient information while booking the appoitnmen should be available on appoitnment confirmation page");
		setExtentTest(test);

		confirmationDetails = page.appoitnmentConfirmation.confirmationDetails().replaceAll("\n", " ");
		Assert.assertTrue(confirmationDetails.contains(PatientInfo.patientName),
				"Verify the patient name on appointment confirmation page");
	}

	@Test(priority = 4, dependsOnMethods = "verifyThePatientInformationOnAppoitnmentConfirmationPage")
	public void verifyTheDoctorNameOnAppoitnmentConfirmationpage() {
		ExtentTest test = extent.createTest("Verify the Doctor name on confirmation page",
				"Selected doctor name should be available on appoitnment confirmation page");
		setExtentTest(test);

		Assert.assertTrue(confirmationDetails.toUpperCase().contains(Constants.doctor),
				"Verify the doctor name on appointment confirmation page");

	}

	@Test(priority = 5, dependsOnMethods = "verifyTheDoctorNameOnAppoitnmentConfirmationpage")
	public void verifyTheDateAndSlotOnAppointmentConfirmationPage() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the selected Date and Slot on appointment confirmation page",
				"Selected Date and Slot while booking the appoitnmen should be available on appointment confirmation page");
		setExtentTest(test);

		// Assert.assertTrue(Arrays.equals(Constants.appointmentDateAndSlot,
		// page.appoitnmentConfirmationPage.getAppointmentDateAndSlot()));

		Assert.assertTrue(confirmationDetails.contains(Constants.appointmentDateAndSlot[0]),
				"Verify the selected Date and Slot on appointment confirmation page");

		// Assert.assertTrue(confirmationDetails.contains(Constants.appointmentDateAndSlot[1]),
		// "Verify the Appointment Slot on Confirmation page");

	}

	@Test(priority = 6, dependsOnMethods = "verifyTheDateAndSlotOnAppointmentConfirmationPage")
	public void verifyTheVideoLinkOnAppointmentConfirmationPage() {
		ExtentTest test = extent.createTest("Verify the video link on appointment confirmation page ",
				"A link for video call should be available on appointment confirmation page");
		setExtentTest(test);

		Assert.assertEquals(page.appoitnmentConfirmation.checkVideoLink(), true,
				"Verify the video link on appointment confirmation page");

	}

	@Test(priority = 7, dependsOnMethods = "verifyTheVideoLinkOnAppointmentConfirmationPage")
	public void verifyTheUploadedFileOnAppointmentConfirmationPage() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the uploaded file name on appointment confirmation page",
				"Uploaded file name should be available on appointment confirmation page");
		setExtentTest(test);

		// Assert.assertTrue(
		// page.appoitnmentConfirmation.UploadPrescriptionFileToPage(prop.getProperty("PrescriptionFile")),
		// "Verify the uploaded file name on appointment confirmation page");
	}

	@Test(priority = 8, dependsOnMethods = "verifyTheUploadedFileOnAppointmentConfirmationPage")
	public void verifyTheSucesMsgAfterUploadingTheFile() {
		ExtentTest test = extent.createTest(
				"Verify the Sucess Message after uploading the file on appointment confirmation page",
				"Document uploaded Successfully! message should be available on appointment confirmation page");
		setExtentTest(test);

		// Assert.assertTrue(page.appoitnmentConfirmation.getDocumentUploadConfirmationMsg(Constants.fileUploadedMsg),
		// "Verify the file processing confirmation message on appointment
		// confirmation page");

	}

	@Test(priority = 9, dependsOnMethods = "verifyTheSucesMsgAfterUploadingTheFile")
	public void verifyTheEmailConfirmationOnGmail() {
		ExtentTest test = extent.createTest("Verify the email confirmation after submit the appointment",
				"User should be receive the email after sucessfully book the appointment");
		setExtentTest(test);

		page.emailUtill.gmailLogin(prop.getProperty("gmailURL"), prop.getProperty("gmailUserName"),
				prop.getProperty("gmailPassword"));
		page.emailUtill.selectEmail();
		Assert.assertTrue(page.emailUtill.verifyBookingConfirmation(PatientInfo.patientName),
				"Verify the email confirmation after submit the appointment");

	}

	@Test(priority = 10, dependsOnMethods = "verifyTheEmailConfirmationOnGmail")
	public void verifyThePrescribeLogin() {
		ExtentTest test = extent.createTest("Verify user is able to login the Prescribe application",
				"User should be able to login with valid credentials");
		setExtentTest(test);

		page.prescribeLogin.login(prop.getProperty("webappURL"), prop.getProperty("webappUserName"),
				prop.getProperty("webappPassword"));
		Assert.assertTrue(page.allAppointments.checkAllAppointmentLink(),
				"Verify the All Appointment Link after sucesful login");
	}

	@Test(priority = 11, dependsOnMethods = "verifyThePrescribeLogin")
	public void verifyThePatientAppointmenOnAllAppointmenPage() throws InterruptedException {
		ExtentTest test = extent.createTest("Verify the patient appointment on All Appointmen page",
				"Patient appointment record should be available under All Appointmen worklist");
		setExtentTest(test);

		Assert.assertTrue(page.allAppointments.checkPatientAppointmentRecord(PatientInfo.patientName,
				Integer.toString(PatientInfo.age)), "Verify the patient appointment on All Appointmen page");
	}

	@Test(priority = 12, dependsOnMethods = "verifyThePatientAppointmenOnAllAppointmenPage")
	public void verifyTheConnectToVideoPopUpOnPrescribePage() {
		ExtentTest test = extent.createTest("Verify the connect to Video popup on Prescribe page",
				"Video Popup should be available on Prescribe page");
		setExtentTest(test);

		page.allAppointments.selectPrescribe(PatientInfo.patientName, Integer.toString(PatientInfo.age));
		Assert.assertTrue(page.prescribe.isCloseVideoButtonDisplay(),
				"Verify the connect to Video popup on Prescribe page");
	}

	@Test(priority = 13, dependsOnMethods = "verifyTheConnectToVideoPopUpOnPrescribePage")
	public void verifyThePatientDetailOnPrescribePage() {
		ExtentTest test = extent.createTest("Verify the patient name, gender and age on Prescribe page",
				"Patient name, gender and age on Prescribe Page should be same as user enter while booking the Appointmen");
		setExtentTest(test);

		page.prescribe.closeVideo();
		String patientInfo = page.prescribe.getPatientInfo().replaceAll("\n", " ");
		Assert.assertTrue(patientInfo.contains(PatientInfo.patientName.toUpperCase()),
				"Verify the patient name on Prescribe page");
		Assert.assertTrue(patientInfo.contains(PatientInfo.sex), "Verify the patient gender on Prescribe page");
		Assert.assertTrue(patientInfo.contains(Integer.toString(PatientInfo.age)),
				"Verify the patient age on Prescribe page");
	}

	@Test(priority = 14, dependsOnMethods = "verifyThePatientDetailOnPrescribePage")
	public void verifyThePatientUploadedPrescriptionFile() {
		ExtentTest test = extent.createTest("Verify the patient uploaded prescription file on Prescribe page",
				"Patient uploaded prescription file should be availableon Prescribe page");
		setExtentTest(test);
		// Assert.assertTrue(page.prescribe.checkRecordButon(), "Verify the
		// record button on Prescribe page");
		// Assert.assertTrue(page.prescribe.verifyRecords(), "Verify the
		// uploaded Prescription File on Prescribe page");
	}

	@Test(priority = 15, dependsOnMethods = "verifyThePatientUploadedPrescriptionFile")
	public void saveThePhysicianPrescription() {
		ExtentTest test = extent.createTest("Verify the comfirmation message after save the Prescription reporte",
				"Submitted Successfully... mesage should be appear after click on save buton");
		setExtentTest(test);

		page.prescribe.addVitals();
		page.prescribe.addSymptoms();
		page.prescribe.addDiagnosis();
		page.prescribe.addMedicine();
		page.prescribe.addAdvice();
		page.prescribe.addTestsRequested();
		Assert.assertTrue(page.prescribe.savePrescription(Constants.savePrescriptionMsg),
				"Verify the comfirmation message after save the Prescription reporte");

	}

	@Test(priority = 16, dependsOnMethods = "saveThePhysicianPrescription")
	public void verifyThePrescriptionReport() {
		ExtentTest test = extent.createTest("Verify the Physician Prescription report on Prescribe page",
				"Physician Prescription report should be generated on clicking the Generate Prescription button");
		setExtentTest(test);
		Assert.assertTrue(page.prescribe.openPrescriptionPdf().contains(Constants.appointmentDateAndSlot[0]),
				"Verify the appointment Date on Physician Prescription reporte");

	}

	@Test(priority = 17, dependsOnMethods = "verifyThePrescriptionReport")
	public void verifyTheGeneratePrescriptionReportFunctionality() {
		ExtentTest test = extent.createTest("Verify the sucess mesage after generating the Prescription Report",
				"Successfully sent prescription to the patient! mesage should be appear after click on confirm Send buton");
		setExtentTest(test);

		Assert.assertTrue(page.prescribe.generateThePrescriptionPdf(Constants.sentPrescriptionMsg),
				"Verify the sucess mesage after generating the Prescription Report");
	}

	@Test(priority = 18, dependsOnMethods = "verifyTheGeneratePrescriptionReportFunctionality")
	public void verifyThePrescriptionReportDownloadLink() {
		ExtentTest test = extent.createTest("Verify the Prescription Report Download Link on Prescribe page",
				"A link should be available to download the Prescription Report");
		setExtentTest(test);
		Assert.assertTrue(page.prescribe.prescriptionDownloadLink(),
				"Verify the Prescription Report Download Link on Prescribe page");
	}

	@Test(priority = 19, dependsOnMethods = "verifyThePrescriptionReportDownloadLink")
	public void verifyThePatientDetailOnPdfFile() throws Exception {
		ExtentTest test = extent.createTest("Verify the patient Detail On Pdf File",
				"Patient details should be same as entered while booking the appointment");
		setExtentTest(test);

		page.prescribe.clickOnPrescriptionDownloadLink();
		switchTab(1);
		pdfContent = page.emailUtill.readPDFContent(driver.getCurrentUrl());
		Assert.assertTrue(pdfContent.contains(PatientInfo.patientName), "Verify the patient name on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(PatientInfo.sex), "Verify the patient gender on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(Integer.toString(PatientInfo.age)),
				"Verify the patient age on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(PatientInfo.emailId), "Verify the patient email Id on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(PatientInfo.address), "Verify the patient address on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(Integer.toString(PatientInfo.phoneNo)),
				"Verify the patient phone number on Prescription pdf");

	}

	@Test(priority = 20, dependsOnMethods = "verifyThePatientDetailOnPdfFile")
	public void verifyThePrescriptionDetailOnPdfFile() throws Exception {
		ExtentTest test = extent.createTest("Verify the prescription detail On Pdf File",
				"prescription detail should be same as entered by physician");
		setExtentTest(test);

		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("bph")), "Verify the bph on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("bpl")), "Verify the bpl on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("weight")),
				"Verify the weight on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("height")),
				"Verify the height on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("symptoms")),
				"Verify the symptoms Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("diagnosis")),
				"Verify the diagnosis on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("medicine")),
				"Verify the medicine on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("dosages")),
				"Verify the dosages on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("dosagesTime")),
				"Verify the dosagesTime on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("durationInDays")),
				"Verify the durationInDays on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("instructions")),
				"Verify the instructions on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("advice")),
				"Verify the advice on Prescription pdf");
		Assert.assertTrue(pdfContent.contains(prescribeData.getProperty("testsRequested")),
				"Verify the testsRequested on Prescription pdf");

	}

	@Test(priority = 21, dependsOnMethods = "verifyThePrescriptionDetailOnPdfFile")
	public void verifyTheStatusAfterGeneratingThePrescription() throws InterruptedException {
		ExtentTest test = extent.createTest(
				"Verify the Status on all Prescription worklist after generating the prescription",
				"Status should be updated as complete");
		setExtentTest(test);

		page.prescribe.backToAllAppointmentPage();
		Assert.assertTrue(page.allAppointments.checkPatientAppointmentRecord(PatientInfo.patientName,
				Integer.toString(PatientInfo.age)), "Verify the patient appointment on All Appointmen page");
		Assert.assertTrue(
				page.allAppointments.getStatus(PatientInfo.patientName, Integer.toString(PatientInfo.age),
						Constants.completeStatus),
				"Verify the Status on all Prescription worklist after generating the prescription");
	}

	@AfterTest
	public void afterTest() {
		tearDown();
	}

}
