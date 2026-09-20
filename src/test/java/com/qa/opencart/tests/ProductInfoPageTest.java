package com.qa.opencart.tests;

import java.util.Map;

import javax.xml.xpath.XPathVariableResolver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.SearchListPage;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void doLogin() {
		loginPage = homePage.navigateToLogin();
		accPage = loginPage.doLoginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] productSearch() {
		return new Object[][] { 
			{ "macbook", "MacBook" }, 
			{ "macbook", "MacBook Air" }, 
			{ "macbook", "MacBook Pro" },
			{ "samsung", "Samsung SyncMaster 941BW" }, 
			{ "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "productSearch")
	public void verifyProductInfoPageBreadCrumb(String SearchKey, String ProductName) {
		searchListPage = accPage.searchProduct(SearchKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		String actBreadCrumb = productInfoPage.getProductPageBreadCrumb();
		Assert.assertEquals(actBreadCrumb, ProductName);
	}

	@Test(dataProvider = "productSearch")
	public void verifyProductInfoPageHeading(String SearchKey, String ProductName) {
		searchListPage = accPage.searchProduct(SearchKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		String actProductHeading = productInfoPage.getProductPageHeading();
		Assert.assertEquals(actProductHeading, ProductName);
	}

	@DataProvider
	public Object[][] productImage() {
		return new Object[][] { 
			{ "macbook", "MacBook", 5 }, 
			{ "macbook", "MacBook Air", 4 },
			{ "macbook", "MacBook Pro", 4 },
			{ "samsung", "Samsung SyncMaster 941BW", 1 },
			{ "samsung", "Samsung Galaxy Tab 10.1", 7 }, 
			{ "apple", "Apple Cinema 30\"", 6 },
			{ "iphone", "iPhone", 6 } };
	}

	@Test(dataProvider = "productImage")
	public void verifyProductImageCount(String searchKey, String ProductName, int imageCount) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		int actProductImageCount = productInfoPage.getProductImageCount();
		Assert.assertEquals(actProductImageCount, imageCount);
	}

	@DataProvider
	public Object[][] productCost() {
		return new Object[][] { 
			{ "apple", "Apple Cinema 30\"", "$110.00" }, 
			{ "macbook", "MacBook Pro", "$2,000.00" },
			{ "samsung", "Samsung Galaxy Tab 10.1", "$241.99" }, 
			{ "canon", "Canon EOS 5D", "$98.00" },
			{ "iphone", "iPhone", "$123.20" } };
	}

	@Test(dataProvider = "productCost")
	public void verifyProductUnitCost(String searchKey, String productName, String unitCost) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(productName);
		String actProductCost = productInfoPage.getProductCost();
		Assert.assertEquals(actProductCost, unitCost);
	}

	@Test(dataProvider = "productSearch")
	public void verifyProductaddedMessage(String searchKey, String ProductName) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		String actMessage = productInfoPage.addProductToCart();
		shoppingCartPage = productInfoPage.viewCart();
		shoppingCartPage.removeProduct();
		Assert.assertTrue(actMessage.contains("Success: You have added " + ProductName + " to your shopping cart!"));

	}
	
	@DataProvider
	public Object[][] productInfoData() {
		return new Object[][] {
			{"ProductName","MacBook Air"},
			{"ProductImages","4"},
			{"Brand","Apple"},
			{"Product Code","Product 17"},
			{"Reward Points","700"},
			{"Availability","Out Of Stock"},
			{"ProductPrice","$1,202.00"},
			{"ExTaxPrice","$1,000.00"},
		};
	}
	
	@DataProvider
	public Object[][] productInfo(){
		return new Object[][] {
			{"macbook", "MacBook Air", "4", "Apple", "Product 17", "700", "Out Of Stock","$1,202.00","$1,000.00"}
		};
	}
	
	@Test(dataProvider = "productInfo")
	public void verifyProductInfo(String searchKey, String productName, String imgCount, String brand, String productCode,
								  String rewardPoints, String availability, String unitPrice, String taxPrice) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(productName);
		Map<String, String> actProductinfo = productInfoPage.getProductInfo();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actProductinfo.get("ProductName"), productName);
		softAssert.assertEquals(actProductinfo.get("ProductImages"), imgCount);
		softAssert.assertEquals(actProductinfo.get("Brand"), brand);
		softAssert.assertEquals(actProductinfo.get("Product Code"), productCode);
		softAssert.assertEquals(actProductinfo.get("Reward Points"), rewardPoints);
		softAssert.assertEquals(actProductinfo.get("Availability"), availability);
		softAssert.assertEquals(actProductinfo.get("ProductPrice"), unitPrice);
		softAssert.assertEquals(actProductinfo.get("ExTaxPrice"), taxPrice);
		softAssert.assertAll();
	}

}
