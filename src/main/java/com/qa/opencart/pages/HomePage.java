package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(HomePage.class);
	
	//private page objects
	private By myAccountMenu = By.xpath("//div[@id='top-links']//a[@title='My Account']");
	private By loginLink = By.linkText("Login");
	private By registerLink = By.linkText("Register");
	private By productHeading = By.xpath("//h3[text()='Featured']");
	private By procuctList = By.xpath("//div[@class='row']//h4");

	
	//constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("Get title of Home Page")
	public String getHomePageTitle() {
		String homePageTitle = eleUtil.waitforPageTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Home Page Title = " + homePageTitle);
		return homePageTitle;
	}
	
	@Step("Get Home Page Heading")
	public String getHomePageProductListHeading() {
		String productlistHeading = eleUtil.doGetText(productHeading);
		log.info("Product List Heading : "+ productlistHeading);
		return productlistHeading;
	}
	
	@Step("Get list of products displayed on Home page")
	public List<String> getHomePageProductList() {
		List<String> productNameList = eleUtil.getElementsListText(procuctList, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Total products listed on Home Page : "+ productNameList.size());
		log.info("Products available on Home Page : "+ productNameList);
		return productNameList;
	}
	
	
	@Step("Navigate to Login page")
	public LoginPage navigateToLogin() {
		eleUtil.doClick(myAccountMenu);
		eleUtil.waitforElementClickable(loginLink, AppConstants.DEFAULT_SHORT_WAIT);
		return new LoginPage(driver);
	}
	
	@Step("Navigate to Registration page")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.waitforElementVisibility(myAccountMenu, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		eleUtil.waitforElementVisibility(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegistrationPage(driver);
	}

}
