package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;
	private static final Logger log = LogManager.getLogger(ProductInfoPage.class);

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page objects
	private By breadCrumbLastLink = By.xpath("(//ul[@class='breadcrumb']//a)[last()]");
	private By heading = By.tagName("h1");
	private By imageList = By.cssSelector("ul.thumbnails img");
	private By productCost = By.cssSelector("div#content div.col-sm-4 h2");
	private By addToCartBtn = By.xpath("//button[text()='Add to Cart']");
	private By cartSuccessAlert = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By cartItemsBtn = By.cssSelector("div#cart");
	private By viewCartlink = By.xpath("//div[@id='cart']//a/strong[contains(text(), 'View Cart')]");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	// get breadCrumb
	public String getProductPageBreadCrumb() {
		String breadCrumbEndItem = eleUtil.waitforElementVisibility(breadCrumbLastLink, AppConstants.DEFAULT_SHORT_WAIT)
				.getText();
		log.info("Page BreadCrumb contains : " + breadCrumbEndItem);
		return breadCrumbEndItem;
	}

	// get page heading
	public String getProductPageHeading() {
		String productName = eleUtil.waitforElementVisibility(heading, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("Page heading : " + productName);
		return productName;
	}

	// get product image count
	public int getProductImageCount() {
		int imageCount = eleUtil.waitforElementsPresence(imageList, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		log.info("Total product image count = " + imageCount);
		return imageCount;
	}

	// getProductCost
	public String getProductCost() {
		String productValue = eleUtil.waitforElementVisibility(productCost, AppConstants.DEFAULT_SHORT_WAIT).getText();
		log.info("Product Value = " + productValue);
		return productValue;
	}

	public Map<String, String> getProductInfo() {
		productMap = new LinkedHashMap<String, String>();
		productMap.put("ProductName", getProductPageHeading());
		productMap.put("ProductImages", String.valueOf(getProductImageCount()));
		getProductMetaInfo();
		getProductPriceInfo();
		log.info("Product Information \n"+productMap);
		return productMap;
	}

	public void getProductMetaInfo() {
		List<WebElement> metaList = eleUtil.waitforElementsPresence(productMetaData, AppConstants.DEFAULT_SHORT_WAIT);
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}

	public void getProductPriceInfo() {
		List<WebElement> priceList = eleUtil.waitforElementsPresence(productPriceData, AppConstants.DEFAULT_SHORT_WAIT);

		String productValue = priceList.get(0).getText();
		String exTaxValue = priceList.get(1).getText().split(":")[1].trim();

		productMap.put("ProductPrice", productValue);
		productMap.put("ExTaxPrice", exTaxValue);
	}

	// add product to cart
	public String addProductToCart() {
		eleUtil.waitforElementVisibility(addToCartBtn, AppConstants.DEFAULT_SHORT_WAIT).click();
		String addCartSuccessMessage = eleUtil
				.waitforElementVisibility(cartSuccessAlert, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		log.info("Alert message after adding produt to cart = " + addCartSuccessMessage);
		return addCartSuccessMessage;
	}

	// navigate to Cart Page
	public ShoppingCartPage viewCart() {
		eleUtil.waitforElementVisibility(cartItemsBtn, AppConstants.DEFAULT_SHORT_WAIT).click();
		eleUtil.waitforElementVisibility(viewCartlink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new ShoppingCartPage(driver);
	}

}
