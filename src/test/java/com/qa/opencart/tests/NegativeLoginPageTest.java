package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-02 : Login Page")
@Feature("FE-02 : Login page features")
@Story("US-03 : Login Page - Check validation message in case of invalid credentials")
public class NegativeLoginPageTest extends BaseTest{
	
	@BeforeClass
	public void navigateToLoginPage() {
		loginPage = homePage.navigateToLogin();
	}
	
	
	@DataProvider
	public Object[][] invalidCredentialsData(){
		return new Object[][] {
			{"adaaafaf@afafaf", "asafaf"},
			{"hhjkk@sa", ""},
			{"", "sdad2222"},
			{"",""}
		};
	}
	
	@Description("Login with invalid credentails to verify error message")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test(dataProvider = "invalidCredentialsData")
	public void verifyLoginErrorInvalidCredentials(String username, String password) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(username, password));
	}


}
