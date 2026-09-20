package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil js;
	private static final Logger log = LogManager.getLogger(ElementUtil.class);

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		js = new JavaScriptUtil(driver);
	}

	// method to check WebElement is available - returns true only if element is
	// available
	// does not return false - if element is not available -
	public boolean checkIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			log.info(locator + " Element is not found.");
			return false;
		}
	}

	public boolean checkIsDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			log.info(element + " - Element is not found.");
			return false;
		}
	}

	public boolean checkisEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	// method to get value entered in WebElement
	public String doGetValue(By locator) {
		return getElement(locator).getAttribute("value");
	}

	// method to get text value of WebElement
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	// method to enter value in WebElement
	// Java throws IllegalArgumentException if value is null. to handle this, added
	// try catch block with relevant message
	public void doSendKeys(By locator, String value) {
		if (value == null) {
			throw new ElementException("Value is null. It should not be null.");
		}
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	// SendKeys method allows user to enter multiple values. Which can be achived by
	// CharSequence... parameter
	// CharSequence is Interface
	public void doMultipleSendKeys(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
	}

	// method to perform click action on WebElement
	public void doClick(By locator) {
		getElement(locator).click();
	}

	// method to get value of mentioned attribute of element
	public String getElementDOMAttributeValue(By locator, String attrValue) {
		return getElement(locator).getDomAttribute(attrValue);
	}

	// method to get value of mentioned DOM Property of element
	public String getElementDOMPropertyValue(By locator, String propValue) {
		return getElement(locator).getDomProperty(propValue);
	}

	// method to get WebElement
	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highLightEle)) {
			js.flashBackGroundColor(ele);
		}
		return ele;
	}

	// method to retun list of Webelements
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// return count of element list
	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	// method to return list of links of all elements
	public List<String> getElementTextList(By locator) {
		List<String> linkTextList = new ArrayList<String>();
		for (WebElement e : getElements(locator)) {
			String linkText = e.getText();
			if (linkText.length() != 0) {
				linkTextList.add(linkText);
			}
		}
		return linkTextList;
	}

	// Method to check if 1 element is present or not
	public boolean isElementExist(By locator) {
		if (getElementCount(locator) == 1) {
			log.info("Element is presnet - PASS");
			return true;
		} else {
			log.info("Element is not presnet - FAIL");
			return false;
		}
	}

	// Method to check if element is present for mentioned times
	public boolean isElementExists(By locator, int eleExpCout) {
		if (getElementCount(locator) == eleExpCout) {
			log.info("\'" + locator + "\'" + "is present : " + eleExpCout + " times - PASS.");
			return true;
		} else {
			log.info("\'" + locator + "\'" + "is not present : " + eleExpCout + " times - FAIL.");
			return false;
		}
	}

	// get list of webelements (static list) and then click on mentioned element
	public void doClickElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String Text = e.getText();
			if (Text.contains(eleText)) {
				if (Boolean.parseBoolean(DriverFactory.highLightEle)) {
					js.flashBackGroundColor(e);
				}
				e.click();
				break;
			}
		}
	}

	// Enter text in Search field and select any element of searched result list
	// (dynamic list)
	public void doSeacrh(By locator, String searchKey, By searchListLoc, String textToSearch)
			throws InterruptedException {
		doSendKeys(locator, searchKey);
		Thread.sleep(5000);

		List<WebElement> searchListEle = getElements(searchListLoc);
		log.info("Total search result count: " + searchListEle.size());
		for (WebElement e : searchListEle) {
			String searchText = e.getText();
			log.info(searchText);
			if (searchText.contains(textToSearch)) {
				e.click();
				break;
			}
		}
	}

	///////////// method to perform action on Select Dropdown/////////////////

	// method to select value from Select dropdown by using index
	public void doSelectEleByIndex(By locator, int index) {
		Select countrydd = new Select(getElement(locator));
		countrydd.selectByIndex(index);
	}

	// method to select value from Select dropdown by using value attribute of
	// element
	public void doSelectEleByValue(By locator, String value) {
		Select countrydd = new Select(getElement(locator));
		countrydd.selectByValue(value);
	}

	// method to select value from Select dropdown by using text
	public void doSelectEleByVisibleText(By locator, String value) {
		Select countrydd = new Select(getElement(locator));
		countrydd.selectByVisibleText(value);
	}

	// method to get list of Webelements using getOptions() menthod and then select
	// value from select dropdown list using uisng click()
	public void doSelectDropdownValue(By locator, String value) {
		Select selectdd = new Select(getElement(locator));
		List<WebElement> ddEleList = selectdd.getOptions();

		for (WebElement e : ddEleList) {
			String ddValue = e.getText();
			if (ddValue.contains(value)) {
				e.click();
			}
		}
	}

	// method to get list of values available in select dropdown list
	public List<String> getOptionsValueList(By locator) {
		Select selectdd = new Select(getElement(locator));
		List<WebElement> ddEleList = selectdd.getOptions();
		List<String> countryList = new ArrayList<String>();
		for (WebElement e : ddEleList) {
			String countryName = e.getText();
			countryList.add(countryName);
		}
		return countryList;

	}

	// method to get count of values available in select dropdown list
	public int getOptionsCount(By Locator) {
		Select selectdd = new Select(getElement(Locator));
		List<WebElement> ddElelist = selectdd.getOptions();
		int ddValueCount = ddElelist.size();
		return ddValueCount;
	}

	// method to get default selected value in Select dropdown
	public String getDefaultSelectedValue(By locator) {
		Select selectdd = new Select(getElement(locator));
		String defaultValue = selectdd.getFirstSelectedOption().getText();
		return defaultValue;
	}

	///// ***********Actions class utils******************//////////////////

	// method to navigate to desired element by using Actions class
	// this method is internal method and will not accessed outside of this class.
	// so Private
	private void moveToEle(By locator) {
		act.moveToElement(getElement(locator)).perform();
	}

	// method to click on any element having 2 levels using Actions class
	public void menuSubMenuHandlinglev2(By menutLoc, By submenuLoc) throws InterruptedException {
		moveToEle(menutLoc);
		Thread.sleep(2000);
		doClick(submenuLoc);
	}

	// method to click on any element having 3 levels using Actions class
	public void menuSubMenuHandlinglev3(By menuLevel1, By menuLevel2, By menuLevel3, String actionLevel01)
			throws InterruptedException {
		if (actionLevel01.equalsIgnoreCase("click")) {
			doClick(menuLevel1);
		} else if (actionLevel01.equalsIgnoreCase("mousehover")) {
			moveToEle(menuLevel1);
		}
		Thread.sleep(2000);
		moveToEle(menuLevel2);
		Thread.sleep(2000);
		doClick(menuLevel3);
	}

	// method to click on any element having 4 levels using Actions class
	public void menuSubMenuHandlinglev4(By menuLevel1, By menuLevel2, By menuLevel3, By menuLevel4,
			String actionLevel01) throws InterruptedException {
		if (actionLevel01.equalsIgnoreCase("click")) {
			doClick(menuLevel1);
		} else if (actionLevel01.equalsIgnoreCase("mousehover")) {
			moveToEle(menuLevel1);
		}
		Thread.sleep(2000);
		moveToEle(menuLevel2);
		Thread.sleep(2000);
		moveToEle(menuLevel3);
		Thread.sleep(2000);
		doClick(menuLevel4);
	}

	// Actions = Click method >> moveToElement + Click on middle of mentioned
	// element
	public void doActionsClick(By Locator) {
		act.click(getElement(Locator)).perform();
	}

	// Actions sendKeys >> moveToElement + Click on middle of mentioned element +
	// enter given value
	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	// Actions method - to enter value 1 by 1 character with specified time pause
	public void doActionsSendKeyswithPause(By locator, String value, long pauseTime) {
		char[] charArray = value.toCharArray();
		for (char ch : charArray) {
			act.sendKeys(getElement(locator), String.valueOf(ch)).pause(pauseTime).perform();
		}

	}

	// **************Wait related methods*************************//

	// wait for given timeout - return webelement
	// PresenceOfEle method - only check if ele is present in DOM.
	public WebElement waitforElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		if (Boolean.parseBoolean(DriverFactory.highLightEle)) {
			js.flashBackGroundColor(ele);
		}
		return ele;
	}

	// wait for given timeout - return webelement
	// VisibilityOfEle method - check for ele present in DOM and visible on webpage.
	public WebElement waitforElementVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		if (Boolean.parseBoolean(DriverFactory.highLightEle)) {
			js.flashBackGroundColor(ele);
		}
		return ele;
	}

	// wait for given timeout for visibility of alert and switch to alert
	public Alert waitforAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	// wait for Page title contains
	public String waitforPageTitleContains(String partialTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.titleContains(partialTitle));
		} catch (Exception e) {
			log.info("Expected Partial page title : " + partialTitle + " - not present");
		}
		return driver.getTitle();
	}

	// wait for page tile - exact match
	public String waitforPageTitleIs(String pageTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(pageTitle));
		} catch (Exception e) {
			log.info("Expected page title : " + pageTitle + " - not present");
		}
		return driver.getTitle();
	}

	// wait for page URL contains
	public String waitforURLContains(String partialURL, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.urlContains(partialURL));
		} catch (Exception e) {
			log.info("Expected partial URL - " + partialURL + " - is not present");
		}
		return driver.getCurrentUrl();
	}

	// wait for page URL - exact match
	public String waitforURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.urlContains(url));
		} catch (Exception e) {
			log.info("Expected URL - " + url + " - is not present");
		}
		return driver.getCurrentUrl();
	}

	// wait for window count - compare expected window count with Actual window
	// count and return boolean
	public boolean waitforWindow(int noOfExpWindows, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(noOfExpWindows));
		} catch (Exception e) {
			log.info("Actual opened window count is not equal to " + noOfExpWindows);
			return false;
		}

	}

	// Wait for iFrame for given time period + switch to frame once available
	public void waitforiFrame(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	// Wait for Elements for given period of time
	// presenceOfAllElementsLocatedBy method - An expectation for checking that
	// there is at least one element present on a web page.
	public List<WebElement> waitforElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// Wait for Elements for given period of time
	// visibilityOfAllElementsLocatedBy - An expectation for checking that all
	// elements present on the web page that match the locator are visible.
	// Visibility means that the elements are not only displayed but also have a
	// height and width that is greater than 0.
	public List<WebElement> waitforElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// return List<String> (List of WebElement text)
	public List<String> getElementsListText(By locator, int timeOut) {
		List<WebElement> eleList = waitforElementsPresence(locator, timeOut);
		List<String> eleListText = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (eleText.length() != 0) {
				if (Boolean.parseBoolean(DriverFactory.highLightEle)) {
					js.flashBackGroundColor(e);
				}
				eleListText.add(eleText);
			}
		}
		return eleListText;
	}

	// An expectation for checking an element is visible and enabled such that you
	// can click it.
	public void waitforElementClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		;
	}

	// Wait method with FluentWait features - wait for given time for visibility of
	// element
	public WebElement waitforElementwithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.withMessage("===Element is not visible===");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
