package com.qa.opencart.tests;

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

@Epic("EP-03 : My Account Page")
@Feature("FE-03 : My Account page features")
@Story("US-03 : My Acount Page - Check Title, URL, Logo, Top Menu List, Section headings and Right navigation links")
public class MyAccountTest extends BaseTest {
	
	@Description("Navigate to My Account page")
	@BeforeClass
	public void login() {
		loginPage = homePage.navigateToLogin();
		accPage = loginPage.doLoginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Description("Account Page - Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void VerifyAccountPageTitle() {
		String actPageTitle = accPage.getPageTitle();
		ChainTestListener.log("Account Page Title : "+actPageTitle);
		Assert.assertEquals(actPageTitle, AppConstants.MYACCOUNT_PAGE_TITLE);
	}
	
	@Description("Account Page - Partial URL Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyAccountPageURL() {
		String actAccountPageURL = accPage.getPageURL();
		ChainTestListener.log("Account Page URL : "+actAccountPageURL);
		Assert.assertTrue(actAccountPageURL.contains(AppConstants.MYACCOUNT_PAGE_PARTIAL_URL));
	}
	
	@Description("Account Page - Top menu options Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyTopNavMyAccountMenuList() {
		 List<String> actTopNavMyAccountMenuList = accPage.getAccountMenuList();
		 ChainTestListener.log("Account Page - My Account Menu options : " + actTopNavMyAccountMenuList);
		 Assert.assertEquals(actTopNavMyAccountMenuList, AppConstants.AfterLoginTopNavMyAccountList);
	}
	
	@Description("Account Page - Section Headings Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyAccountPageSectionHeadings() {
		List<String> actAccountSectionHeadingList = accPage.getAccountHeadingList();
		ChainTestListener.log("My Account page - section headings : " + actAccountSectionHeadingList);
		Assert.assertEquals(actAccountSectionHeadingList, AppConstants.AfterLoginAccountSectionHeadingList);
	}
	
	@Description("Account Page - Right Navigation links Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyAccountPageRightNavLinkList() {
		List<String> actAccountRightNavLinkList = accPage.getRightNavLinks();
		ChainTestListener.log("My Account Page - Right Nav links : "+ actAccountRightNavLinkList);
		Assert.assertEquals(actAccountRightNavLinkList, AppConstants.AfterLoginRightNavList);
	}
	
	

}
