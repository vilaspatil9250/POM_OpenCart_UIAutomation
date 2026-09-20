package com.qa.opencart.utils;

public class StringUtil {
	
	//method to generate random and unique email address by adding current time in milliseconds in email string
	public static String randomEmail() {
		String randomEmail = "uiautomation"+System.currentTimeMillis()+"@test.com";
		return randomEmail;
	}

}
