package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By headerCurrancy = By.xpath("//button/span[contains(text(),'Currency')]");
	private By headerContact = By.xpath("//div[@id='top-links']//a[contains(@href,'contact')]/following-sibling::span");
	private By headerAccount = By.xpath("(//div[@id='top-links']//a[@title='My Account']//span)[1]");
	private By headerWishList = By.xpath("(//div[@id='top-links']//a[@id='wishlist-total']//span)[1]");
	private By headerCart = By.xpath("//div[@id='top-links']//a[@title='Shopping Cart']//span");
	private By headerCheckout = By.xpath("//div[@id='top-links']//a[@title='Checkout']//span");
	private By appLogo = By.xpath("//img[@title='naveenopencart']");
	private By footerLinks = By.cssSelector("footer li");
	
	public boolean getHeaderCurrancy() {
		if(	eleUtil.checkIsDisplayed(headerCurrancy)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHeaderContact() {
		if(	eleUtil.checkIsDisplayed(headerContact)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHeaderMyAccount() {
		if(	eleUtil.checkIsDisplayed(headerAccount)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHeaderWishList() {
		if(	eleUtil.checkIsDisplayed(headerWishList)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHeaderShoppingCart() {
		if(	eleUtil.checkIsDisplayed(headerCart)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHeaderCheckOut() {
		if(	eleUtil.checkIsDisplayed(headerCheckout)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getAppLogo() {
		if(	eleUtil.checkIsDisplayed(appLogo)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getFooterLinks() {
		 return eleUtil.getElementsListText(footerLinks, AppConstants.DEFAULT_MEDIUM_WAIT).size();
	}

}
