package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ReturnsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static Logger log = LogManager.getLogger(ContactUsPage.class);
	
	public ReturnsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//Page objects
	
	private By heading = By.tagName("h1");
	
	public String getContactUsPageHeading() {
		String pageheading = eleUtil.waitforElementVisibility(heading, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("Returns Page heading : "+ pageheading);
		return pageheading;
	}
	

}
