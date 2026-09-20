package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	// page objects - private By locators
	private By headerCurrancy = By.xpath("//button/span[contains(text(),'Currency')]");
	private By headerContact = By.xpath("//div[@id='top-links']//a[contains(@href,'contact')]/following-sibling::span");
	private By headerAccount = By.xpath("(//div[@id='top-links']//a[@title='My Account']//span)[1]");
	private By headerWishList = By.xpath("(//div[@id='top-links']//a[@id='wishlist-total']//span)[1]");
	private By headerCart = By.xpath("//div[@id='top-links']//a[@title='Shopping Cart']//span");
	private By headerCheckout = By.xpath("//div[@id='top-links']//a[@title='Checkout']//span");
	private By appLogo = By.xpath("//img[@title='naveenopencart']");

	private By topNavigationBar = By.xpath("//ul[contains(@class,'navbar-nav')]/li/a");
	private By headingNewCust = By.xpath("(//div[@class='well']//h2)[1]");

	private By newCustRegLink = By.xpath("//div[@class='well']/a");

	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpwdLink = By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By rightNavLinks = By.xpath("//div[@class='list-group']/a");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("Get Login Page Title")
	public String getLoginPageTitle() {
		String loginPageTitle = eleUtil.waitforPageTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Login Page Title = " + loginPageTitle);
		return loginPageTitle;
	}

	@Step("Get Login Page URL")
	public String getLoginPageUrl() {
		String loginPageUrl = eleUtil.waitforURLContains(AppConstants.LOGIN_PAGE_PARTIAL_URL, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Login Page URL = " + loginPageUrl);
		return loginPageUrl;
	}

	@Step("Check logo displayed on Login Page")
	public boolean isLogoExist() {
		WebElement logo = eleUtil.waitforElementVisibility(appLogo, AppConstants.DEFAULT_SHORT_WAIT);
		return eleUtil.checkIsDisplayed(logo);
	}

	@Step("Check New Customer section heading displayed on Login Page")
	public boolean isNewCustomerHeaderExist() {
		WebElement newCustHeading = eleUtil.waitforElementVisibility(headingNewCust, AppConstants.DEFAULT_SHORT_WAIT);
		return eleUtil.checkIsDisplayed(newCustHeading);
	}

	@Step("Get List of links displayed in Right Navigation section of Login Page")
	public List<String> rightNavigationLinks() {
		List<String> linksTextList = eleUtil.getElementsListText(rightNavLinks, AppConstants.DEFAULT_SHORT_WAIT);
		log.info(linksTextList);
		return linksTextList;
	}
	
	@Step("Do login with valid credentials - username : \"{0}\"")
	public AccountPage doLoginWithValidCredentials(String uname, String pwd) {
		log.info("User Credentials: username - "+uname);
		eleUtil.waitforElementVisibility(username, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(uname);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		return new AccountPage(driver);
	}
	
	@Step("Do login with invalid credentials and get error message.")
	public boolean doLoginWithInvalidCredentials(String uname, String pwd) {
		log.info("user credentials (Invalid) : "+ uname +" : "+ pwd);
		WebElement usernameEle = eleUtil.waitforElementVisibility(username, AppConstants.DEFAULT_SHORT_WAIT);
		usernameEle.clear();
		usernameEle.sendKeys(uname);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginbtn);
		String errMsg = eleUtil.waitforElementVisibility(loginErrorMessage, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		log.info("Error message :: "+errMsg);
		if(errMsg.contains(AppConstants.invalidLoginErrorMsg) || errMsg.contains(AppConstants.blankLoginErrorMsg)) {
			return true;
		} else {
			return false;
		}
	}

}
