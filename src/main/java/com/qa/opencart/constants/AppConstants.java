package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	//username - first01last01@gmail.com
	//pwd = admin123
	
	public static final int DEFAULT_SHORT_WAIT = 5;
	public static final int DEFAULT_MEDIUM_WAIT = 10;
	public static final int DEFAULT_LONG_WAIT = 20;
	public static final int FOOTER_LINKS_COUNT = 15;
	
	public static final String HOME_PAGE_TITLE = "Your Store";
	public static final String homePageProductListHeading = "Featured";
	public static final List<String> homePageProductList = List.of("MacBook", "iPhone", "Apple Cinema 30\"", "Canon EOS 5D");
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_PARTIAL_URL = "route=account/login";
	public static final List<String> rightNavBeforeLoginList = List.of("Login", "Register", "Forgotten Password", "My Account",
																	   "Address Book", "Wish List", "Order History", "Downloads", 
																	   "Recurring payments", "Reward Points", 
																	   "Returns","Transactions","Newsletter");
	
	public static final String invalidLoginErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
	public static final String blankLoginErrorMsg = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	
	public static final String MYACCOUNT_PAGE_TITLE = "My Account";
	public static final String MYACCOUNT_PAGE_PARTIAL_URL = "route=account/account";
	public static final List<String> AfterLoginRightNavList = List.of("My Account", "Edit Account", "Password", 
																	   "Address Book", "Wish List", "Order History", "Downloads", 
																	   "Recurring payments", "Reward Points", 
																	   "Returns","Transactions","Newsletter", "Logout");
	public static final List<String> AfterLoginTopNavMyAccountList = List.of("My Account", "Order History", "Transactions",
																			 "Downloads", "Logout");
	public static final List<String> AfterLoginAccountSectionHeadingList = List.of("My Account", "My Orders",
																					"My Affiliate Account", "Newsletter");
	
	
	public static final String searchListBreadCrumbLastLink = "Search";
	public static final String searchListHeading = "Search - ";
	
	public static final String shoppingCartBreadCrumbLastLink = "Shopping Cart";
	public static final String shoppingCartHeading = "Shopping Cart";
	
	public static final String registerSuccessHeading = "Your Account Has Been Created!";
}
