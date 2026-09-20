package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	protected WebDriver driver;
	protected ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(RegistrationPage.class);
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//page objects
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By suscribeYes = By.xpath("//input[@name='newsletter' and @value='1']");
	private By suscribeNo = By.xpath("//input[@name='newsletter' and @value='0']");
	private By privacyPolicy = By.xpath("//input[@name='agree']");
	private By submit = By.xpath("//input[@value='Continue']");
	private By registerSuccessHeading = By.cssSelector("div#content h1");
	private By logout = By.xpath("//div[@class='list-group']/a[text()='Logout']");
	private By register = By.xpath("//div[@class='list-group']/a[text()='Register']");

	public boolean doRegistration(String firstName, String lastName, String email, String telePhone, String password, String suscribeValue) {
		eleUtil.waitforElementVisibility(this.firstName, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telePhone, telePhone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if(suscribeValue.equalsIgnoreCase("yes")) {
			eleUtil.doClick(suscribeYes);
		} else {
			eleUtil.doClick(suscribeNo);
		}
		eleUtil.doClick(privacyPolicy);
		eleUtil.doClick(submit);
		
		String regSuccessMsg =eleUtil.waitforElementVisibility(registerSuccessHeading, AppConstants.DEFAULT_LONG_WAIT).getText();
		if(regSuccessMsg.contains(AppConstants.registerSuccessHeading)) {
			log.info("Registration with Email :"+ email + "::::: Result : "+regSuccessMsg);
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		} else {
			return false;
		}
	}
}
