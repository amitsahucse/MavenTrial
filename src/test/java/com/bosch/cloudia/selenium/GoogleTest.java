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


public class GoogleTest {
	static WebDriver driver;
	static ScreenshotHelper sh;
	@Tag("Mandatory")
	@Test
	public void grafanaLoginMethod() {
		Assertions.assertTrue(false);
	}

	@BeforeAll
	public static void openBrowser() throws Exception{
		Properties properties = new ReadProperties().getFile();
		driver = OpenChromeBrowser.openURL(properties.getProperty("google.url"));
		
		sh = new ScreenshotHelper();
		throw new Exception();
	}

	@AfterAll
	public static void closeBrowser() {
		driver.close();
	}


}