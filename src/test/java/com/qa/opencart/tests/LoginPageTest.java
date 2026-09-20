package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-02 : Login Page")
@Feature("FE-02 : Login page features")
@Story("US-02 : Login Page - Check Title, URL, Logo, New Customer section heading and Right navigation links")
public class LoginPageTest extends BaseTest {
	
	@Description("Navigate from Home page to Login page")
	@BeforeClass
	public void loginPageSetup() {
		 loginPage = homePage.navigateToLogin();
	}

	@Description("Login Page - Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyLoginPageTitle() {
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login Page Title : "+ actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("Login Page - partialURL Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyLoginPageURL() {
		String actualUrl = loginPage.getLoginPageUrl();
		ChainTestListener.log("Login Page URL : "+actualUrl);
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_PARTIAL_URL));
	}

	@Description("Login Page - Application Logo Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyLogoDisplayed() {
		Assert.assertTrue(loginPage.isLogoExist());
	}

	@Description("Login Page - New Customer section heading Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyNewCustomerSectionDisplayed() {
		Assert.assertTrue(loginPage.isNewCustomerHeaderExist());
	}

	@Description("Login Page - Right Navigation links Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyRightNavigationLinks() {
		List<String> actRightNavLinks = loginPage.rightNavigationLinks();
		ChainTestListener.log("Right Nav links on Login Page : "+actRightNavLinks);
		Assert.assertEquals(actRightNavLinks, AppConstants.rightNavBeforeLoginList);
	}
	
	



}
