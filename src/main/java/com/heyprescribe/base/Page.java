package com.heyprescribe.base;

import com.heyprescribe.page.AdminHomePage;
import com.heyprescribe.page.AdminLogin;
import com.heyprescribe.page.AllAppointments;
import com.heyprescribe.page.AppoitnmentConfirmation;
import com.heyprescribe.page.BookAppoitnment;
import com.heyprescribe.page.EditSlots;
import com.heyprescribe.page.PatientAppDasboard;
import com.heyprescribe.page.Prescribe;
import com.heyprescribe.page.PrescribeLogin;
import com.heyprescribe.utill.EmailUtill;
import com.heyprescribe.utill.PDFReader;

public class Page extends BasePage {

	public PatientAppDasboard patientAppDasboard;
	public BookAppoitnment bookAppoitnment;
	public AppoitnmentConfirmation appoitnmentConfirmation;
	public PrescribeLogin prescribeLogin;
	public AllAppointments allAppointments;
	public Prescribe prescribe;
	public EmailUtill emailUtill;
	public PDFReader pdfReader;
	public AdminLogin adminLogin;
	public AdminHomePage adminHomePage;
	public EditSlots editSlots;

	public Page() {
		patientAppDasboard = new PatientAppDasboard();
		bookAppoitnment = new BookAppoitnment();
		appoitnmentConfirmation = new AppoitnmentConfirmation();
		prescribeLogin = new PrescribeLogin();
		allAppointments = new AllAppointments();
		prescribe = new Prescribe();
		emailUtill = new EmailUtill();
		pdfReader = new PDFReader();
		adminLogin = new AdminLogin();
		adminHomePage = new AdminHomePage();
		editSlots = new EditSlots();

	}

}
