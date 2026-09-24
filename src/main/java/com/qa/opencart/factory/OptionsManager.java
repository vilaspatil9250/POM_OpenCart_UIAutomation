package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromOptions() {
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			co.addArguments("--incognito");
		}if(Boolean.parseBoolean(prop.getProperty("remote"))){
			co.setCapability("browserName", "chrome");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		} if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--private");
		}if(Boolean.parseBoolean(prop.getProperty("remote"))){
			fo.setCapability("browserName", "firefox");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless=new");
		} if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--inprivate");
		} if(Boolean.parseBoolean(prop.getProperty("remote"))){
			eo.setCapability("browserName", "edge");
		}
		return eo;
	}
	
	
}
