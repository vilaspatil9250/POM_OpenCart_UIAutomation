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

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(AccountPage.class);
	
	//page objects
	private By AccHearders = By.xpath("//div[@id='account-account']//h2");
	private By topNavMyAccount = By.xpath("//div[@id='top-links']//a[@title='My Account']");
	private By MyAccountMenu = By.xpath("//a[@title='My Account']/following-sibling::ul//a");
	private By rightNavLinks = By.xpath("//div[@class='list-group']/a");
	private By editAccountLink = By.partialLinkText("Edit your account");
	private By logout = By.xpath("//div[@class='list-group']/a[text()='Logout']");
	private By searchField = By.xpath("//div[@id='search']/input");
	private By searchFButton = By.xpath("//div[@id='search']//button");
	private By shoppingCart = By.xpath("//div[@id='top-links']//span[text()='Shopping Cart']");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("Get Account Page Title")
	//method - get page title
	public String getPageTitle() {
		String accountPageTitle = eleUtil.waitforPageTitleIs(AppConstants.MYACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Title of the Page: "+accountPageTitle);
		return accountPageTitle;
	}
	
	@Step("Get Account Page URL")
	//method - get page URL
	public String getPageURL() {
		String accountPageURL = eleUtil.waitforURLContains(AppConstants.MYACCOUNT_PAGE_PARTIAL_URL, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("My account Page URL: "+ accountPageURL);
		return accountPageURL;
	}
	
	@Step("Get list of links available in My Account - Top Menu")
	//method - top nav - Account menu items
	public List<String> getAccountMenuList() {
		eleUtil.waitforElementVisibility(topNavMyAccount, AppConstants.DEFAULT_SHORT_WAIT).click();
		List<String> accMenuitemList = eleUtil.getElementsListText(MyAccountMenu, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("My Account Page - My Account Menus : " + accMenuitemList);
		return accMenuitemList;
	}
	
	@Step("Get list of headings of sections displayed on Account page")
	//method - get account section headings
	public List<String> getAccountHeadingList() {
		List<String> accHeaderList = eleUtil.getElementsListText(AccHearders, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Accoung section headings : " + accHeaderList);
		return accHeaderList;
	}
	
	@Step("Get list of Right Navigation links available on My Account page")
	//method - get right nav links
	public List<String> getRightNavLinks() {
		List<String> loggedInRightNavLinks = eleUtil.getElementsListText(rightNavLinks, AppConstants.DEFAULT_SHORT_WAIT);
		log.info("Right navigation links for Logged-in user : "+ loggedInRightNavLinks);
		return loggedInRightNavLinks;
	}
	
	@Step("Search for Product : \"{0}\"")
	public SearchListPage searchProduct(String productName) {
		WebElement search = eleUtil.waitforElementVisibility(searchField, AppConstants.DEFAULT_SHORT_WAIT);
		search.clear();
		search.sendKeys(productName);
		eleUtil.doClick(searchFButton);
		return new SearchListPage(driver);
	}
	
	@Step("Navigate to My Account information page")
	//method - click on Edit Account link
	public MyAccountInfo navigateToEditAccount() {
		eleUtil.waitforElementVisibility(editAccountLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new MyAccountInfo();
	}
	
	@Step("Navigate to Shopping Cart page")
	public ShoppingCartPage navigateToShoppingCart() {
		eleUtil.waitforElementVisibility(shoppingCart, AppConstants.DEFAULT_SHORT_WAIT).click();;
		return new ShoppingCartPage(driver);
	}
	
	@Step("Navigate to Logout page")
	//method - click on logout link
	public AccountLogout doLogout() {
		eleUtil.waitforElementVisibility(logout, AppConstants.DEFAULT_SHORT_WAIT);
		return new AccountLogout();
	}
	
	
}
