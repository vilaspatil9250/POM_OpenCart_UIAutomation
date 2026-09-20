package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ShoppingCartPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(ShoppingCartPage.class);

	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page objects
	private By breadCrumbLastLink = By.xpath("(//ul[@class='breadcrumb']//a)[last()]");
	private By heading = By.tagName("h1");
	private By productUnitPrice = By.xpath("(//div[@class='table-responsive']//tbody//td)[last()-1]");
	private By removeProductBtn = By.xpath("//td//span[@class='input-group-btn']//button[@data-original-title='Remove']");
	private By emptyCartMsg = By.xpath("//div[@id='content']/p[contains(text(),'empty')]");

	// method to get breadcrumb
	public String getBreadCrumb() {
		String breadcrumbLastLink = eleUtil
				.waitforElementVisibility(breadCrumbLastLink, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println("BreadCrumb last link =" + breadcrumbLastLink);
		return breadcrumbLastLink;
	}

	// method to get page heading
	public String getPageHeading() {
		String shoppingCartPageHeading = eleUtil.waitforElementVisibility(heading, AppConstants.DEFAULT_SHORT_WAIT)
				.getText();
		System.out.println("Shopping Cart Page Heading = " + shoppingCartPageHeading);
		return shoppingCartPageHeading;
	}

	// method to getProductName
	public String getProductName(String prodcutName) {
		By productNameLoc = By.xpath("//div[@id='content']//tbody//td/a[text()=\'" + prodcutName + "\']");
		String cartProduct = eleUtil.waitforElementVisibility(productNameLoc, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product avaialble in cart Table = "+ cartProduct);
		return cartProduct;
	}
	
	//method to get unit price
	public String getProductUnitPrice() {
		String productPrice = eleUtil.waitforElementVisibility(productUnitPrice, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product Unit price = "+ productPrice);
		return productPrice;
	}
	
	//method to remove product from Cart
	public String removeProduct() {
		List<WebElement> removeBtn = eleUtil.waitforElementsPresence(removeProductBtn, AppConstants.DEFAULT_SHORT_WAIT);
		for(int i=1; i<=removeBtn.size(); i++) {
			for(WebElement e: removeBtn) {
				e.click();
			}
		}
		String cartEmptyMsg = eleUtil.waitforElementVisibility(emptyCartMsg, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println("Cart Empty message = "+ cartEmptyMsg);
		return cartEmptyMsg;
		
	}
	

}
