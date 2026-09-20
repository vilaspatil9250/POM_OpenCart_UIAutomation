package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
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

@Epic("EP-01 : Home Page")
@Feature("FE-01 : Home page features")
@Story("US-01 : Home Page - Title, Heading and list of products")
public class HomePageTest extends BaseTest{
	
	
	@Description("Home Page - Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyHomePageTitle() {
		String actTitle = homePage.getHomePageTitle();
		ChainTestListener.log("Home Page Title : "+ actTitle);
		Assert.assertEquals(actTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Description("Home Page - Heading Test")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Vilas")
	@Test
	public void verifyProductListHeading() {
		String actProductListHeading = homePage.getHomePageProductListHeading();
		ChainTestListener.log("Home Page-Product List heading : "+actProductListHeading);
		Assert.assertEquals(actProductListHeading, AppConstants.homePageProductListHeading);
	}
	
	@Description("Home Page - Product List Test")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyHomePageProductList() {
		List<String> actProductList = homePage.getHomePageProductList();
		ChainTestListener.log("Products listed on Home Page : "+actProductList);
		Assert.assertEquals(actProductList, AppConstants.homePageProductList);
	}

}
