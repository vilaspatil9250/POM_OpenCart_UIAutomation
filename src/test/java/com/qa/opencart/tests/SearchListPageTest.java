package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class SearchListPageTest extends BaseTest {

	@BeforeClass
	public void doLogin() {
		loginPage = homePage.navigateToLogin();
		accPage = loginPage.doLoginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider 
	public Object[] singleValueData(){
		return new Object[]{"macbook"};
	}
	
	@Test(dataProvider = "singleValueData")
	public void verifySearchListPageBreadCrumb(String searchKey) {
		searchListPage = accPage.searchProduct(searchKey);
		String actBreadCrumbLastLink = searchListPage.getSearchListPageBreadCrumb();
		Assert.assertEquals(actBreadCrumbLastLink, AppConstants.searchListBreadCrumbLastLink);	
	}
	
	@DataProvider
	public Object[] singleValueForHeading() {
		return new Object[] {"macbook","samsung", "canon", "apple"};
	}
	
	@Test(dataProvider = "singleValueForHeading")
	public void verifySearchListPageHeading(String searchKey) {
		searchListPage = accPage.searchProduct(searchKey);
		String actHeading = searchListPage.getSearchListPageHeading();
		Assert.assertEquals(actHeading, AppConstants.searchListHeading+searchKey);
	}
	
	@DataProvider 
	public Object[][] getProductCount(){
		return new Object[][] {
			{"macbook",3},
			{"samsung",2},
			{"canon",1},
			{"apple",1},
		};
	}
	
	@Test(dataProvider = "getProductCount")
	public void verifySearchListProductCount(String searchKey, int productCount) {
		searchListPage = accPage.searchProduct(searchKey);
		int actCount = searchListPage.getProductListCount();
		Assert.assertEquals(actCount, productCount);
	}
	
	@DataProvider
	public Object[][] getProductNames(){
		return new Object[][]{
			{"macbook",List.of("MacBook","MacBook Air","MacBook Pro")},
			{"samsung",List.of("Samsung SyncMaster 941BW","Samsung Galaxy Tab 10.1")},
			{"canon",List.of("Canon EOS 5D")},
			{"apple",List.of("Apple Cinema 30\"")}
		};
	}
	
	@Test(dataProvider = "getProductNames")
	public void verifySearchListProducts(String searchKey, List<String> productList) {
		searchListPage = accPage.searchProduct(searchKey);
		List<String> actProductList = searchListPage.getProductListNames();
		Assert.assertEquals(actProductList, productList);
	}

}
