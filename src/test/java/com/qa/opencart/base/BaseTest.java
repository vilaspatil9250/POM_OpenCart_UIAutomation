package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchListPage;
import com.qa.opencart.pages.ShoppingCartPage;

import io.qameta.allure.Description;

public class BaseTest {

	protected WebDriver driver;
	protected DriverFactory df;
	protected Properties prop;
	protected HomePage homePage;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SearchListPage searchListPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingCartPage shoppingCartPage;
	protected RegistrationPage registrationPage;
	protected CommonsPage commonsPage;

	@Description("Open \"{0}\" browser and enter URL")
	@Parameters({ "browser" })
	@BeforeTest
	public void setup(@Optional("chrome") String browserName) {
		df = new DriverFactory();
		prop = df.iniProperties();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void attachScreenShot(ITestResult result) {
		if (!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotByte(), "image/png");
		}
	}

	@Description("Close Browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
