package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void navigateToRegistrationPage() {
		registrationPage = homePage.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object [][] registerData() {
		return new Object[][] {
			{"firstname01", "lastname01","1010101010","admin123","no"},
			{"firstname02", "lastname02","1010101011","admin123","yes"}
		};
	}
	
	@DataProvider
	public Object[][] registerDataExcel() {
		return ExcelUtil.getTestData("RegisterData");
	}
	
	@DataProvider 
	public Object[][] registerDataCSV() {
		return CsvUtil.getCSVData("Register");
	}
	
	@Test(dataProvider = "registerDataCSV")
	public void verifySuccessfulRegistration(String firstName, String lastName, String telePhone, String password, String suscribeValue) {
		Boolean regStatus = registrationPage.doRegistration(firstName, lastName, StringUtil.randomEmail(), telePhone, password, suscribeValue);
	Assert.assertTrue(regStatus);
	}

}
