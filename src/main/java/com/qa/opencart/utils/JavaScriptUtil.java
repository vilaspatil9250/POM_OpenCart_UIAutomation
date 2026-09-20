package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	// method to get title of page
	public String getTitleByJS() {
		return js.executeScript("return document.title").toString();
	}
	
	//method to get current URL of page
	public String getURLByJS() {
		return js.executeScript("return document.URL;").toString();
	}
	
	//method to click on element
	public void doClickByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	//method to refresh page
	public void refreshPageByJS() {
		js.executeScript("history.go(0)");
	}

	//method to page forward
	public void pageForwardByJS() {
		js.executeScript("history.go(1)");
	}
	
	//method to page backward
	public void pageBackwardByJS() {
		js.executeScript("history.go(-1)");
	}
	
	//method to generate Alert pop up on page
	public void generateAlertByJS(String message) {
		js.executeScript("alert('"+message+"')");
	}
	
	//method to generate Prompt pop up on page
	public void generatePromptByJS(String message) {
		js.executeScript("prompt('"+message+"')");
	}
	
	//method to generate Confirm pop up on page
	public void generateConfirmByJS(String message) {
		js.executeScript("confirm('"+message+"')");
	}
	
	//method to get inner text on page
	public String  getInnerTextByJS() {
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	//method to scroll page upto mentioned height in pixel
	public void scrollPageByJS(int height) {
		js.executeScript("window.scrollTo(0, "+height+")");
	}
	
	//method to scroll page to bottom of page
	public void scrollPagetoBottomByJs() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	//method to scroll page to top of page
	public void scrollPagetoTopByJs() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	//draw border to any element
	public void drawBorderByJs(WebElement element) {
		js.executeScript("arguments[0].style.border = '3px solid red'", element);
	}
	
	//method to change Background color
	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" +color+ "'", element);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
	}
	
	//method to change flash background color
	public void flashBackGroundColor(WebElement element) {
		String bgColor =element.getCssValue("backgroundColor");
		
		for(int i=0; i<1; i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}
	}
	
	

}
