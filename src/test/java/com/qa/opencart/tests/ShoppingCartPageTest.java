package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

public class ShoppingCartPageTest extends BaseTest {
	
	@BeforeClass
	public void doLogin() {
		loginPage = homePage.navigateToLogin();
		accPage = loginPage.doLoginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider 
	public Object[][] productSearch(){
		return new Object[][] {
			{"macbook","MacBook Pro"}
		};
	}
	
	@Test(dataProvider = "productSearch")
	public void verifyShoppingCartPageBreadCrumb(String SearchKey, String ProductName) {
		searchListPage = accPage.searchProduct(SearchKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		productInfoPage.addProductToCart();
		shoppingCartPage = productInfoPage.viewCart();
		String actBreadCrumb = shoppingCartPage.getBreadCrumb();
//		shoppingCartPage.removeProduct();
		Assert.assertEquals(actBreadCrumb, AppConstants.shoppingCartBreadCrumbLastLink);
	}

	@Test(dataProvider = "productSearch")
	public void verifyShoppingCartPageHeading(String SerachKey, String ProductName) {
		searchListPage = accPage.searchProduct(SerachKey);
		productInfoPage = searchListPage.clickProductLink(ProductName);
		productInfoPage.addProductToCart();
		shoppingCartPage = productInfoPage.viewCart();
		String actHeading = shoppingCartPage.getPageHeading();
//		shoppingCartPage.removeProduct();
		Assert.assertTrue(actHeading.contains(AppConstants.shoppingCartHeading));
	}
	
	@DataProvider
	public Object [][] productCart() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"iphone","iPhone",},
			{"imac","iMac",}
		};
	}
	
	@Test(dataProvider = "productCart")
	public void verifyCartTableProductName(String searchKey, String productName) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(productName);
		productInfoPage.addProductToCart();
		shoppingCartPage = productInfoPage.viewCart();
		String actCartProduct = shoppingCartPage.getProductName(productName);
//		shoppingCartPage.removeProduct();
		Assert.assertEquals(actCartProduct, productName);
	}
	
	@DataProvider
	public Object[][] cartUnitCost() {
		return new Object[][] {
			{"macbook","MacBook Air","$1,202.00"},
			{"samsung","Samsung SyncMaster 941BW","$242.00"},
			{"iphone","iPhone","$123.20"},
			{"imac","iMac","$122.00"}
		};
	}
	
	@Test(dataProvider = "cartUnitCost")
	public void verifyCartProductUnitPrice(String searchKey, String productName, String unitCost) {
		searchListPage = accPage.searchProduct(searchKey);
		productInfoPage = searchListPage.clickProductLink(productName);
		productInfoPage.addProductToCart();
		shoppingCartPage = productInfoPage.viewCart();
		String actCartProductUnitPrice = shoppingCartPage.getProductUnitPrice();
		Assert.assertEquals(actCartProductUnitPrice, unitCost);
	}
	
	
	@AfterMethod
	public void clearCart() {
		shoppingCartPage = accPage.navigateToShoppingCart();
		shoppingCartPage.removeProduct();
		
	}
	
}
