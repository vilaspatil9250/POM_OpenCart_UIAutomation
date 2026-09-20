package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchListPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(SearchListPage.class);
	
	public SearchListPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//page objects
	private By breadCrumbLastLink = By.xpath("(//ul[@class='breadcrumb']//a)[last()]");
	private By heading = By.tagName("h1");
	private By productNames = By.cssSelector("div.product-thumb h4");
	
	//get breadCrumb
	public String getSearchListPageBreadCrumb() {
		String breadCrumbEndItem = eleUtil.waitforElementVisibility(breadCrumbLastLink, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("Page BreadCrumb contains : " +breadCrumbEndItem);
		return breadCrumbEndItem;
	}
	
	//get page heading
	public String getSearchListPageHeading() {
		String pageHeading = eleUtil.waitforElementVisibility(heading, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("Page heading : "+ pageHeading);
		return pageHeading;
	}
	
	//get listed product names
	public List<String> getProductListNames() {
		List<String> productList = eleUtil.getElementsListText(productNames, AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("Products listed on page : "+ productList);
		return productList;
	}
	
	//get listed product count
	public int getProductListCount() {
		int productListCount = eleUtil.getElementsListText(productNames, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		log.info("Count of listed products : "+ productListCount);
		return productListCount;
	}
	
	//click on any specific product
	public ProductInfoPage clickProductLink(String prodcutName) {
		eleUtil.doClick(By.linkText(prodcutName));
		return new ProductInfoPage(driver);
	}
	
}
