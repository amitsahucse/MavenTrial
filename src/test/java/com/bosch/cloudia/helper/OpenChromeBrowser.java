/*
 * OpenChromeBrowser.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */
package com.bosch.cloudia.helper;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This is a helper Class, used only to open the Chrome browser.
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de) 
 */

public class OpenChromeBrowser {

	public static WebDriver openURL(String url) {
        Properties properties = new ReadProperties().getFile();
        String version = "78.0.3904.70";

		WebDriverManager.chromedriver().version(version).setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-gpu");
		options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(url);

		return driver;
	}

}
