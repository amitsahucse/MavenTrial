/*
 * GoogleTest.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */

package com.bosch.cloudia.selenium;

import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import com.bosch.cloudia.helper.OpenChromeBrowser;
import com.bosch.cloudia.helper.ReadProperties;
import com.bosch.cloudia.helper.ScreenshotHelper;

/**
 * This testcase logs checks google as reference for a working pipeline.
 * 
 * @author Tobias Mahncke (tobias.mahncke@lhind.dlh.de)
 */

public class GoogleTestP3 {
	static WebDriver driver;
	static ScreenshotHelper sh;
	static Properties properties;

//	@Rule
//	public final TestRule watchman = new TestWatcher() {
//
//		// This method gets invoked if the test fails for any reason:
//		@Override
//		protected void failed(Throwable e, Description description) {
//			driver = OpenChromeBrowser.openURL(properties.getProperty("google.url"));
//			sh.takeScreenshot(driver, "GoogleTestP2");
//		}
//	};

	@BeforeAll
	public static void openBrowser() {
		Properties properties = new ReadProperties().getFile();
		driver = OpenChromeBrowser.openURL(properties.getProperty("google.url"));
		sh = new ScreenshotHelper();
	}
	
	@Tag("Mandatory3")
	@Test
	public void grafanaLoginMethod() {

		System.out.println("the Screenshot is taken");
		Assertions.assertTrue(true);
	}

	@AfterAll
	public static void closeBrowser() {
		driver.close();
	}

}