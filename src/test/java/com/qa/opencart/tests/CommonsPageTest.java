package com.qa.opencart.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.CommonsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP-03 : All pages - common components")
@Feature("FE-03 : All pages - common features")
@Story("US-03 : All pages - Header, logo and footer")
public class CommonsPageTest extends BaseTest{
	
	@BeforeClass
	public void commonPage() {
		commonsPage = new CommonsPage(driver);
	}
	
	@Description("Header section - Links")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyHeaderLinks() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonsPage.getHeaderCurrancy());
		softAssert.assertTrue(commonsPage.getHeaderContact());
		softAssert.assertTrue(commonsPage.getHeaderMyAccount());
		softAssert.assertTrue(commonsPage.getHeaderWishList());
		softAssert.assertTrue(commonsPage.getHeaderShoppingCart());
		softAssert.assertTrue(commonsPage.getHeaderCheckOut());
		softAssert.assertAll();
	}
	
	@Description("Application Logo")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyAppLogo() {
		Assert.assertTrue(commonsPage.getAppLogo());
	}
	
	@Description("Footer Links")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Vilas")
	@Test
	public void verifyFooterLinks() {
		int actFooterLinksCount = commonsPage.getFooterLinks();
		Assert.assertEquals(actFooterLinksCount, AppConstants.FOOTER_LINKS_COUNT);
	}

}
