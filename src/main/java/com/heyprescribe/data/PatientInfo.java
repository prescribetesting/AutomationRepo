package com.heyprescribe.data;

import com.github.javafaker.Faker;

public class PatientInfo {
	public static String firstName;
	public static String lastName;
	public static String patientName;
	public static String address;
	public static int phoneNo = 1234567890;
	public static int age;
	public static String sex = "Male";
	public static String emailId = "prescribetesting@gmail.com";

	public static void details() {
		Faker faker = new Faker();
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		patientName = firstName + " " + lastName;
		address = faker.address().streetAddress();
		age = (int) (Math.random() * (100 - 30 + 1) + 30);
		;
	}
}
